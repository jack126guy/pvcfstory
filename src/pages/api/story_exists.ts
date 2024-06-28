export function GET() {
	const responseObj = { doesExist: true };
	return new Response(JSON.stringify(responseObj), {
		headers: { 'Content-Type': 'application/json' },
	});
}
