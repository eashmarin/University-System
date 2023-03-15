import React from 'react';
import {Button, Form, FormGroup, FormLabel, FormText} from "react-bootstrap";

function StudentForm() {
    return (
        <div className="w-25 m-auto mt-3 border rounded-3 d-flex justify-content-center" style={{backgroundColor: "#f5f5f5"}}>
            <Form>
                <Form.Group className="mt-3 mb-3">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="text" placeholder="Ivanov Ivan Ivanovich"/>
                </Form.Group>
                <FormGroup className="mt-3 mb-3">
                    <FormLabel>Faculty</FormLabel>
                    <Form.Select>
                        <option>123</option>
                    </Form.Select>
                </FormGroup>
                <FormGroup className="mt-3 mb-3">
                    <FormLabel>Group</FormLabel>
                    <Form.Select>
                        <option>123</option>
                    </Form.Select>
                </FormGroup>
                <FormGroup className="mt-3 mb-3">
                    <FormLabel>Gender</FormLabel>
                    <Form.Select>
                        <option>male</option>
                        <option>female</option>
                    </Form.Select>
                </FormGroup>
                <FormGroup className="mt-3 mb-3">
                    <FormLabel>Education Level</FormLabel>
                    <Form.Select>
                        <option>Bachelor</option>
                    </Form.Select>
                </FormGroup>
                <FormGroup className="mt-3 mb-3">
                    <FormLabel>Scholarship</FormLabel>
                    <Form.Control type="text"/>
                </FormGroup>


                <Button className="d-block w-75 m-auto mt-3 mb-3" variant="outline-dark" >Add</Button>
            </Form>
        </div>
    );
};

export default StudentForm;