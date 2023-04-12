import "./css/App.css"
import "./css/custom.css"
import Header from "./components/Header";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import FacultiesList from "./components/FacultiesList";
import NewStudentForm from "./components/form/NewStudentForm";
import NewGroupForm from "./components/form/NewGroupForm";
import React from "react";
import EditStudentForm from "./components/form/EditStudentForm";
import LoginForm from "./components/form/LoginForm";


function App() {
    return (
        <BrowserRouter>
            <div className="App">
                <Header/>
            </div>
            <Routes>
                <Route path="/login" element={<LoginForm/>}/>
                <Route path="/students" element={<FacultiesList/>}/>
                <Route path="/students/new" element={<NewStudentForm/>}/>
                <Route path="/students/edit" element={<EditStudentForm/>}/>
                <Route path="/groups/new" element={<NewGroupForm/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;