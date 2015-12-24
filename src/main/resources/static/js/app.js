var React = require("react");
var $ = require("jquery");

var App = React.createClass({
    render() {
        return (
            <h1>Hello {this.state.name}</h1>
        );
    },
    getInitialState() {
        return {name: "World"};
    }

});

React.render(
    <App/>,
    document.getElementById('app')
);