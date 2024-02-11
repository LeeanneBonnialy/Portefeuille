/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./docs/**/*.{html,js}",
  ],
  theme: {
    extend: {},
    fontFamily: {
        regular: ['Proxima Nova Regular', 'sans-serif'],
        bold: ['Proxima Nova Bold', 'sans-serif'],
    },
  },
  plugins: [],
}
