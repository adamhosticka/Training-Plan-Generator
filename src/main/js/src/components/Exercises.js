import Table from "react-bootstrap/Table";
import {Component} from "react";
import LoadingScreen from "./LoadingScreen";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

import styleMuscleGroups from "../helper/StyleMuscleGroups";
import apiUri from '../helper/constants';

class Exercises extends Component{
    constructor(props) {
        super(props);
        this.state = {
            loading: true,
            exercises: [],
        };
    }

    async componentDidMount() {
        let exercisesResponse = await axios.get(apiUri + '/exercises');
        for(let exercise of exercisesResponse.data._embedded.exercises) {
            const catRes = await axios.get(exercise._links.category.href);
            exercise.category = catRes.data;
            const musRes = await axios.get(exercise._links.muscleGroups.href);
            exercise.muscleGroups = musRes.data._embedded.muscleGroups;
        }
        this.setState({
            loading: false,
            exercises: exercisesResponse.data._embedded.exercises,
        });
    }
    render() {
        if(this.state.loading === true) {
            return (<LoadingScreen/>)
        }
        const exercises = this.state.exercises.map(exercise =>
            <Exercise key={exercise._links.self.href} exercise={exercise}/>
        );
        return (
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Muscle groups</th>
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
                <td>{this.props.exercise.category.name}</td>
                <td>{styleMuscleGroups(this.props.exercise.muscleGroups)}</td>
            </tr>
        )
    }
}

export default Exercises;
