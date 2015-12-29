'use strict';

var Dispatcher = require('../core/Dispatcher');
var ActionConstants = require('../constants/ActionConstants');
var EventEmitter = require('events').EventEmitter;
var assign = require('object-assign');

var CHANGE_EVENT = 'change',
    _posts = [];

/**
 * Set the values for categories that will be used
 * with components.
 */
function setPosts(posts) {
	_posts = posts;
}

var PostsStore = assign({}, EventEmitter.prototype, {

  /**
   * Emits change event.
   */
  emitChange: function () {
    this.emit(CHANGE_EVENT);
  },

  /**
   * Adds a change listener.
   *
   * @param {function} callback Callback function.
   */
  addChangeListener: function (callback) {
    this.on(CHANGE_EVENT, callback);
  },

  /**
   * Removes a change listener.
   *
   * @param {function} callback Callback function.
   */
  removeChangeListener: function (callback) {
    this.removeListener(CHANGE_EVENT, callback);
  },

  /**
   * Return the value for categories.
   */
  getPosts: function () {
    return _posts;
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
