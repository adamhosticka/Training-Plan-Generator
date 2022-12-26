import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {LinkContainer} from 'react-router-bootstrap'

function Header() {
    return (
        <Navbar bg="light" expand="lg" className="mb-3">
            <Container>
                <LinkContainer to="/">
                    <Navbar.Brand>TPG</Navbar.Brand>
                </LinkContainer>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <LinkContainer to="/">
                            <Nav.Link>Create a plan</Nav.Link>
                        </LinkContainer>
                        <LinkContainer to="/training-plans">
                            <Nav.Link>Training plans</Nav.Link>
                        </LinkContainer>
                        <LinkContainer to="/exercises">
                            <Nav.Link>Exercises</Nav.Link>
                        </LinkContainer>
                        <LinkContainer to="/muscle-groups">
                            <Nav.Link>Muscle groups</Nav.Link>
                        </LinkContainer>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;
