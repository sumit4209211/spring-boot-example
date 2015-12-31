/**
 * Created by wonwoo on 15. 12. 27..
 */
import React, { Component } from 'react';
import PostsStore from '../stores/PostsStore';
import PostsActionCreator from '../actions/PostsActionCreator';
import MarkDown from './MarkDown';


class NewContent extends Component{

    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
//        this.state = this.getStateStore();
    }
    
    getStateStore(props){
    	const { id } = props ? props.params : this.props.params;
    	return { 
    		post: PostsStore.getPost(id)
    	}
    }
    
    componentWillMount() {
//    	PostsStore.addChangeListener(this._onChange);
    }

    componentDidMount() {
    	
    }

    componentWillUnmount() {
//    	PostsStore.removeChangeListener(this._onChange);
    }

    _onChange() {
//    	var post = this.getStateStore();
//    	this.setState(this.getStateStore());
    }
    
    render() {
		return (
			<div className="col-md-8">
				<MarkDown />
 			</div>
        );
    }
}

module.exports = NewContent;
