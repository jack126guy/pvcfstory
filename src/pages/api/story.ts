export function GET() {
	const responseObj = {
		title: 'Example Story',
		author: 'Nobody',
		story: "This isn't really a story, [USERNAME]! This is just a test.",
		choices: {
			question: 'What do you do with this information?',
			options: [
				{
					text: 'Carry on with your day (Hero)',
					endingKey: 'HERO',
				},
				{
					text: 'Close your browser and turn off your computer (Chaos)',
					endingKey: 'CHAOS',
				},
			],
		},
		endings: [
			{
				key: 'HERO',
				story: 'You carry on with your day and nothing much else happens.\n\n(+1 page for Heroes)',
			},
			{
				key: 'CHAOS',
				story: 'You turn off your computer and...nothing much else happens.\n\n(+1 page for Chaos)',
			},
		],
	};
	return new Response(JSON.stringify(responseObj), {
		headers: { 'Content-Type': 'application/json' },
	});
}
