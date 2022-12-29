import React from 'react';
import { useLocation } from 'react-router-dom';
import { Button } from 'react-bootstrap';

function NoMatch() {
    const location = useLocation();

    return (
        <div>
            <p className="lead">
                The page you are looking for was not found: {location.pathname}
            </p>
            <p className="lead">
                <Button variant="info" href="/">
                    Go to Homepage
                </Button>
            </p>
        </div>
    );
}

export default NoMatch;
