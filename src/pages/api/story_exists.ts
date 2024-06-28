import { type APIContext } from 'astro';

export function GET({ request }: APIContext) {
	const storyCode = new URL(request.url).searchParams.get('code');
	const responseObj = { doesExist: storyCode === 'EXAMPLE' };
	return new Response(JSON.stringify(responseObj), {
		headers: { 'Content-Type': 'application/json' },
	});
}
