import {Component} from "react";
import {Alert, Button, Form} from "react-bootstrap";
// import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import apiUri from "../constants";


class TrainingPlanGenerator extends Component {

    constructor(props) {
        super(props);
        this.state = {
            age: 18,
            gender: 'Male',
            muscleGroups: [],
            timeToTrain: 40,
            goal: 'Hypertrophy'
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleCheck = this.handleCheck.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        const payload = {...this.state};
        payload.timeToTrain = parseInt(payload.timeToTrain) + 20;
        console.log(payload);
        // axios.post(`http://localhost:8080/api/authors`, payload)
        //     .then(res => {
        //         let authors =
        //             this.setState({status: res.status})
        //     })
    }

    handleCheck(muscleGroup) {
        let muscleGroups = [...this.state.muscleGroups];
        for(const mG of muscleGroups) {
            if (mG.link === muscleGroup.link) {
                mG.checked = !mG.checked;
            }
        }
        this.setState({muscleGroups});
    }

    componentDidMount() {
        axios.get(apiUri + '/muscleGroups').then(response => {
            this.setState({muscleGroups:
                response.data._embedded.muscleGroups.map(muscleGroup => ({
                        name: muscleGroup.name,
                        link: muscleGroup._links.self,
                        checked: false
                }))
            });
        });
    }

    getTimeToTrain() {
        return parseInt(this.state.timeToTrain) + 20;
    }

    render() {
        let flashMessage;
        if (this.state.status === 201) {
            flashMessage = <Alert key={"success"} variant={"success"}>
                Plan created
            </Alert>
        } else if (this.state.status != null) {
            flashMessage = <Alert key={"danger"} variant={"danger"}>
                Plan creation error
            </Alert>
        }

        return (

            <div>
                <h3>Create a customized plan!</h3>
                {flashMessage}
                <Form onSubmit={this.handleSubmit}>
                    <Form.Group className="mb-3" controlId="age">
                        <Form.Label>Age</Form.Label>
                        <Form.Control type="number" min={15} max={100} placeholder="Age" value={this.state.age}
                                      onChange={(e) => this.setState({age: e.target.value})}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="gender">
                        <Form.Label>Gender</Form.Label>
                        <Form.Select aria-label="Gender" value={this.state.gender}
                                     onChange={(e) => this.setState({gender: e.target.value})}>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </Form.Select>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="muscleGroups">
                        <Form.Label>Muscle groups</Form.Label>
                        <div>
                            {this.state.muscleGroups.map(muscleGroup => (
                                <Form.Check inline key={muscleGroup.name} label={muscleGroup.name} type="checkbox"
                                    checked={muscleGroup.checked}
                                    onChange={(e) => this.handleCheck(muscleGroup)}
                                />
                            ))}
                        </div>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="timeToTrain">
                        <Form.Label>Time to train: {this.getTimeToTrain()} minutes</Form.Label>
                        <Form.Range value={this.state.timeToTrain}
                                          onChange={(e) => this.setState({timeToTrain: e.target.value})}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="goal">
                        <Form.Label>Goal</Form.Label>
                        <Form.Select aria-label="goal" value={this.state.goal}
                                     onChange={(e) => this.setState({goal: e.target.value})}>
                            <option value="Hypertrophy">Hypertrophy</option>
                            <option value="Strength">Strength</option>
                        </Form.Select>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Create
                    </Button>
                </Form>
            </div>
        );
    }
}

export default TrainingPlanGenerator;
