import React from 'react';
import { Component } from 'react';
import "reactjs-popup";


class BarraDeDireccion extends Component {
    render() {
        return (
            <div style={{
                paddingTop: "5px",
                paddingRight: "46px",
                paddingLeft: "40px",
                paddingBottom: "1px"
            }}>
                <div className="col-md-12 alert" style={fondoBoton}>
                    {this.props.texto}
                </div>
            </div>

        )
    }



}

const fondoBoton = {
    background: "white",
    fontSize: "14px",
    width: "100%",
    padding: "10px"

}




export default BarraDeDireccion;