/**
 * Created by wonwoo on 15. 12. 27..
 */
var React = require('react');

class Footer extends React.Component{

    render () {
        return (
            <footer>
                <div className="row">
                    <div className="col-lg-12">
                        <p>Copyright &copy; wonwoo 2015</p>
                    </div>
                </div>
            </footer>
        );
    }
}

module.exports = Footer;
