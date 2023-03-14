import React from "react";

function Separator(props) {
    return (
        <>
            <label className="display-6 mx-2">{props.title}</label>
            <hr className="m-0"/>
        </>
    );

}

export default Separator;