import "./css/App.css"
import {useRef} from "react";
import Header from "./components/Header";


function App() {
    const dropBlock = useRef(null);

    return (
        <div className="App">
            <Header/>
        </div>
    );
}

export default App;
