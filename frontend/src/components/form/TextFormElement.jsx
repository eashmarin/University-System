import React from 'react';
import {Form} from "react-bootstrap";

function TextFormElement ({label, children, ...args}) {
    return (
        <Form.Group className="mt-3 mb-3">
            <Form.Label>{children}</Form.Label>
            <Form.Control type="text" {...args} />
        </Form.Group>
    );
};

export default TextFormElement;