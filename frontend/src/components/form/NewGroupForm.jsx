import React, {useEffect, useState} from 'react';
import {Button, Card, Form, Modal, ModalBody, Toast} from "react-bootstrap";
import SelectFormElement from "./SelectFormElement";
import FacultyService from "../../API/FacultyService";
import TextFormElement from "./TextFormElement";
import StudentService from "../../API/StudentService";
import GroupService from "../../API/GroupService";
import ResponseNotification from "../ResponseNotification";
import Authenticator from "../Authenticator";

const NewGroupForm = () => {
    const [faculties, setFaculties] = useState([]);

    const [selectedName, setSelectedName] = useState()
    const [selectedFaculty, setSelectedFaculty] = useState("0");
    const [selectedCourse, setSelectedCourse] = useState("1");

    const [serverResponse, setServerResponse] = useState(null)

    useEffect(() => {
        FacultyService.getFaculty("")
            .then(response => {
                setFaculties(response);
                setSelectedFaculty(response.at(0).id);
            });
    }, []);

    async function sendGroupData(e) {
        const group = JSON.stringify({
            "name": selectedName,
            "faculty": selectedFaculty,
            "course": selectedCourse
        });

        GroupService.addGroup(group).then((resp) => {
            console.log(resp.status);
            setServerResponse(resp.status)
        });

    }

    return (
        <>
            <div className="custom-form">
                <Authenticator></Authenticator>
                <Form>
                    <TextFormElement onChange={(e) => setSelectedName(e.currentTarget.value)}>
                        Name
                    </TextFormElement>

                    <SelectFormElement elements={faculties}
                                       onChange={(e) => setSelectedFaculty(e.currentTarget.options[e.currentTarget.selectedIndex].id)}>
                        Faculty
                    </SelectFormElement>

                    <TextFormElement placeholder="1"
                                     onChange={(e) => setSelectedCourse(e.currentTarget.value)}>
                        Course
                    </TextFormElement>

                    <div className="d-flex">
                        <Button className="d-block w-75 m-auto mt-3 mb-3"
                                variant={serverResponse === null ? "outline-dark" :
                                    serverResponse === 200 ? "outline-success" : "outline-danger"}
                                onClick={(e) => sendGroupData(e)}>Add</Button>
                    </div>
                </Form>
            </div>

        </>
    );
};

export default NewGroupForm;