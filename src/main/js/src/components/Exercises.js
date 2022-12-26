import {Col, Row, Table} from "react-bootstrap";
import {Component} from "react";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

import apiUri from '../constants';

class Exercises extends Component{
    constructor(props) {
        super(props);
        this.state = {exercises: []};
    }

    componentDidMount() {
        axios.get(apiUri + '/exercises').then(response => {
            this.setState({exercises: response.data._embedded.exercises});
        });
    }
    render() {
        const exercises = this.state.exercises.map(exercise =>
            <Exercise key={exercise._links.self.href} exercise={exercise}/>
        );
        return (
            <Table>
                <Row>
                    <Col>Název cviku</Col>
                </Row>
                {exercises}
            </Table>
        )
    }
}

class Exercise extends Component{
    render() {
        return (
            <Row>
                <Col>{this.props.exercise.name}</Col>
            </Row>
        )
    }
}

export default Exercises;
