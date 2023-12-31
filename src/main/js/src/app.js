import {Component} from "react";
import Container from "react-bootstrap/Container";
import {Col, Row} from "react-bootstrap";
import {Routes, Route} from "react-router-dom";

import Header from "./components/Header";
import TrainingPlanGenerator from "./components/TrainingPlanGenerator";
import TrainingPlans from "./components/TrainingPlans";
import Exercises from "./components/Exercises";
import MuscleGroups from "./components/MuscleGroups";
import NoMatch from "./components/NoMatch";

import 'bootstrap/dist/css/bootstrap.min.css';
import TrainingPlan from "./components/TrainingPlan";


class App extends Component {
    render() {
        return (
            <div>
                <Header/>
                <Container>
                    <Row>
                        <Col>
                            <Routes>
                                <Route index element={<TrainingPlanGenerator/>}/>
                                <Route path="training-plans" element={<TrainingPlans/>}/>
                                <Route path="training-plans/:planId" element={<TrainingPlan/>}/>
                                <Route path="exercises" element={<Exercises/>}/>
                                <Route path="muscle-groups" element={<MuscleGroups/>}/>
                                <Route path="*" element={<NoMatch/>}/>
                            </Routes>
                        </Col>
                    </Row>
                </Container>
            </div>
        )
    }
}

export default App;
