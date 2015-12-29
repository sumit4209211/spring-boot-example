'use strict';

var Dispatcher = require('../core/Dispatcher');
var ActionConstants = require('../constants/ActionConstants');
var Promise = require('es6-promise').Promise; // jshint ignore:line
var Api = require('../services/Api');

var ActionCreator = {

  getPosts: function () {
    Api
      .get('/post')
      .then(function (posts) {
        Dispatcher.handleViewAction({
          actionType: ActionConstants.RECEIVE_POSTS,
          posts: posts
        });        
      })
      .catch(function () {
        Dispatcher.handleViewAction({
          actionType: ActionConstants.RECEIVE_ERROR,
          error: 'There was a problem getting the Posts'
        });      
      });
   }
};

module.exports = ActionCreator;