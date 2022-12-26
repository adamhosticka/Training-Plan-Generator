import Table from "react-bootstrap/Table";
import {Component} from "react";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

import apiUri from '../constants';

class TrainingPlans extends Component{
    constructor(props) {
        super(props);
        this.state = {trainingPlans: []};
    }

    componentDidMount() {
        axios.get(apiUri + '/trainingPlans').then(response => {
            this.setState({trainingPlans: response.data._embedded.trainingPlans});
        });
    }
    render() {
        console.log(this.state);
        const trainingPlans = this.state.trainingPlans.map(trainingPlan =>
            <TrainingPlan key={trainingPlan._links.self.href} trainingPlan={trainingPlan}/>
        );
        return (
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Created at</th>
                </tr>
                </thead>
                <tbody>
                    {trainingPlans}
                </tbody>
            </Table>
        )
    }
}

class TrainingPlan extends Component{
    render() {
        return (
            <tr>
                <td>{new Date(this.props.trainingPlan.id).toLocaleString('cs-CZ', {
                    day: "2-digit",
                    month: "2-digit",
                    year: "numeric",
                    hour: "2-digit",
                    minute: '2-digit'
                })}</td>
            </tr>
        )
    }
}

export default TrainingPlans;
