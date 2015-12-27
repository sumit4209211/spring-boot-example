/**
 * Created by wonwoo on 15. 12. 27..
 */
var React = require('react');
var HeadNav = require('./HeadNav');
var Footer = require('./Footer');
var LeftNav = require('./LeftNav');
var Contents = require('./Contents');


class BlogMain extends React.Component{

    render () {
        return (
            <div>
                <HeadNav url=""/>
                <div className="container">
                    <div className="container">
                        <div className="row">
                            <Contents url="/post"/>
                            <LeftNav />
                        </div>
                        <Footer />
                    </div>
                </div>
            </div>
        );
    }
}

module.exports = BlogMain;
