import React from "react";
import {ToggleButton} from "react-bootstrap";
function GroupButton(props) {
    const group = props.group;
    return (
        <ToggleButton
            className="border-secondary"
            variant="light"
            key={group.id}
            value={group.id}
            id={"tbg-radio-" + group.id}
        >
            {group.name}
        </ToggleButton>
    );
}

export default GroupButton;