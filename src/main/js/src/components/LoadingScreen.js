import React from 'react';
import ReactLoading from 'react-loading';

const LoadingScreen = () => (
    <div className={"d-flex align-items-center justify-content-center mt-5"}>
        <ReactLoading type="spin" color="#696969" height={"15%"} width={"15%"} />
    </div>
);

export default LoadingScreen;