import React, {useEffect, useState} from "react";
import {ToggleButton} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Separator from "./Separator";
import GroupService from "../API/GroupService"
import StudentTable from "./StudentTable";
import {useFetching} from "../hooks/useFetching";
import Spinner from "react-bootstrap/Spinner";

function GroupButtonList(props) {
    const [radioValue, setRadioValue] = useState(props.groups[0] ? props.groups[0].id : null);
    const [students, setStudents] = useState(null);
    const [fetchStudents, areStudentsLoading] = useFetching(async () => {
        const groupList = await GroupService.getGroupList(radioValue);
        setStudents(groupList);
    })

    useEffect(() => {
        fetchStudents();
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
            {areStudentsLoading ?
                <div className="d-flex justify-content-center">
                    <Spinner/>
                </div>
                :
                <StudentTable students={students}></StudentTable>
            }

        </>
    );
}

export default GroupButtonList;