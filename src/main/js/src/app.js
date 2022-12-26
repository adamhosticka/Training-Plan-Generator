import {Component} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from "react-bootstrap/Container";
import MuscleGroups from "./components/MuscleGroups";

class App extends Component {
    render() {
        return (
            <div>
                <h1>Ahoj</h1>
                <Container>
                    <MuscleGroups/>
                </Container>
            </div>
        )
    }
}

export default App;
