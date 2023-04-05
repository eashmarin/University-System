import React from "react";
import Dropdown from "react-bootstrap/Dropdown";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import Container from "react-bootstrap/Container";

function Header() {
    return (
        <Navbar className="py-3" variant="dark" bg="light" expand="sm">
            <Container >
                <NavDropdown title="Students" variant="light">
                    <Dropdown.Item href="/students">List</Dropdown.Item>
                    <Dropdown.Item href="/students/new">New Student</Dropdown.Item>
                    <Dropdown.Item href="/students/edit">Edit Student</Dropdown.Item>
                    <Dropdown.Item href="/groups/new">New Group</Dropdown.Item>
                </NavDropdown>
                <NavDropdown title="Teachers" variant="light">
                    <Dropdown.Item>1</Dropdown.Item>
                    <Dropdown.Item>2</Dropdown.Item>
                    <Dropdown.Item>3</Dropdown.Item>
                </NavDropdown>
                <NavDropdown title="Dissertations" variant="light">
                    <Dropdown.Item>1</Dropdown.Item>
                    <Dropdown.Item>2</Dropdown.Item>
                    <Dropdown.Item>3</Dropdown.Item>
                </NavDropdown>
                <NavDropdown title="Departments" variant="light">
                    <Dropdown.Item>1</Dropdown.Item>
                    <Dropdown.Item>2</Dropdown.Item>
                    <Dropdown.Item>3</Dropdown.Item>
                </NavDropdown>
            </Container>
        </Navbar>
    );
}

export default Header;