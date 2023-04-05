import Table from 'react-bootstrap/Table';
import Container from "react-bootstrap/Container";
import StudentList from "./StudentList";
import React from "react";

function StudentTable(props) {
    /*const headerStyle = () => {
        return { width: '80px', textAlign: 'center'};
    }*/

    return (
        <>
            {props.students && props.students.length > 0 ?
                <Container className="d-flex justify-content-center">
                    <Table className="w-100 mt-3 justify-content-center" striped bordered>
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Birthday</th>
                            <th>Children number</th>
                            <th>Education level</th>
                            <th>Scholarship</th>
                        </tr>
                        </thead>
                        <tbody>
                        <StudentList students={props.students}/>
                        </tbody>
                    </Table>
                </Container>
                : null
            }
        </>
    );
}

export default StudentTable;