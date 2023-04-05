import React from "react";
import {Accordion} from "react-bootstrap";
import GroupButtonList from "./GroupButtonList";

function FacultyAccordionItem(props) {
    return (
        <Accordion.Item eventKey={props.id}>
            <Accordion.Header>{props.children}</Accordion.Header>
            <Accordion.Body className="d-flex flex-column">
                <GroupButtonList groups={props.groups} courses={[1, 2, 3, 4]}/>
            </Accordion.Body>
        </Accordion.Item>
    );
}

export default FacultyAccordionItem;