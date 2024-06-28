import { defineConfig } from 'astro/config';
import classicJs from '@halfgray/vite-plugin-classic-js';

export default defineConfig({
	vite: {
		build: {
			assetsInlineLimit: 0,
		},
		plugins: [classicJs()],
	},
});
