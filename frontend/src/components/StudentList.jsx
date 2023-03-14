import React from "react";
function StudentList(props) {
    return (
            Array.from(props.students).map(student => {
                    return (
                        <tr key={student.id}>
                            <td>{student.name}</td>
                            <td>{student.gender}</td>
                            <td>{student.group.name}</td>
                            <td>{student.educationLevel}</td>
                            <td>{student.scholarship}</td>
                        </tr>
                    )
                }
            )
        );
}

export default StudentList;