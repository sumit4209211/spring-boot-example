
var Accounts = React.createClass({
	  getInitialState: function() {
	    return {
	    	accounts : [],
	    };
  },

  componentDidMount: function() {
    $.get(this.props.source, function(result) {
      var accounts = result;
      	console.log(this.state.accounts)
//      accounts.content.map(function(item, i){
//    	  console.log(item);
//      });
	  this.setState({
		  accounts: accounts.content
	  });
    }.bind(this));
  },

  render: function() {
	  return (
	      <div>
	        {this.state.accounts.map(function(item, i) {
	          return (
	            <div>{item}</div>
	          );
	        }, this)}
	      </div>
	  );
   }
});

ReactDOM.render(
  <Accounts source="/accounts" />, document.body
);
	