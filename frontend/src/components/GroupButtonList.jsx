import React, {useEffect, useState} from "react";
import {ToggleButton} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Separator from "./Separator";
import GroupService from "../API/GroupService"
import StudentTable from "./StudentTable";

function GroupButtonList(props) {
    const [radioValue, setRadioValue] = useState('1');
    const [students, setStudents] = useState(null);

    useEffect(() => {
        async function updateStudents()  {
            const groupList = await GroupService.getGroup(radioValue);
            setStudents(groupList);
            console.log(students);
        }
        updateStudents();
    }, [radioValue]);

    function getGroups(course) {
        return props.groups.filter(group => group.course === course);
    }

    async function handleChange(e) {
        setRadioValue(e.currentTarget.value);
    }

    return (
        <>
            {props.courses.map(course => {
                return (<Container className="d-flex flex-column flex-wrap gap-2" key={course}>
                    {getGroups(course).length > 0 ? <>
                        <Separator title={course}/>
                        <Container className="d-flex flex-wrap justify-content-start flex-row px-0 ">
                            {getGroups(course).map(group => {
                                return (<ToggleButton
                                    className="border-secondary mx-2"
                                    variant="light"
                                    key={group.id}
                                    value={group.id}
                                    id={"radio-" + group.id}
                                    type="radio"
                                    checked={Number(radioValue) === group.id}
                                    onChange={(e) => handleChange(e)}
                                >
                                    {group.name}
                                </ToggleButton>)
                            })}
                        </Container>
                    </> : null}
                </Container>)
            })}
            <StudentTable students={students}></StudentTable>
        </>
    );
}

export default GroupButtonList;