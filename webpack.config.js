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
    		test: /\.js$/, loaders: ['babel'], exclude: [/node_modules/] 
	    }]
    }
};