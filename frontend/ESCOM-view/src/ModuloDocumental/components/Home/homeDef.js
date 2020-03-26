import React from 'react';
import { Component } from 'react';
import { withRouter } from 'react-router-dom';


class HomeDef extends Component {

    render() {
        return (
            <div className="container" style={{ width: "80%" }}> 
                <h1 className="text-center">Bienvenidos</h1>
            </div>
        )
    }
}

export default withRouter(HomeDef);
