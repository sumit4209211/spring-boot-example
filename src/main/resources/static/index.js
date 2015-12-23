const {Router, Route, Link} = ReactRouter;

var HeadNav = React.createClass({
	render : function(){
		return (
			<nav className="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div className="container">
					<div className="navbar-header">
						<button type="button" className="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span className="sr-only">Toggle navigation</span>
							<span className="icon-bar"></span>
							<span className="icon-bar"></span>
							<span className="icon-bar"></span>
						</button>
						<a className="navbar-brand" href="#">Start Bootstrap</a>
					</div>
					<div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul className="nav navbar-nav">
							<li>
								<a href="#">About</a>
							</li>
							<li>
								<a href="#">Services</a>
							</li>
							<li>
								<a href="#">Contact</a>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		)
	}
});

var Content = React.createClass({
	getInitialState: function() {
		return {
			posts : [],
		};
	},
	componentDidMount: function() {
		$.get(this.props.url, function(result) {
			var content = result.content;
			this.setState({
				posts: content
			});
		}.bind(this));
	},
	render : function(){
		return (
			<ContentPost data={this.state.posts} />
		);
	}
});

var ContentPost = React.createClass({
	render :function(){
		var PostNode = this.props.data.map(function (post) {
			return (
				<div>
					<h2>
						<Link to="/about">{post.title}</Link>
					</h2>
					<p className="lead">
						by <a href="index.php">{post.email}</a>
					</p>
					<p><span className="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:00 PM</p>
					<hr>
	                    <img className="img-responsive" src="http://placehold.it/900x300" alt=""/>
	                </hr>
	                    <p>{post.content}</p>
                        <a className="btn btn-primary" href="#">Read More <span className="glyphicon glyphicon-chevron-right"></span></a>
	                
                </div>
            )
		});
		return (
			<div className="col-md-8">
				<h1 className="page-header">
					Page Heading
				</h1>
				{PostNode}
				<hr>
					<ul className="pager">
		                <li className="previous">
		                	<a href="#">&larr; Older</a>
		                </li>
		                <li className="next">
		                	<a href="#">Newer &rarr;</a>
		                </li>
		            </ul>
	            </hr>
			</div>
		);
	}
});


var Search = React.createClass({
   render :function(){
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
});


var Category = React.createClass({
    render : function(){
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
})

var LeftNav = React.createClass({
    render : function(){
        return (
            <div className="col-md-4">
                <Search />
                <Category />
            </div>
        )
    }
});


var Footer = React.createClass({
   render : function(){
       return (
           <footer>
               <div className="row">
                   <div className="col-lg-12">
                       <p>Copyright &copy; wonwoo 2015</p>
                   </div>
               </div>
           </footer>
       )
   }
});

const Main = React.createClass({
	render : function(){
		return (
			<div>
				<HeadNav url=""/>
                <div className="container">
                    <div className="row">
                        <Content url="/post"/>
                        <LeftNav />
                    </div>
                    <Footer />
                </div>
                {this.props.children}
			</div>
		)
	}
});

const App = React.createClass({
	render : function(){
		return (
			<div>123</div>
		)
	}
});
const About = React.createClass({
	componentDidMount: function() {
		console.log(this.props.router);
	},
	render : function(){
		return (
			<div>1255555555555553</div>
		)
	}
});
const Users = React.createClass({
	render : function(){
		return (
			<div>12zzzzzzzzzzzzzz3</div>
		)
	}
})

ReactDOM.render((
  <Router>
    <Route path="/" component={Main}>
      <Route path="about" component={About} />
      <Route path="inbox" component={Users}>
      </Route>
    </Route>
  </Router>
), document.body)
//
//ReactDOM.render((
//	<Router>
//		<Route path="/" component={App}>
//			<Route path="content" component={About} />
//			<Route path="login" component={Users} />
//		</Route>
//	</Router>
//), document.body);

//ReactDOM.render((
//	<div>
//		<Route path="/" component={App} />
//		<Route path="about" component={About} />
//		<Route path="users" component={Users} />
//		<Route path="*" component={App} />
//	</div>
//	), document.body
//);


//var Application = React.createClass({
//render : function(){
//	return (
//		<div>
//		 
//	  </div>
//  )
//}
//});

//var routes = (
//<Route path="/" handler={Main}>
//<Route path="comments" handler={App}/>
//</Route>
//);
//
//ReactRouter.run(routes, function (Handler) {
//React.render(<Handler/>, document.body);
//});




//ReactDOM.render(
//	<HeadNav url="/accounts" />, document.body
//);


//
//var Accounts = React.createClass({
//  getInitialState: function() {
//	    return {
//	    	accounts : [],
//	    };
//  },
//
//  componentDidMount: function() {
//    $.get(this.props.url, function(result) {
//      var accounts = result;
//      	console.log(this.state.accounts)
//	  this.setState({
//		  accounts: accounts.content
//	  });
//    }.bind(this));
//  },
//
//  render: function() {
//	  return (
//	      <div>
//	        <AccountList data={this.state.accounts}/>
//	      </div>
//	  );
//   }
//});
//
//var AccountList = React.createClass({
//		render: function() {
//			var AccountsNode = this.props.data.map(function (account) {
//				return (
//				<div>
//					{account.last_name}
//					{account.id}
//				</div>
//			);
//		});
//		return (
//			<div className="accountList">
//				{AccountsNode}
//			</div>
//		);
//	}
//});


