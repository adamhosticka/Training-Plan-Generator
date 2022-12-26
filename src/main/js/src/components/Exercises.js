import Table from "react-bootstrap/Table";
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
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    {exercises}
                </tbody>
            </Table>
        )
    }
}

class Exercise extends Component{
    render() {
        return (
            <tr>
                <td>{this.props.exercise.name}</td>
            </tr>
        )
    }
}

export default Exercises;
