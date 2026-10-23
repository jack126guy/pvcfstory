import { defineConfig } from 'astro/config';
import classicJs from '@halfgray/astro-plugin-classic-js';

export default defineConfig({
	output: 'server',
	vite: {
		build: {
			assetsInlineLimit: 0,
		},
		plugins: [classicJs()],
	},
});
