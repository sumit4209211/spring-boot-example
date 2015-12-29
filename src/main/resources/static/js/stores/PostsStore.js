'use strict';

var Dispatcher = require('../core/Dispatcher');
var ActionConstants = require('../constants/ActionConstants');
var EventEmitter = require('events').EventEmitter;
var assign = require('object-assign');

var CHANGE_EVENT = 'change',
    _posts = [] , _post;;

/**
 * Set the values for categories that will be used
 * with components.
 */
function setPosts(posts) {
	_posts = posts;
}

function setPost(post) {
	_post = post;
}

var PostsStore = assign({}, EventEmitter.prototype, {

  emitChange: function () {
    this.emit(CHANGE_EVENT);
  },

  addChangeListener: function (callback) {
    this.on(CHANGE_EVENT, callback);
  },

  removeChangeListener: function (callback) {
    this.removeListener(CHANGE_EVENT, callback);
  },

  getPosts: function () {
    return _posts;
  },
  
  getPost: function (id) {
	 for(var i = 0; i < _posts.length; i++){
		 if(_posts[i].id == id){
			 return _posts[i];
		 }
	 }
	 return {};
  }
});

PostsStore.dispatchToken = Dispatcher.register(function (payload) {
  var action = payload.action;

  switch (action.actionType) {
    case ActionConstants.RECEIVE_POSTS:
      setPosts(action.posts.content);
      break;
    default:
      return true;
  }

  PostsStore.emitChange();

  return true;
});

module.exports = PostsStore;
