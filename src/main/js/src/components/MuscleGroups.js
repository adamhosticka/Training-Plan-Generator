import {Col, Row, Table} from "react-bootstrap";
import {Component} from "react";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

import apiUri from '../constants';

class MuscleGroups extends Component{
    constructor(props) {
        super(props);
        this.state = {muscleGroups: []};
    }

    componentDidMount() {
        axios.get(apiUri + '/muscleGroups').then(response => {
            this.setState({muscleGroups: response.data._embedded.muscleGroups});
        });
    }
    render() {
        const muscleGroups = this.state.muscleGroups.map(muscleGroup =>
            <MuscleGroup key={muscleGroup._links.self.href} muscleGroup={muscleGroup}/>
        );
        return (
            <Table>
                <Row>
                    <Col>Název svalové partie</Col>
                    <Col>Objem svalové partie</Col>
                </Row>
                {muscleGroups}
            </Table>
        )
    }
}

class MuscleGroup extends Component{
    render() {
        return (
            <Row>
                <Col>{this.props.muscleGroup.name}</Col>
                <Col>{this.props.muscleGroup.volume}</Col>
            </Row>
        )
    }
}

export default MuscleGroups;
