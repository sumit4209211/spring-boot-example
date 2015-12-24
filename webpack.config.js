var path = require('path');

module.exports = {
    entry: './src/main/resources/static/js/app.js',
    devtool: 'sourcemaps',
    cache: true,
    debug: true,
    output: {
        path: __dirname,
        filename: 'src/main/resources/static/js/bundle.js'
    },
    module: {
    	loaders: [{
    	   test: /\.jsx?$/, // A regexp to test the require path. accepts either js or jsx
    	   loader: 'babel' // The module to load. "babel" is short for "babel-loader"
	    }]
    }
};