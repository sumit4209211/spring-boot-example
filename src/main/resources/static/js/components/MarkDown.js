/**
 * Created by wonwoo on 15. 12. 27..
 */

import React, { Component } from 'react';
import Remarkable from 'remarkable';
import hljs from 'highlight.js';

require('../../css/default.css');
require('../../css/markdown.css');

var md = new Remarkable('full', {
	  html:         false,        // Enable HTML tags in source
	  xhtmlOut:     false,        // Use '/' to close single tags (<br />)
	  breaks:       false,        // Convert '\n' in paragraphs into <br>
	  langPrefix:   'language-',  // CSS language prefix for fenced blocks
	  linkify:      true,         // autoconvert URL-like texts to links
	
	  // Enable some language-neutral replacements + quotes beautification
	  typographer:  false,
	
	  // Double + single quotes replacement pairs, when typographer enabled,
	  // and smartquotes on. Set doubles to '«»' for Russian, '„“' for German.
	  quotes: '“”‘’',
	
	  // Highlighter function. Should return escaped HTML,
	  // or '' if input not changed
	  highlight: function (str, lang) {
	    if (lang && hljs.getLanguage(lang)) {
	      try {
	        return hljs.highlight(lang, str).value;
	      } catch (__) {}
	    }
	
	    try {
	      return hljs.highlightAuto(str).value;
	    } catch (__) {}
	
	    return ''; // use external default escaping
	  }
});

class MarkDown extends Component{
    constructor(props) {
        super(props);
        this.state = {
        	outputValue : ""
        };
    }
	handleChange(e) {
		var source = document.getElementsByClassName("source")[0].value;
	    document.getElementsByClassName("result-html")[0].innerHtml = md.render(source);
	    try {
	    	if (source) {
	    		permalink.href = '#md64=' + window.btoa(encodeURI(JSON.stringify({
	    			source: source,
	    			defaults: _.omit(defaults, 'highlight')
	        })));
	    	} else {
	    	  permalink.href = '';
	    	}	
	    } catch (__) {
	      permalink.href = '';
	    }
		this.setState({outputValue: md.render(source)});
	}
	render() {
		return (
			<div className="container full-height">
		      	<div className="row full-height">
		      		<div className="col-xs-6 full-height">
		      			<div className="demo-control"><a href="#" className="source-clear">clear</a><a id="permalink" href="./" title="Share this snippet as link"><strong>permalink</strong></a></div>
		      				<textarea className="source full-height" ref="textarea" onChange={this.handleChange.bind(this)}>
		      				</textarea>
	      			</div>
	      				<section className="col-xs-6 full-height">
		      				<div className="demo-control"><a href="#" data-result-as="html">html</a><a href="#" data-result-as="src">source</a><a href="#" data-result-as="debug">debug</a></div>
		      				<div className="result-html full-height" dangerouslySetInnerHTML={{__html: this.state.outputValue}}></div>
		      				<pre className="result-src full-height"><code className="result-src-content full-height"></code></pre>
		      				<pre className="result-debug full-height"><code className="result-debug-content full-height"></code></pre>
	      				</section>
		        </div>
		      </div>
		);
	}
}

module.exports = MarkDown;