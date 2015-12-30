/**
 * Created by wonwoo on 15. 12. 27..
 */
var React = require('react');
var Markdown = require('react-remarkable');
var Remarkable = require('remarkable');
var hljs = require('highlight.js') 


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

class Mark extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
        	inputValue : "",
        	outputValue : ""
        };
    }
	componentDidMount() {
		console.log(md.render('```java public static void main(){ }```'));
	}
	handleChange(e) {
		this.setState({outputValue: md.render(e.target.value)});
		console.log(md.render(e.target.value));
	}
  render() {
    return (
      <div>
      <textarea
	      onChange={this.handleChange}
	      ref="textarea"
	      value={this.state.inputValue} />
	      <div>
	      	{this.state.outputValue} 
	      </div>
      </div>
      
    );
  }
}

module.exports = Mark;