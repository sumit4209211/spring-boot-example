
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
	render : function(){
		return (
			<div className="col-md-8">

				<h1 className="page-header">
					Page Heading
				</h1>
				<h2>
					<a href="#">Blog Post Title</a>
				</h2>
				<p className="lead">
					by <a href="index.php">Start Bootstrap</a>
				</p>
				<p><span className="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:00 PM</p>
				<hr>
                    <img className="img-responsive" src="http://placehold.it/900x300" alt=""/>
                </hr>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolore, veritatis, tempora, necessitatibus inventore nisi quam quia repellat ut tempore laborum possimus eum dicta id animi corrupti debitis ipsum officiis rerum.</p>
                        <a className="btn btn-primary" href="#">Read More <span className="glyphicon glyphicon-chevron-right"></span></a>
                <hr>
                    <h2>
                        <a href="#">Blog Post Title</a>
                    </h2>
                    <p className="lead">
                        by <a href="index.php">Start Bootstrap</a>
                    </p>
                    <p>
                        <span className="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:45 PM</p>
                </hr>
                        <img className="img-responsive" src="http://placehold.it/900x300" alt=""/>
                <hr>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quibusdam, quasi, fugiat, asperiores harum voluptatum tenetur a possimus nesciunt quod accusamus saepe tempora ipsam distinctio minima dolorum perferendis labore impedit voluptates!</p>
                        <a class="btn btn-primary" href="#">Read More <span className="glyphicon glyphicon-chevron-right"></span></a>
                </hr>

                    <h2>
                        <a href="#">Blog Post Title</a>
                    </h2>
                    <p className="lead">
                        by <a href="index.php">Start Bootstrap</a>
                    </p>
                    <p>
                        <span className="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:45 PM</p>
                <hr>
                    <img className="img-responsive" src="http://placehold.it/900x300" alt=""/>
                </hr>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate, voluptates, voluptas dolore ipsam cumque quam veniam accusantium laudantium adipisci architecto itaque dicta aperiam maiores provident id incidunt autem. Magni, ratione.</p>
                    <a className="btn btn-primary" href="#">Read More <span className="glyphicon glyphicon-chevron-right"></span></a>
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

var Main = React.createClass({
	render : function(){
		return (
			<div>
				<HeadNav />
                <div className="container">
                    <div className="row">
                        <Content />
                        <LeftNav />
                    </div>
                    <Footer />
                </div>
			</div>
		)
	}
});


ReactDOM.render(
	<Main />, document.body
);

//ReactDOM.render(
//	<HeadNav url="/accounts" />, document.body
//);



var Accounts = React.createClass({
	  getInitialState: function() {
	    return {
	    	accounts : [],
	    };
  },

  componentDidMount: function() {
    $.get(this.props.url, function(result) {
      var accounts = result;
      	console.log(this.state.accounts)
	  this.setState({
		  accounts: accounts.content
	  });
    }.bind(this));
  },

  render: function() {
	  return (
	      <div>
	        <AccountList data={this.state.accounts}/>
	      </div>
	  );
   }
});

var AccountList = React.createClass({
		render: function() {
			var AccountsNode = this.props.data.map(function (account) {
				return (
				<div>
					{account.last_name}
					{account.id}
				</div>
			);
		});
		return (
			<div className="accountList">
				{AccountsNode}
			</div>
		);
	}
});


