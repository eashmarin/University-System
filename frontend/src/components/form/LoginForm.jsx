import React, {useState} from 'react';
import {Button, Form} from "react-bootstrap";
import TextFormElement from "./TextFormElement";
import AuthorizationService from "../../API/AuthorizationService";

function LoginForm() {
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");

    function authorize(e) {
        e.preventDefault();
        const resp = AuthorizationService.authorize(login, password);
        resp.then((r) => console.log(r.data));
        console.log(resp);
    }

    return (
        <div className="custom-form">
            <Form>
                <TextFormElement onChange={(e) => setLogin(e.currentTarget.value)}>Login</TextFormElement>
                <TextFormElement type="password" onChange={(e) => setPassword(e.currentTarget.value)}>Password</TextFormElement>
                <Button className="d-block w-75 m-auto mt-3 mb-3" variant="outline-dark" onClick={(e) => authorize(e)}>Login</Button>
            </Form>
        </div>
    );
};

export default LoginForm;