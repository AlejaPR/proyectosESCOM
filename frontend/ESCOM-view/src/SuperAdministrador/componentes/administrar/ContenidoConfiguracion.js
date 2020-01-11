import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

//componentes
import { reduxForm } from 'redux-form';
import { SketchPicker } from 'react-color';
import { Button } from 'reactstrap';

//redux conexion
import { connect } from 'react-redux';
import { consultarConfiguracion, actionActualizarBarraLateral, actionActualizarBarraSuperior, actionActualizarBotones } from '../../actions/actionConfiguracion.js'

import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'


class Configuracion extends React.Component {

    async componentDidMount() {
        console.log('PROPS ', this.props)
        this.props.consultarConfiguracion('esta es una prueba')
    }

    handleSubmit = formValues => {
    }

    cargarEstiloBoton = () => {
        return ({
            background: this.props.configuracion.botones,
            fontSize: "14px",
            fontFamily: "Open sans, sans-serif"
        })
    }

    handleChangeComplete = (color) => {
        this.props.actionActualizarBarraLateral(color.hex);
    };

    handleChangeCompleteSuperior = (color) => {
        this.props.actionActualizarBarraSuperior(color.hex);
    };

    handleChangeCompleteBotones = (color) => {
        this.props.actionActualizarBotones(color.hex);
    };

    render() {
        return (
            <div>
                <div className="text-left titulo" style={estiloLetrero}>
                    <h4>Configuracion de aspecto</h4>
                </div>
                <div className="container" style={{
                    paddingTop: "7px",
                    paddingRight: "12px",
                    paddingLeft: "40px",
                    paddingBottom: "20px",
                    margin: "0px 0px 32px"
                }}>
                    <div className="container shadow" style={fondoBarraSuperior}>
                        <br />
                        <br />
                        <div className="p-1 jumbotron-fluid" >
                            <form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                <div className="contenedor-inputs">
                                    <div className="row">
                                        <div className="col-sm-4">
                                            <span>Barra lateral: </span>
                                            <br />
                                            <br />
                                            <SketchPicker disableAlpha={true} color={this.props.configuracion.fondoBarra} onChangeComplete={this.handleChangeComplete} />
                                        </div>
                                        <div className="col-sm-4">
                                            <span>Barra superior: </span>
                                            <br />
                                            <br />
                                            <SketchPicker disableAlpha={true} color={this.props.configuracion.fondoSuperior} onChangeComplete={this.handleChangeCompleteSuperior} />
                                        </div>
                                        <div className="col-sm-4">
                                            <span>Botones: </span>
                                            <br />
                                            <br />
                                            <SketchPicker disableAlpha={true} color={this.props.configuracion.botones} onChangeComplete={this.handleChangeCompleteBotones} />
                                            <br /><br />
                                            <Button style={this.cargarEstiloBoton()}>Boton muestra</Button>{''}
                                        </div>
                                    </div>
                                    <br />
                                    <br />
                                    <div className="row">

                                        <div className="col-sm-5">
                                            <span>Logo </span>
                                        </div>
                                        <div className="col-sm-7">
                                            <input type="file" name="files[]" multiple="" width="200" height="40" />
                                        </div>
                                    </div>
                                    <br />
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <span>Login </span>
                                        </div>
                                        <div className="col-sm-7">
                                            <input type="file" name="files[]" multiple="" width="200" height="40" />
                                        </div>
                                    </div>
                                    <br />
                                </div>
                                <Button style={{ background: "blue" }} type="submit">Guardar</Button>{''}
                                <br />
                                <br />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

// const generarInput = ({ input, label, type, meta: { touched, error, warning } }) => (
//     <div>
//         <div>
//             <input {...input} placeholder={label} type={type} className="form-control letra form-control-solid placeholder-no-fix" />
//             {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
//         </div>
//     </div>
// )

const validate = values => {
    const errors = {}
    if (!values.nombre) {
        errors.nombre = 'Este campo es obligatorio'
    } else if (values.nombre.length < 2) {
        errors.nombre = 'Minimum be 2 characters or more'
    }

    if (!values.email) {
        errors.email = 'Required'
    } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(values.email)) {
        errors.email = 'Invalid email address'
    }
    return errors
}


const estiloLetrero = {
    paddingTop: "20px",
    paddingRight: "12px",
    paddingLeft: "40px",
    paddingBottom: "1px"
}

const fondoBarraSuperior = {
    background: "#FFFFFF"

}




function mapStateToProps(state) {
    return {
        configuracion: state.conf.estilos
    }
}

let formulario = reduxForm({
    form: 'registrarConfiguracion', validate
})(Configuracion)

export default connect(mapStateToProps, { consultarConfiguracion, actionActualizarBarraLateral, actionActualizarBarraSuperior, actionActualizarBotones })(formulario);
