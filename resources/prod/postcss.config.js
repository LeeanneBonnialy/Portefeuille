module.exports = function(ctx) {
    let plugins = [
        require('postcss-import')({
            from: ["src/leebonn/"]
        }),
        require('postcss-preset-env')({
            features: {
                'color-function': true,
                'nesting-rules': false,
            },
        }),
        require('tailwindcss/nesting'),
        require('tailwindcss')({
            config: 'resources/prod/tailwind.config.js'
        }),
        require('autoprefixer'),
        require('cssnano')({ preset: 'default' }),
    ]

    return { plugins }
}
