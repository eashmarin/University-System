import React, {useEffect, useState} from "react";
import FacultyAccordionItem from "./FacultyAccordionItem";
import {Accordion} from "react-bootstrap";
import FacultyService from "../API/FacultyService";
import {useFetching} from "../hooks/useFetching";
import Spinner from 'react-bootstrap/Spinner'
import Authenticator from "./Authenticator";

function FacultiesList() {
    const [faculties, setFaculties] = useState();
    const [fetchFaculties, areFacultiesLoading] = useFetching(async () => {
        const faculties = await FacultyService.getFaculty("");
        setFaculties(faculties);
    })

    useEffect(() => {
        fetchFaculties();
    }, [])

    return (
        <Accordion className="w-50 mt-3 m-auto justify-content-center">
            <Authenticator></Authenticator>
            {areFacultiesLoading &&
                <div className="d-flex justify-content-center">
                    <Spinner animation="border" role="status"></Spinner>
                </div>
            }
            {faculties &&
                faculties.map((faculty) =>
                    <FacultyAccordionItem key={faculty.id} id={faculty.id}
                                          groups={faculty.groups}>{faculty.name}</FacultyAccordionItem>
                )
            }
        </Accordion>
    );
}

export default FacultiesList;