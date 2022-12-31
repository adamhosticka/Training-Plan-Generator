import Table from "react-bootstrap/Table";
import {Component} from "react";
import LoadingScreen from "./LoadingScreen";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

import apiUri from '../helper/constants';

class MuscleGroups extends Component{
    constructor(props) {
        super(props);
        this.state = {
            loading: true,
            muscleGroups: [],
        };
    }

    componentDidMount() {
        axios.get(apiUri + '/muscleGroups').then(response => {
            this.setState({
                loading: false,
                muscleGroups: response.data._embedded.muscleGroups,
            });
        });
    }
    render() {
        if(this.state.loading === true) {
            return (<LoadingScreen/>)
        }
        const muscleGroups = this.state.muscleGroups.map(muscleGroup =>
            <MuscleGroup key={muscleGroup._links.self.href} muscleGroup={muscleGroup}/>
        );
        return (
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Volume</th>
                    </tr>
                </thead>
                <tbody>
                    {muscleGroups}
                </tbody>
            </Table>
        )
    }
}

class MuscleGroup extends Component{
    render() {
        return (
            <tr>
                <td>{this.props.muscleGroup.name}</td>
                <td>{this.props.muscleGroup.volume}</td>
            </tr>
        )
    }
}

export default MuscleGroups;
