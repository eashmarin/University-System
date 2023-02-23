import React from "react";
import NavBar from "./NavBar";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import Navbar from "react-bootstrap/Navbar";

function Header(props) {
    return (
        <Navbar variant="dark" bg="light" expand="sm">
            <DropdownButton id="students-btn" title="Students" variant="light">
                <Dropdown.Item>1</Dropdown.Item>
                <Dropdown.Item>2</Dropdown.Item>
                <Dropdown.Item>3</Dropdown.Item>
            </DropdownButton>
            <DropdownButton title="Teachers" variant="light">
                <Dropdown.Item>1</Dropdown.Item>
                <Dropdown.Item>2</Dropdown.Item>
                <Dropdown.Item>3</Dropdown.Item>
            </DropdownButton>
            <DropdownButton title="Dissertations" variant="light">
                <Dropdown.Item>1</Dropdown.Item>
                <Dropdown.Item>2</Dropdown.Item>
                <Dropdown.Item>3</Dropdown.Item>
            </DropdownButton>
            <DropdownButton title="Departments" variant="light">
                <Dropdown.Item>1</Dropdown.Item>
                <Dropdown.Item>2</Dropdown.Item>
                <Dropdown.Item>3</Dropdown.Item>
            </DropdownButton>
        </Navbar>
    );
}

export default Header;