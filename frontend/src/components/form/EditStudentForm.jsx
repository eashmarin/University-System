import React, {useEffect, useState} from 'react';
import {Button, Form, FormGroup} from "react-bootstrap";
import FacultyService from "../../API/FacultyService";
import StudentService from "../../API/StudentService";
import TextFormElement from "../form/TextFormElement";
import SelectFormElement from "../form/SelectFormElement";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import GroupService from "../../API/GroupService";
import {genders} from "../enum/genders";
import {educationLevels} from "../enum/educationLevels";

function EditStudentForm() {
    const [faculties, setFaculties] = useState([]);
    const [groups, setGroups] = useState([]);
    const [students, setStudents] = useState([]);

    const [facultyFilter, setFacultyFilter] = useState();
    const [groupFilter, setGroupFilter] = useState();
    const [studentFilter, setStudentFilter] = useState();

    const [selectedFaculty, setSelectedFaculty] = useState("0");
    const [selectedGroup, setSelectedGroup] = useState();
    const [selectedName, setSelectedName] = useState();
    const [selectedGender, setSelectedGender] = useState("male");
    const [childNum, setChildNum] = useState("0");
    const [birthDate, setBirthDate] = useState(subYears(new Date(), 16))
    const [selectedEducationLevel, setSelectedEducationLevel] = useState("bachelor");
    const [selectedScholarship, setSelectedScholarship] = useState(0);

    useEffect(() => {
        FacultyService.getFaculty("")
            .then(response => {
                setFaculties(response);
                setFacultyFilter(response.at(0));
            });
    }, []);

    useEffect(() => {
        const faculty = faculties.find(faculty => faculty.id === parseInt(facultyFilter.id));
        console.log(faculty);
        if (faculty) {
            setGroups(faculty.groups);
            setGroupFilter(faculty.groups.at(0));
        }

    }, [facultyFilter]);

    useEffect(() => {
        async function fetchData() {
            const groupMembers = await GroupService.getGroupList(groupFilter.id);
            setStudents(groupMembers);
            setStudentFilter(groupMembers[0]);
        };
        fetchData();
        console.log(students);
        setSelectedGroup(groupFilter);
    }, [groupFilter])

    useEffect(() => {
        const student = students.find(s => s.id === parseInt(studentFilter.id));
        if (studentFilter && student) {
            setSelectedGender(student.gender);
            setSelectedScholarship(student.scholarship);
            setSelectedEducationLevel(student.educationLevel);
            setChildNum(student.childNum);
            setBirthDate(new Date(student.birthDate).toISOString());
            console.log(student.birthDate);
        }
    }, [studentFilter])

    function sendStudentData(e) {
        const student = JSON.stringify({
            "name": selectedName,
            "gender": selectedGender,
            "birthDate": birthDate,
            "childNum": childNum,
            "group": {
                "id": Number(selectedGroup)
            },
            "educationLevel": selectedEducationLevel,
            "scholarship": selectedScholarship
        });

        StudentService.addStudent(student)
    }

    function subYears(date, number) {
        return new Date(date.getFullYear() - number, date.getMonth(), date.getDay());
    }

    return (
        <>
            <div className="custom-form ">
                <Form>
                    <SelectFormElement elements={faculties}
                                       onChange={(e) => setFacultyFilter(e.currentTarget.options[e.currentTarget.selectedIndex])}>
                        Faculty Filter
                    </SelectFormElement>

                    <SelectFormElement elements={groups}
                                       onChange={(e) => setGroupFilter(e.currentTarget.options[e.currentTarget.selectedIndex])}>
                        Group Filter
                    </SelectFormElement>

                    <SelectFormElement elements={students}
                                       onChange={(e) => setStudentFilter(e.currentTarget.options[e.currentTarget.selectedIndex])}>
                        Student Filter
                    </SelectFormElement>
                </Form>
            </div>
            <div className="custom-form">
                <Form>
                    <TextFormElement value={studentFilter ? studentFilter.name : ""}
                                     onChange={(e) => setSelectedName(e.currentTarget.value)}>
                        Name
                    </TextFormElement>

                    <SelectFormElement value={selectedGroup ? selectedGroup.text : ""}
                                       elements={groups}
                                       onChange={(e) => setSelectedGroup(e.currentTarget.options[e.currentTarget.selectedIndex].value)}>
                        Group
                    </SelectFormElement>

                    <SelectFormElement value={selectedGender}
                                       elements={genders}
                                       onChange={(e) => setSelectedGender(e.currentTarget.options[e.currentTarget.selectedIndex].value)}>
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

                    <SelectFormElement elements={educationLevels}
                                       onChange={(e) => setSelectedEducationLevel(e.currentTarget.options[e.currentTarget.selectedIndex].value)}>
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

export default EditStudentForm;