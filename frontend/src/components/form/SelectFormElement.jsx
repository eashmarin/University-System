import React from 'react';
import {Form, FormGroup, FormLabel} from "react-bootstrap";

function SelectFormElement({children, elements, ...props}) {
    return (
        <FormGroup className="mt-3 mb-3">
            <FormLabel>{children}</FormLabel>
            <Form.Select {...props}>
                {elements && elements.map(element =>
                    <option key={element.id} id={element.id}>{element.name}</option>
                )}
            </Form.Select>
        </FormGroup>

    );
};

export default SelectFormElement;