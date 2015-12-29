/**
 * Created by wonwoo on 15. 12. 27..
 */
import React, { PropTypes, Component } from 'react';
import { Router, Route ,IndexRoute} from 'react-router';
import BlogMain from './BlogMain';
import Contents from './Contents';
import Content from './Content';

/*
 *  Base App Component for Redirect Handling
 */
class Main extends Component {
	static propTypes : {
		history: PropTypes.object.isRequired
	}

	render() {
	   const { history } = this.props;
	   return (
	      <Router history={history}>
	        <Route name="blogMain" path="/" component={BlogMain}>
		        <IndexRoute component={Contents} />
	        	<Route path="content/:id" component={Content} />
	        </Route>
	      </Router>
	   );
   }

}

class About extends Component{
  render() {
    return <h3>About</h3>
  }
}
module.exports = Main;