/**
 * Created by wonwoo on 15. 12. 27..
 */
var React = require('react');
var {RouteHandler} = require('react-router');


/*
 *  Base App Component for Redirect Handling
 */
class Main extends React.Component {
    /*
     *  Return JSX representation of component view
     */
    render () {
        return <RouteHandler/>;
    }
}

module.exports = Main;