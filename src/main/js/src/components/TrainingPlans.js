import Table from "react-bootstrap/Table";
import {Component} from "react";
import {LinkContainer} from "react-router-bootstrap";
import {Alert, Button} from "react-bootstrap";
import {Trash3} from "react-bootstrap-icons";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

import apiUri from '../constants';

class TrainingPlans extends Component{
    constructor(props) {
        super(props);
        this.state = {
            trainingPlans: [],
            deletionStatus: null,
        };
    }

    componentDidMount() {
        axios.get(apiUri + '/trainingPlans').then(response => {
            this.setState({trainingPlans: response.data._embedded.trainingPlans.reverse()});
        });
    }

    deletePlan = (e, planId) => {
        e.preventDefault();
        axios.delete(apiUri + '/trainingPlans/' + planId).then(res => {
            let updatedTrainingPlans = this.state.trainingPlans.filter(function( tp ) {
                return tp.id !== planId;
            });
            this.setState({
                trainingPlans: updatedTrainingPlans,
                deletionStatus: res.status
            });
        }).catch(e => {
            this.setState({deletionStatus: e.response.status});
        });
    }

    render() {
        let flashMessage;
        if (this.state.deletionStatus === 204) {
            flashMessage = <Alert key={"success"} variant={"success"}>
                Plan deleted
            </Alert>
        } else if (this.state.deletionStatus != null) {
            flashMessage = <Alert key={"danger"} variant={"danger"}>
                Plan deletion error
            </Alert>
        }

        const trainingPlans = this.state.trainingPlans.map(trainingPlan =>
            <TrainingPlanRow key={trainingPlan._links.self.href} trainingPlan={trainingPlan} deletePlan={this.deletePlan}/>
        );
        return (
            <div>
                {flashMessage}
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
            </div>
        )
    }
}

class TrainingPlanRow extends Component{
    render() {
        return (
            <LinkContainer style={{cursor: 'pointer'}} to={"/training-plans/" + this.props.trainingPlan.id}>
                <tr key={this.props.trainingPlan.id}>
                    <td>{this.props.trainingPlan.name}</td>
                    <td>{this.props.trainingPlan.timeToTrain} minutes</td>
                    <td>{this.props.trainingPlan.goal}</td>
                    <td>{new Date(this.props.trainingPlan.createdAt).toLocaleString('cs-CZ', {
                        day: "2-digit",
                        month: "2-digit",
                        year: "numeric",
                        hour: "2-digit",
                        minute: '2-digit'
                    })}</td>
                    <td className="text-center">
                        <Button onClick={(e) => this.props.deletePlan(e, this.props.trainingPlan.id)} variant="danger">
                            <Trash3/>
                        </Button>
                    </td>
                </tr>
            </LinkContainer>
        )
    }
}

export default TrainingPlans;
