import React, {useEffect, useState} from 'react';
import {Button, Form, FormGroup} from "react-bootstrap";
import FacultyService from "../../API/FacultyService";
import StudentService from "../../API/StudentService";
import TextFormElement from "../form/TextFormElement";
import SelectFormElement from "../form/SelectFormElement";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Authenticator from "../Authenticator";
import {genders} from "../enum/genders";
import {educationLevels} from "../enum/educationLevels";

function NewStudentForm() {
    const [faculties, setFaculties] = useState([]);
    const [groups, setGroups] = useState([]);

    const [selectedName, setSelectedName] = useState();
    const [selectedFaculty, setSelectedFaculty] = useState("0");
    const [selectedGroup, setSelectedGroup] = useState();
    const [selectedGender, setSelectedGender] = useState("male");
    const [childNum, setChildNum] = useState("0");
    const [birthDate, setBirthDate] = useState(subYears(new Date(), 16))
    const [selectedEducationLevel, setSelectedEducationLevel] = useState("bachelor");
    const [selectedScholarship, setSelectedScholarship] = useState(0);

    useEffect(() => {
        FacultyService.getFaculty("")
            .then(response => {
                setFaculties(response);
                setSelectedFaculty(response.at(0).id);
                setSelectedGroup(Number(response.at(0).groups.at(0)));
            });
    }, []);

    useEffect(() => {
        const faculty = faculties.find(faculty => faculty.id === parseInt(selectedFaculty));
        console.log(faculty);
        if (faculty) {
            setGroups(faculty.groups);
        }

    }, [selectedFaculty]);


    function sendStudentData(e) {
        const student = JSON.stringify({
            "name": selectedName,
            "gender": selectedGender.toUpperCase(),
            "birthDate": birthDate,
            "childNum": childNum,
            "group": {
                "id": Number(selectedGroup)
            },
            "educationLevel": selectedEducationLevel.toUpperCase(),
            "scholarship": selectedScholarship
        });

        console.log(student);

        StudentService.addStudent(student)
    }

    function subYears(date, number) {
        return new Date(date.getFullYear() - number, date.getMonth(), date.getDay());
    }

    return (
        <>
            <Authenticator></Authenticator>
            <div className="custom-form">
                <Form>
                    <TextFormElement placeholder="Ivanov Ivan Ivanovich"
                                     onChange={(e) => setSelectedName(e.currentTarget.value)}>
                        Name
                    </TextFormElement>

                    <SelectFormElement elements={genders}
                                       onChange={(e) => setSelectedGender(e.currentTarget.options[e.currentTarget.selectedIndex].text)}>
                        Gender
                    </SelectFormElement>

                    <FormGroup>
                        <Form.Label>Birth Date</Form.Label>
                        <DatePicker className="form-select"
                                    showIcon
                                    selected={birthDate}
                                    onChange={(date) => setBirthDate(date)}
                                    maxDate={subYears(new Date(), 16)}
                        />

                    </FormGroup>

                    <TextFormElement placeholder="0"
                                     onChange={(e) => setChildNum(e.currentTarget.value)}>
                        Children number
                    </TextFormElement>

                    <SelectFormElement elements={faculties}
                                       onChange={(e) => setSelectedFaculty(e.currentTarget.options[e.currentTarget.selectedIndex].id)}>
                        Faculty
                    </SelectFormElement>

                    <SelectFormElement elements={groups}
                                       onChange={(e) => setSelectedGroup(e.currentTarget.options[e.currentTarget.selectedIndex].id)}>
                        Group
                    </SelectFormElement>

                    <SelectFormElement elements={educationLevels}
                                       onChange={(e) => setSelectedEducationLevel(e.currentTarget.options[e.currentTarget.selectedIndex].text)}>
                        Education Levels
                    </SelectFormElement>
                    <TextFormElement placeholder="0" onChange={(e) => setSelectedScholarship(e.currentTarget.value)}>
                        Scholarship
                    </TextFormElement>

                    <Button className="d-block w-75 m-auto mt-3 mb-3" variant="outline-dark"
                            onClick={(e) => sendStudentData(e)}>Add</Button>
                </Form>
            </div>
        </>
    );
};

export default NewStudentForm;