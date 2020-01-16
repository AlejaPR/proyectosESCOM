import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import { Button } from 'reactstrap';


//componentes
import Barra from '../general/BarraDirecciones';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { withRouter } from 'react-router-dom';
import { nombre, requerido, seleccione, apellido, fechaNacimiento, correo, contrasena, documentoIdentificacion } from '../../utilitario/validacionCampos.js';
import MaterialTable from 'material-table';
import Alerta from '@icons/material/AlertIcon.js';

//redux
import { actionCargarInformacionDeModulo, actionConsultarActividadesModulo } from '../../actions/actionsModulo.js'
import { connect } from "react-redux";
import { reduxForm, Field } from 'redux-form';


class EditarModulo extends React.Component {

    state = {
        habilitado: false,
        actividadesSeleccionadas:[]
    }

    componentDidUpdate() {
        if (this.props.mensajeEditar === 'Sin permiso') {
            if (!this.state.habilitado) { this.setState({ habilitado: true }) };
            // this.props.history.push('/adminUsuario');
        }
        console.log('props',this.props)
    }

    componentDidMount() {
        if (this.props.codigoModulo === undefined || this.props.codigoModulo.length === 0) {
            this.props.history.push('/adminModulo');
        } else {
            this.props.actionCargarInformacionDeModulo(this.props.codigoModulo, localStorage.getItem('Token'));
            this.props.actionConsultarActividadesModulo(this.props.codigoModulo, localStorage.getItem('Token'));
            // this.props.actionConsultarActividadesUsuario(this.props.cedula,localStorage.getItem('Token'));
        }
    }


    onClickCancelar = (event) => {
        event.preventDefault();
        this.props.history.push('/adminModulo');
    }

    handleSubmit = formValues => {

    }

    render() {
        return (
            <div>
                <div class="text-left titulo" style={estiloLetrero}>
                    <h4>Editar modulo</h4>
                </div>
                <Barra texto="Inicio > Editar modulo" />
                <div className="col-sm-12" style={{
                    paddingTop: "0px",
                    paddingRight: "40px",
                    paddingLeft: "40px",
                    paddingBottom: "0px",
                    margin: "0px 0px 30px"

                }}>

                    <div className="container shadow" style={fondoBarraSuperior}>
                        <br />
                        {
                            this.state.habilitado ? <div className="col-sm-12"> <span className="col-sm-2 center" style={{
                                textShadow: "none!important",
                                fontSize: "16px",
                                fontFamily: "Open Sans,sans-serif",
                                fontWeight: "300",
                                padding: "13px 122px",
                                color: "#fff",
                                background: "rgb(158, 35, 45)"
                            }}><Alerta />No tiene los permisos suficientes para administrar los usuarios</span>
                                <div style={{ padding: "25px 44px 25px 287px" }}>
                                    <Button style={fondoBoton} onClick={this.onClickCancelar} type="submit">Aceptar</Button>{''}
                                </div>
                            </div> :
                                <form className="letra" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                    <br />
                                    <label>Imagen</label>
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <Field name="numeroDocumento" validate={[requerido, documentoIdentificacion]} component={generarInput} label="Numero de documento" />
                                        </div>
                                    </div>
                                    <br />
                                    <label>Nombre</label>
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <Field name="nombre" validate={[requerido, nombre]} component={generarInput} label="Nombre" />
                                        </div>
                                    </div>
                                    <br />
                                    <label>Descripcion</label>
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <Field name="apellido" validate={[requerido, apellido]} component={renderTextArea} label="Apellido" />
                                        </div>
                                    </div>
                                    <br />
                                    <div className="text-left titulo letra" style={estiloTitulo}>
                                        <MaterialTable
                                            title="Actividades actualmente asignadas al modulo"
                                            localization={{
                                                header: {
                                                    actions: ' '
                                                },
                                                pagination: {
                                                    nextTooltip: 'Siguiente ',
                                                    previousTooltip: 'Anterior',
                                                    labelDisplayedRows: '{from}-{to} de {count}',
                                                    lastTooltip: 'Ultima pagina',
                                                    firstTooltip: 'Primera pagina',
                                                    labelRowsSelect: 'Registros',
                                                    firstAriaLabel: 'oooo'
                                                },
                                                body: {
                                                    emptyDataSourceMessage: 'Aun no hay ningun modulo registrado'
                                                },
                                                toolbar: {
                                                    searchTooltip: 'Buscar',
                                                    searchPlaceholder: 'Buscar'
                                                }
                                            }}
                                            columns={[
                                                { title: 'Nombre de modulo', field: 'idActividad', headerStyle: estiloCabecera, cellStyle: estiloFila },
                                                { title: 'Descripcion del modulo', field: 'nombre', headerStyle: estiloCabecera, cellStyle: estiloFila }
                                            ]}
                                            data={this.props.actividades}
                                            options={{
                                                search: true,
                                                rowStyle: estiloFila,
                                                selection:true
                                            }}
                                            onSelectionChange={(rows) => {
                                                this.setState({actividadesSeleccionadas:rows});
                                                console.log('You selected ' , rows)
                                            }}
                                        />

                                    </div>
                                    <div>
                                        <Button style={fondoBoton} type="submit">Guardar</Button>{''}
                                        <Button style={fondoBotonS} color="secondary" onClick={this.onClickCancelar}>Cancelar</Button>
                                    </div>
                                    <br />
                                    <br />
                                </form>
                        }
                        <br />
                    </div>
                </div>
            </div>
        )


    }
}


const generarInput = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
    <div>
        <div>
            <input {...input} type={type} style={{ fontSize: "12px" }} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
        </div>
    </div>
)

const renderTextArea = ({input, meta: { touched, error, warning }}) => (
    <div>
        <div>
            <textarea {...input} style={{ fontSize: "12px" }} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
        </div>
    </div>
);

const estiloTitulo = {

    paddingTop: "7px",
    paddingRight: "12px",
    paddingLeft: "5px",
    paddingBottom: "1px"
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


const estiloCabecera = {
    fontSize: '13px',
    fontFamily: 'sans-serif',
    padding: '8px',
    background: '#e7ecf1'

}

const estiloFila = {
    fontSize: '12px',
    fontFamily: 'sans-serif',
    padding: '8px',
}



const fondoBoton = {
    background: "#ec671d",
    fontSize: "14px",
    fontFamily: "Open sans, sans-serif"

}

const fondoBotonS = {
    background: "secondary",
    fontSize: "14px",
    fontFamily: "Open sans, sans-serif"

}

function mapStateToProps(state) {
    return {
        codigoModulo: state.mod.codigoModulo,
        actividades: state.mod.actividadesModulos,
        mensajeEditar: state.mod.mensajeEditarModulo,
        initialValues: {
            nombre: state.mod.moduloEditar.nombreModulo,
            apellido: state.mod.moduloEditar.descripcionModulo,
            numeroDocumento: state.mod.moduloEditar.imagenModulo,
        }
    }
}


let formularioEditar = reduxForm({
    form: 'editarModulo',
    enableReinitialize: true
})(EditarModulo)

export default withRouter(connect(mapStateToProps, { actionCargarInformacionDeModulo, actionConsultarActividadesModulo })(formularioEditar));

