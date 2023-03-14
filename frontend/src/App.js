import "./css/App.css"
import {useRef} from "react";
import Header from "./components/Header";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import StudentTable from "./components/StudentTable";
import FacultyAccordionItem from "./components/FacultyAccordionItem";
import FacultiesList from "./components/FacultiesList";


function App() {
    return (
        <BrowserRouter>
            <div className="App">
                <Header/>
            </div>
            <Routes>
                <Route path="/students" element={<FacultiesList/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
