#!/usr/bin/env sh

yarn postcss --config ./resources/dev/ src/leebonn/custom.css -d docs/css --watch --verbose
