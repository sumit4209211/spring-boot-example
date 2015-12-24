const {Router, Route, Link, IndexRoute} = ReactRouter;

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
             <Contents url="/post"/>
		)
	}
});


var Contents = React.createClass({
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
			<ContentsPost data={this.state.posts} />
		);
	}
});

var ContentsPost = React.createClass({
	render :function(){
		var PostNode = this.props.data.map(function (post) {
			return (
				<div>
					<h2>
						<Link to={`/content/${post.id}`}> {post.title}</Link>
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
					<hr></hr>
				</div>
			)
		});
		return (
			<div className="col-md-8">
				<h1 className="page-header">
					Page Heading
				</h1>
				{PostNode}
				<ul className="pager">
					<li className="previous">
						<a href="#">&larr; Older</a>
					</li>
					<li className="next">
						<a href="#">Newer &rarr;</a>
					</li>
				</ul>
			</div>
		);
	}
});


var Content = React.createClass({
	getInitialState: function() {
		return {
			post : {},
		};
	},
	componentDidMount: function() {
		$.get("post/" + this.props.params.id, function(result) {
			var content = result;
			this.setState({
				post: content
			});
		}.bind(this));
	},
	render : function(){
		return (
			<ContentPost data={this.state.post} />
		);
	}
});

var ContentPost = React.createClass({
	render : function(){
		return (
			<div className="col-md-8">
				<h1>{this.props.data.title}</h1>
				<p className="lead">
					by <a href="#">{this.props.data.email}</a>
				</p>
				<hr>
				</hr>
				<p><span className="glyphicon glyphicon-time"></span> Posted on August 24, 2013 at 9:00 PM</p>

				<hr>
				</hr>

				<img className="img-responsive" src="http://placehold.it/900x300" alt=""/>
				<hr>
					<p className="lead">{this.props.data.content}</p>
				</hr>
				<Comments />
			</div>
		)
	}
});

var Comments = React.createClass({
	render : function(){
		return(
			<div>
				<CommentForm />
				<CommentBox />
			</div>
		)
	}
});

var CommentForm = React.createClass({
	render : function(){
		return (
			<div className="well">
				<h4>Leave a Comment:</h4>
				<form role="form">
					<div className="form-group">
						<textarea className="form-control" rows="3"></textarea>
					</div>
					<button type="submit" className="btn btn-primary">Submit</button>
				</form>
			</div>
		)
	}
});

var CommentBox = React.createClass({
	render : function(){
		return (
			<div className="media">
				<a className="pull-left" href="#">
					<img className="media-object" src="http://placehold.it/64x64" alt="" />
				</a>
				<div className="media-body">
					<h4 className="media-heading">Start Bootstrap
						<small>August 25, 2014 at 9:30 PM</small>
					</h4>
					Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
				</div>
			</div>
		)
	}
});


const App = React.createClass({
	render : function(){
		return (
			<div>
				<HeadNav url=""/>
				<div className="container">
					<div className="row">
						{this.props.children}
						<LeftNav />
					</div>
					<Footer />
				</div>
			</div>
		)
	}
});


var MarkDown = React.createClass({
    render: function(){
        return( 
		    <div>
		        <div dangerouslySetInnerHTML={{__html: "markdown.html" }} />
		    </div>
        )
    }
});

ReactDOM.render((
  <Router>
    <Route path="/" component={App}>
	  <IndexRoute component={Main}/>
      <Route path="content/:id" component={MarkDown} />
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


