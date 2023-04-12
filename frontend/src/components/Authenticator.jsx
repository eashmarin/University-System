import React, {useState, useEffect} from 'react';
import {Navigate} from 'react-router-dom';
import {wait} from "@testing-library/user-event/dist/utils";

function Authenticator() {
    const [loggedIn, setLoggedIn] = useState(true);

    useEffect(() => {
        fetch('http://localhost:8080/api/check-auth', {
            credentials: "include",
        }).then(response => {
            if (response.status === 200) {
                setLoggedIn(true);
            }
            else {
               setLoggedIn(false);
            }
        }).catch(error => {
            setLoggedIn(false);
        });
    }, []);

    if (!loggedIn) {
        return <Navigate to="/login"/>;
    }
}

export default Authenticator;