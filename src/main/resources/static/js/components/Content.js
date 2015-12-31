/**
 * Created by wonwoo on 15. 12. 27..
 */
import React, { Component } from 'react';
import PostsStore from '../stores/PostsStore';
import PostsActionCreator from '../actions/PostsActionCreator';
import MarkDown from './MarkDown';

class Content extends Component{

    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this.state = this.getStateStore();
    }
    
    getStateStore(props){
    	const { id } = props ? props.params : this.props.params;
    	return { 
    		post: PostsStore.getPost(id)
    	}
    }
    
    componentWillMount() {
    	PostsStore.addChangeListener(this._onChange);
    }

    componentDidMount() {
    	
    }

    componentWillUnmount() {
    	PostsStore.removeChangeListener(this._onChange);
    }

    _onChange() {
    	var post = this.getStateStore();
    	this.setState(this.getStateStore());
    }
    
    render() {
		return (
    			<div className="col-md-8">
 					<h1>{this.state.post.title}</h1>
 				<p className="lead">
 					by <a href="#">{this.state.post.email}</a>
 				</p>
 				<hr>
 				</hr>
 				<p><span className="glyphicon glyphicon-time"></span> Posted on August 24, 2013 at 9:00 PM</p>

 				<hr>
 				</hr>

 				<img className="img-responsive" src="http://placehold.it/900x300" alt=""/>
 				<hr>
 					<p className="lead">{this.state.post.content}</p>
 				</hr>
 			</div>
        );
    }
}

module.exports = Content;
