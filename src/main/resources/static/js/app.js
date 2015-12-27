var React = require("react");
var Router = require('react-router');
var Main = require('./components/Main');
var BlogMain = require('./components/BlogMain');
var $ = require("jquery");

var Route = Router.Route;
var DefaultRoute = Router.DefaultRoute;

var routes = (
    <Route handler={Main}>
        <DefaultRoute name="blogMain" handler={BlogMain} />
    </Route>
);

var router = Router.create({ routes: routes });

router.run((Root) => {
    React.render(<Root/>, document.getElementById('app'));
});