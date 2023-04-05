import React from "react";
function StudentList(props) {
    return (
            Array.from(props.students).map(student => {
                    return (
                        <tr key={student.id}>
                            <td>{student.name}</td>
                            <td>{student.gender}</td>
                            <td>{new Date(student.birthDate).toISOString().slice(0, 10)}</td>
                            <td>{student.childNum}</td>
                            <td>{student.educationLevel}</td>
                            <td>{student.scholarship}</td>
                        </tr>
                    )
                }
            )
        );
}

export default StudentList;