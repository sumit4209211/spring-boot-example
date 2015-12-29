/**
 * Created by wonwoo on 15. 12. 27..
 */
var React = require('react');
var PostsStore = require('../stores/PostsStore');
var PostsActionCreator = require('../actions/PostsActionCreator');

class Contents extends React.Component{

    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this.state = {
            posts : []
        };
    }
    
    componentWillMount() {
    	PostsStore.addChangeListener(this._onChange);
    }

    componentDidMount() {
    	PostsActionCreator.getPosts();
    }

    componentWillUnmount() {
    	PostsStore.removeChangeListener(this._onChange);
    }

    _onChange() {
        this.setState({
          posts: PostsStore.getPosts()
        });
    }
    
    render() {
    	if (this.state.posts) {
    		var PostNode = this.state.posts.map(function (post) {
                return (
                    <div>
                        <h2>
                            {post.title}
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
    	}
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
}

module.exports = Contents;
