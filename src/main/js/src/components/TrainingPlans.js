import Table from "react-bootstrap/Table";
import {Component} from "react";
import {LinkContainer} from "react-router-bootstrap";
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
            console.log(response);
            this.setState({trainingPlans: response.data._embedded.trainingPlans.reverse()});
        });
    }
    render() {
        const trainingPlans = this.state.trainingPlans.map(trainingPlan =>
            <TrainingPlanRow key={trainingPlan._links.self.href} trainingPlan={trainingPlan}/>
        );
        return (
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Expected duration</th>
                    <th>Goal</th>
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

class TrainingPlanRow extends Component{
    render() {
        return (
            <LinkContainer style={{cursor: 'pointer'}} to={"/training-plans/" + this.props.trainingPlan.id}>
                <tr>
                    <td>{this.props.trainingPlan.name}</td>
                    <td>{this.props.trainingPlan.timeToTrain}</td>
                    <td>{this.props.trainingPlan.goal}</td>
                    <td>{new Date(this.props.trainingPlan.createdAt).toLocaleString('cs-CZ', {
                        day: "2-digit",
                        month: "2-digit",
                        year: "numeric",
                        hour: "2-digit",
                        minute: '2-digit'
                    })}</td>
                </tr>
            </LinkContainer>
        )
    }
}

export default TrainingPlans;
