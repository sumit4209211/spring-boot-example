/**
 * Created by wonwoo on 15. 12. 27..
 */
import React, { Component } from 'react';
import HeadNav from './HeadNav';
import Footer from './Footer';
import RightNav from './RightNav';
import Contents from './Contents';


class BlogMain extends Component{
    render () {
        return (
            <div>
                <HeadNav />
                <div className="container">
                    <div className="container">
                        <div className="row">
                        	{this.props.children}
                            <RightNav />
                        </div>
                        <Footer />
                    </div>
                </div>
            </div>
        );
    }
}

module.exports = BlogMain;
