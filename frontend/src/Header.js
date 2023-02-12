import React from "react";

class Header extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="header-block">
                <div className="header-nav-element">
                    Students
                </div>
                <div className="header-nav-element">
                    Teachers
                </div>
                <div className="header-nav-element">
                    Departments
                </div>
                <div className="header-nav-element">
                    Dissertations
                </div>
                <div className="header-nav-element">
                    Curriculum
                </div>
            </div>
        )
    }
}

export default Header;