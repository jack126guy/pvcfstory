(function () {
	'use strict';
	var componentRoot = document.querySelector('.pvcfstory');
	componentRoot.addEventListener('click', function (e) {
		e.target.textContent += '!';
	});
})();
