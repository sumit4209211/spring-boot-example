/**
 * Created by wonwoo on 15. 12. 27..
 */
import React, { Component } from 'react';

class RightNav extends Component{

    render () {
        return (
            <div className="col-md-4">
                <Search />
                <Category />
            </div>
        );
    }
}

class Search extends Component {
    render(){
        return (
            <div className="well">
                <h4>Blog Search</h4>
                <div className="input-group">
                    <input type="text" className="form-control" />
                    <span className="input-group-btn">
                        <button className="btn btn-default" type="button">
                            <span className="glyphicon glyphicon-search"></span>
                        </button>
                    </span>
                </div>
            </div>
        )
    }
}

class Category extends Component {
    render(){
        return (
            <div className="well">
                <h4>Blog Categories</h4>
                <div className="row">
                    <div className="col-lg-6">
                        <ul className="list-unstyled">
                            <li><a href="#">Category Name</a>
                            </li>
                            <li><a href="#">Category Name</a>
                            </li>
                            <li><a href="#">Category Name</a>
                            </li>
                            <li><a href="#">Category Name</a>
                            </li>
                        </ul>
                    </div>
                    <div className="col-lg-6">
                        <ul className="list-unstyled">
                            <li><a href="#">Category Name</a>
                            </li>
                            <li><a href="#">Category Name</a>
                            </li>
                            <li><a href="#">Category Name</a>
                            </li>
                            <li><a href="#">Category Name</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}


module.exports = RightNav;