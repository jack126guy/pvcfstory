(function (createElement, appRoot, window, XMLHttpRequest, JSONparse, encodeURIComponent, decodeURIComponent) {
	'use strict';
	var apiBase = appRoot.getAttribute('data-api-base');

	function loadStory(code) {
		if (!code) {
			return;
		}

		hideElement('.result');
		hideElement('.error');
		showElement('.loading');

		var normalizedCode = normalizeStoryCode(code);
		appRoot.querySelector('.code').value = normalizedCode;

		requestJSON(apiBase + '/story_exists?code=' + encodeURIComponent(normalizedCode), function (existsResponse) {
			if (!existsResponse.doesExist) {
				hideElement('.loading');
				showElement('.error');
				return;
			}

			requestJSON(apiBase + '/story?code=' + encodeURIComponent(normalizedCode), function (storyResponse) {
				hideElement('.loading');
				if (storyResponse.story) {
					setStory(storyResponse);
					showElement('.result');
				} else {
					showElement('.error');
				}
			});
		});
	}

	function requestJSON(url, processResponse) {
		var xhr = new XMLHttpRequest();
		xhr.open('GET', url);
		xhr.addEventListener('load', function () {
			if (xhr.status === 200) {
				processResponse(JSONparse(xhr.responseText));
			} else {
				hideElement('.loading');
				showElement('.error');
			}
		});
		xhr.addEventListener('error', function () {
			hideElement('.loading');
			showElement('.error');
		});
		xhr.send();
	}

	function normalizeStoryCode(code) {
		return code.replace(/[#\s]/g, '').toUpperCase();
	}

	function setStory(response) {
		var resultElement = appRoot.querySelector('.result');
		resultElement.querySelector('.title').textContent = response.title;
		resultElement.querySelector('.author').textContent = response.author;
		resultElement.querySelector('.text').textContent = includeReaderName(response.story);
		resultElement.querySelector('.question').textContent = response.choices.question;
		resultElement.querySelector('.choices').textContent = '';
		for (var i = 0; i < response.choices.options.length; i++) {
			var option = response.choices.options[i];
			var ending = null;
			for (var j = 0; i < response.endings.length; j++) {
				if (option.endingKey === response.endings[j].key) {
					ending = response.endings[j];
					break;
				}
			}
			if (ending) {
				var choiceElement = makeChoiceElement(option, ending);
				resultElement.querySelector('.choices').appendChild(choiceElement);
			}
		}
	}

	function includeReaderName(text) {
		var readerName = appRoot.querySelector('.reader-name').value || '(Your name)';
		return text.replace(/\[USERNAME\]/g, readerName);
	}

	function makeChoiceElement(option, ending) {
		var mainElement = createElement('div');
		var optionElement = createElement('button');
		var endingElement = createElement('p');

		optionElement.className = 'option';
		optionElement.textContent = option.text;
		optionElement.addEventListener('click', function () {
			endingElement.style.display = 'block';
		});

		endingElement.className = 'ending';
		endingElement.style.display = 'none';
		endingElement.textContent = includeReaderName(ending.story);

		mainElement.appendChild(optionElement);
		mainElement.appendChild(endingElement);
		return mainElement;
	}

	function showElement(selector) {
		appRoot.querySelector(selector).style.display = 'block';
	}

	function hideElement(selector) {
		appRoot.querySelector(selector).style.display = 'none';
	}

	appRoot.querySelector('.code-get').addEventListener('click', function (e) {
		var storyCode = appRoot.querySelector('.code').value;
		if (storyCode) {
			window.location.hash = '#' + encodeURIComponent(normalizeStoryCode(storyCode));
		}
		e.preventDefault();
	});
	window.addEventListener('hashchange', loadStoryFromHash);
	window.addEventListener('load', loadStoryFromHash);

	function loadStoryFromHash() {
		var hash = window.location.hash.replace('#', '');
		if (hash) {
			loadStory(decodeURIComponent(hash));
		}
		else
		{
			hideElement('.result');
		}
	}

	appRoot.style.display = 'block';
})(document.createElement.bind(document), document.getElementById('pvcfstory'), window, XMLHttpRequest, JSON.parse, encodeURIComponent, decodeURIComponent);