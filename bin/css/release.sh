#!/usr/bin/env sh

yarn postcss --config ./resources/prod/ src/leebonn/tailwind.css -d docs/css --verbose
yarn postcss --config ./resources/prod/ src/leebonn/custom.css -d docs/css --verbose
