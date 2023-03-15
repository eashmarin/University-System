import "./css/App.css"
import {useRef} from "react";
import Header from "./components/Header";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import StudentTable from "./components/StudentTable";
import FacultyAccordionItem from "./components/FacultyAccordionItem";
import FacultiesList from "./components/FacultiesList";
import StudentForm from "./components/StudentForm";


function App() {
    return (
        <BrowserRouter>
            <div className="App">
                <Header/>
            </div>
            <Routes>
                <Route path="/students" element={<FacultiesList/>}/>
                <Route path="/students/new" element={<StudentForm/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;