# PVCF Story Reader

[Live version](https://www.jackgraysonfox.xyz/pvcfstory/)

The PVCF Story Reader is an unofficial tool for reading stories from the [interactive storytelling adventure at Ponyville Ciderfest 2023](https://ponyvilleciderfest.com/2023-storytelling-adventure-alternate-histories/), packaged as an [Astro](https://astro.build/) component.

## Requirements

* Astro 4
* [vite-plugin-classic-js](https://github.com/jack126guy/vite-plugin-classic-js) (for loading the client-side script)

## Usage

```
---
import { PvcfStoryReader } from '@halfgray/pvcfstory';
---

<!-- ... -->
<PvcfStoryReader apiBase="/api" />
```

The `apiBase` prop is the base path for the API that provides the story information. Refer to the "API" section for details.

## API

The API provides story information based on a codeword. This API is a subset of that used by the official Ponyville Ciderfest mobile app.

There are two endpoints:

* **GET /story_exists?code=[code]**: Check if the story exists
* **GET /story?code=[code]**: Get the story information, including text, prompts, and endings

A demo API is included under `src/pages/api`. Refer to the endpoint scripts for response formats.

## License

PVCF Story Reader is available under the MIT License. Refer to `LICENSE.txt` for details.
