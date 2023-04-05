import React, {useState} from 'react';
import {Toast} from "react-bootstrap";

const ResponseNotification = () => {
    const [show, setShow] = useState(true);

    return (
        <Toast show={show}
               onClick={() => setShow(true)}
               onClose={() => setShow(false)}
               bg="success"
               delay={3000}
               autohide
               className="m-auto rounded-2 w-75"
        >
            <Toast.Header/>
            <Toast.Body>200</Toast.Body>
        </Toast>
    );
};

export default ResponseNotification;