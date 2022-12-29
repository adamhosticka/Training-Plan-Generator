import Table from "react-bootstrap/Table";
import {Component} from "react";
import {Button} from "react-bootstrap";
import { useParams } from "react-router-dom";
import {jsPDF} from "jspdf";
import html2canvas from "html2canvas";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

import styleMuscleGroups from '../helper/StyleMuscleGroups';
import apiUri from '../helper/constants';
import LoadingScreen from "./LoadingScreen";

function withParams(Component) {
    return props => <Component {...props} params={useParams()} />;
}

class TrainingPlan extends Component{
    constructor(props) {
        super(props);
        this.state = {
            loading: true,
            planId: null,
            trainingPlan: {},
            muscleGroups: [],
            exercises: [],
        };
    }

    async componentDidMount() {
        const {planId} = this.props.params;
        let trainingPlanResponse = null;
        try {
            trainingPlanResponse = await axios.get(apiUri + '/trainingPlans/' + planId);
        } catch(e) {
            this.setState({loading: false, planId: -1})
            return;
        }
        const [muscleGroupsResponse, exercisesResponse] = await Promise.all([
            axios.get(trainingPlanResponse.data._links.muscleGroups.href),
            axios.get(trainingPlanResponse.data._links.exercises.href),
        ]);
        for(let exercise of exercisesResponse.data._embedded.exercises) {
            const catRes = await axios.get(exercise._links.category.href);
            exercise.category = catRes.data;
            const musRes = await axios.get(exercise._links.muscleGroups.href);
            exercise.muscleGroups = musRes.data._embedded.muscleGroups;
        }
        this.setState({
            trainingPlan: trainingPlanResponse.data,
            muscleGroups: muscleGroupsResponse.data._embedded.muscleGroups,
            exercises: exercisesResponse.data._embedded.exercises,
            planId: planId,
            loading: false,
        });
    }
    render() {
        if(this.state.loading === true) {
            return (<LoadingScreen/>)
        }
        if(this.state.planId === -1) {
            return (
                <div>Couldn't load training plan...</div>
            )
        }
        const exercises = this.state.exercises.map(exercise =>
            <ExerciseRow key={exercise._links.self.href} exercise={exercise}/>
        );
        return (
            <div>
                <div id="training-plan">
                    <h2 className="mb-3 text-center">{this.state.trainingPlan.name}</h2>
                    <h5>{styleMuscleGroups(this.state.muscleGroups)}</h5>
                    <div id="plan-details" className="mb-3">
                        <div>
                            <span className="fw-bold me-2">Goal:</span>
                            {this.state.trainingPlan.goal}
                        </div>
                        <div>
                            <span className="fw-bold me-2">Duration:</span>
                            {this.state.trainingPlan.timeToTrain} minutes
                        </div>
                        <div>
                            <span className="fw-bold me-2">Created:</span>
                            {new Date(this.state.trainingPlan.createdAt).toLocaleString('cs-CZ', {
                                day: "2-digit",
                                month: "2-digit",
                                year: "numeric",
                                hour: "2-digit",
                                minute: '2-digit'
                            })}
                        </div>
                    </div>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Exercise</th>
                                <th>Targeted muscle groups</th>
                                <th>Sets</th>
                                <th>Reps per set</th>
                            </tr>
                        </thead>
                        <tbody>
                            {exercises}
                        </tbody>
                    </Table>
                </div>
                <Button variant="primary" onClick={generatePDF} type="button" className={"float-end mb-4"}>Export PDF</Button>
            </div>
        )
    }
}

class ExerciseRow extends Component{
    render() {
        return (
            <tr>
                <td>{this.props.exercise.name}</td>
                <td>{styleMuscleGroups(this.props.exercise.muscleGroups)}</td>
                <td>{this.props.exercise.category.optimal_set_count}</td>
                <td>{this.props.exercise.category.optimal_rep_count}</td>
            </tr>
        )
    }
}

const generatePDF = async () => {
    const input = document.getElementById('training-plan');
    html2canvas(input)
        .then((canvas) => {
            let imgWidth = 195;
            let imgHeight = canvas.height * imgWidth / canvas.width;
            const imgData = canvas.toDataURL('img/png');
            const pdf = new jsPDF('p', 'mm', 'a4');
            pdf.addImage(imgData, 'PNG', 10, 10, imgWidth, imgHeight);
            pdf.save("training-plan.pdf");
        })
    ;
}

export default withParams(TrainingPlan);
