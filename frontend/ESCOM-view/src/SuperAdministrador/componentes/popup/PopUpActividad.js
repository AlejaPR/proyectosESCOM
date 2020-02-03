import React from 'react';


import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';


//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import Select from 'react-select'
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { generarInput, renderTextArea } from '../../utilitario/GenerarInputs.js'
import { fechaNacimiento, seleccione, nombre, apellido, contrasena, correo, documentoIdentificacion, requerido } from '../../utilitario/validacionCampos.js';

import { actionConsultarModulos, actionAgregarActividad, actualizarMensajeRegistrar } from '../../actions/actionActividad.js'
import { connect } from 'react-redux';

class PopUpActividad extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modal: false
        };
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState(prevState => ({
            modal: !prevState.modal
        }));
    }

    componentWillMount() {
        this.props.actionConsultarModulos(localStorage.getItem('Token'));
    }

    opciones = () => {
        let respuesta = [];
        this.props.modulos.map(
            modulo => {
                let objeto = {
                    label: modulo.nombreModulo,
                    value: modulo.idModulo,
                }
                respuesta.push(objeto);
            }
        )
        return respuesta;
    }

    handleSubmit = formValues => {
        try {
            console.log('fofro',formValues)
            let actividad = {
                'nombre': formValues.nombre,
                'descripcionActividad': formValues.descripcion,
                'idModulo': formValues.modulo.value
              };
              this.props.actionAgregarActividad(actividad,localStorage.getItem('Token'));
              this.props.reset();
            } catch (error) {
            NotificationManager.error('Ingrese todos los datos');
        }
    }


    render() {
        return (
            <div>
                <Button color="danger" className="btn btn-dark" style={fondoBoton} onClick={this.toggle}>Crear actividad +</Button>
                <Modal isOpen={this.state.modal}
                    toggle={this.toggle}
                    className={this.props.className}
                    size="col-md-6"
                >
                    <ModalHeader toggle={this.toggle} className="center">Crear actividad</ModalHeader>
                    <ModalBody>
                        <form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                            <div className="contenedor-inputs">
                                <div className="row">
                                    <div className="col-sm-12">
                                        <Field name="nombre" validate={[requerido, nombre]} component={generarInput} label="Nombre" />
                                        <br />
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-sm-12">
                                        <Field name="descripcion" component={renderTextArea} label="descripcion" />
                                    </div>
                                </div>
                                <br />
                                <div className="row">
                                    <div className="col-sm-12">
                                        <Field name="modulo" validate={[seleccione]} component={ReduxFormSelect} options={this.opciones()} />
                                    </div>
                                </div>
                            </div>
                            <ModalFooter>
                                <Button style={fondoBoton} type="submit">Registrar</Button>{''}
                                <Button color="secondary" className="letra" onClick={this.toggle}>Cancelar</Button>
                            </ModalFooter>
                        </form>
                    </ModalBody>
                    <NotificationContainer />
                </Modal>
            </div>
        );
    }
}

export const ReduxFormSelect = props => {
    const { input, options } = props;
    const { touched, error } = props.meta;
    return (
        <>
            <Select
                {...input}
                isSearchable={false}
                placeholder='Seleccione un modulo'
                onChange={value => input.onChange(value)}
                onBlur={() => input.onBlur(input.value)}
                noOptionsMessage={() => 'Aun no hay ningun modulo registrado'}
                options={options}
            />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
        </>
    )
}

const fondoBoton = {
    background: "#ec671d",
    fontSize: "13px",
    fontFamily: "sans-serif"

}

function mapStateToProps(state) {
    return {
        modulos: state.act.modulosActividades,
        mensaje: state.act.mensajeRegistrar
    }
}

let formulario = reduxForm({
    form: 'registrarActividad'
})(PopUpActividad)

export default withRouter(connect(mapStateToProps, { actionConsultarModulos, actionAgregarActividad, actualizarMensajeRegistrar })(formulario));

