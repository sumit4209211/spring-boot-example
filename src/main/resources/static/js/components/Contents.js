/**
 * Created by wonwoo on 15. 12. 27..
 */
var React = require('react');
var $ = require('jquery');

class Contents extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            posts : [],
        };
    }

    componentDidMount() {
        $.get(this.props.url, function(result) {
            var content = result.content;
            console.log(content);
            this.setState({
                posts: content
            });
        }.bind(this));
    }
    render() {
        return (
            <ContentsPost data={this.state.posts}/>
        );
    }
}

class ContentsPost extends Contents{
    render(){
        var PostNode = this.props.data.map(function (post) {
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
