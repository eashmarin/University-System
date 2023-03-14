import React, {useEffect, useState} from "react";
import FacultyAccordionItem from "./FacultyAccordionItem";
import {Accordion} from "react-bootstrap";
import FacultyService from "../API/FacultyService";

function FacultiesList() {
    const [faculties, setFaculties] = useState();

    useEffect(() =>
        async function () {
            const faculties = await FacultyService.getFaculty("");
            setFaculties(faculties);
        }, [])

    return (
        <Accordion className="w-50 mt-3 m-auto">
            {faculties &&
                faculties.map((faculty) =>
                    <FacultyAccordionItem key={faculty.id} id={faculty.id} groups={faculty.groups}>{faculty.name}</FacultyAccordionItem>
                )
            }
        </Accordion>
    );
}

export default FacultiesList;