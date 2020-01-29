import React from 'react';


//componentes
import Barra from '../general/BarraDirecciones.js'

import { Button } from 'reactstrap';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'
import 'react-notifications/lib/notifications.css';

//componentes
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { withRouter } from 'react-router-dom';
import { nombre, requerido, seleccione, apellido, fechaNacimiento, correo, documentoIdentificacion } from '../../utilitario/validacionCampos.js';
// import MaterialTable from 'material-table';
import Alerta from '@icons/material/AlertIcon.js';


//redux
import { actionCargarInformacionDeUsuario, actionEditarUsuario, actualizarMensajeEditar, actionConsultarDocumentos, actionConsultarActividadesUsuario, actionActualizarUsuarios } from '../../actions/actionsUsuario.js'
import { connect } from "react-redux";
import { reduxForm, Field } from 'redux-form';


class editar extends React.Component {

  state = {
    habilitado: false
  }

  clickAceptar() {
    this.props.history.push('/adminUsuario');
  }

  componentDidUpdate() {
    switch (this.props.mensajeEditar) {
      case 'Sin permiso':
        if (!this.state.habilitado) { this.setState({ habilitado: true }) };
        break;
      case 'Modificado':
        NotificationManager.success('Informacion actualizada');
        this.props.actionCargarInformacionDeUsuario(this.props.cedula, localStorage.getItem('Token'));
        this.props.actualizarMensajeEditar('');
        break;
      case 'Ya existen los datos registrados previamente':
        this.props.actionCargarInformacionDeUsuario(this.props.cedula, localStorage.getItem('Token'));
        NotificationManager.warning('El correo o numero de identificacion ya estan registrados');
        this.props.actualizarMensajeEditar('');
        break;
      default:
        break;
      // this.props.history.push('/adminUsuario');
    }

  }

  componentDidMount() {
    if (this.props.cedula === undefined || this.props.cedula.length === 0) {
      this.props.history.push('/adminUsuario');
    } else {
      this.props.actionCargarInformacionDeUsuario(this.props.cedula, localStorage.getItem('Token'));
      this.props.actionConsultarDocumentos(localStorage.getItem('Token'));
      // this.props.actionConsultarActividadesUsuario(this.props.cedula,localStorage.getItem('Token'));
    }
  }

  onClickCancelar = (event) => {
    event.preventDefault();
    this.props.history.push('/adminUsuario');
  }

  handleSubmit = formValues => {
    let date = new Date(formValues.fechaNacimiento);
    let usuario = {
      id: 0,
      correoElectronico: formValues.correo,
      numeroDocumento: formValues.numeroDocumento,
      nombre: formValues.nombre,
      apellido: formValues.apellido,
      fechaNacimiento: date,
      tipoDocumento: formValues.tipoDocumento,
      token: ''
    }
    this.props.actionEditarUsuario(usuario, this.props.cedula, localStorage.getItem('Token'));

  }

  render() {

    return (
      <div>
        <div className="text-left titulo letra" style={estiloLetrero}>
          <h4>Editar usuario</h4>
        </div>
        <Barra texto="Inicio > Administracion de usuarios > Editar usuario" />
        <div className="container" style={{
          paddingTop: "7px",
          paddingRight: "44px",
          paddingLeft: "40px",
          paddingBottom: "20px",
          margin: "0px 0px 32px"
        }}>
          <div className="container shadow" style={
            {
              background: "white",
              paddingTop: "37px",
              paddingRight: "31px",
              paddingLeft: "31px",
              paddingBottom: "21px"
            }}>
            <div>
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
                    <label>Nombre</label>
                    <div className="row">
                      <div className="col-sm-5">
                        <Field name="nombre" validate={[requerido, nombre]} component={generarInput} label="Nombre" />
                      </div>
                    </div>
                    <br />
                    <label>Apellidos</label>
                    <div className="row">
                      <div className="col-sm-5">
                        <Field name="apellido" validate={[requerido, apellido]} component={generarInput} label="Apellido" />
                      </div>
                    </div>
                    <br />
                    <label>Tipo de documento</label>
                    <div className="row">
                      <div className="col-sm-5">
                        <Field name="tipoDocumento" validate={[seleccione]} style={{ height: "35px", fontSize: "13px" }} className="form-control" component={generarSelect} label="Username">
                          <option className="letra" style={{ height: "35px", fontSize: "13px" }} value="0">Seleccione</option>
                          {this.props.documentos.map(documento => <option key={documento.idTipoDocumento} className="letra" style={{ height: "35px", fontSize: "13px" }} value={documento.idTipoDocumento}>{documento.tipoDocumento}</option>)}
                        </Field>
                      </div>
                    </div>
                    <br />
                    <label>Numero de documento</label>
                    <div className="row">
                      <div className="col-sm-5">
                        <Field name="numeroDocumento" type="number" validate={[requerido, documentoIdentificacion]} component={generarInput} label="Numero de documento" />
                      </div>
                    </div>
                    <br />
                    <label>Fecha de nacimiento</label>
                    <div className="row">
                      <div className="col-sm-5">
                        <Field name="fechaNacimiento" type="date" validate={[requerido, fechaNacimiento]} component={generarInput} label="Fecha de nacimiento" />
                      </div>
                    </div>
                    <br />
                    <label>Correo</label>
                    <div className="row">
                      <div className="col-sm-5">
                        <Field name="correo" validate={[requerido, correo]} component={generarInput} label="Correo electronico" />
                      </div>
                    </div>
                    <br />
                    <div>
                      <Button style={fondoBoton} type="submit">Guardar</Button>{''}
                      <Button style={fondoBotonS} color="secondary" onClick={this.onClickCancelar}>Salir</Button>
                    </div>
                    <br />
                    <br />
                  </form>
              }
            </div>
          </div>
          <br />
          <br />
        </div>
        <NotificationContainer />
      </div>
    );
  }
}

const estiloLetrero = {
  paddingTop: "20px",
  paddingRight: "12px",
  paddingLeft: "40px",
  paddingBottom: "1px"
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

const generarSelect = ({ input, label, type, meta: { touched, error }, children }) => (
  <div>
    <div>
      <select {...input} className="form-control letra" style={{ height: "35px", fontSize: "13px" }}>
        {children}
      </select>
      {touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
    </div>
  </div>
)

const generarInput = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
  <div>
    <div>
      <input {...input} type={type} style={{ fontSize: "13px" }} className="form-control letra form-control-solid placeholder-no-fix" />
      {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
)


function mapStateToProps(state) {
  return {
    cedula: state.user.cedula,
    mensajeEditar: state.user.mensajeEditar,
    usuarios: state.user.usuariosRegistrados,
    documentos: state.user.tiposDocumento,
    actividadesUsuario: state.user.actividadesUsuario,
    initialValues: {
      nombre: state.user.usuarioEditar.nombre,
      apellido: state.user.usuarioEditar.apellido,
      numeroDocumento: state.user.usuarioEditar.numeroDocumento,
      correo: state.user.usuarioEditar.correoElectronico,
      tipoDocumento: state.user.usuarioEditar.tipoDocumento,
      fechaNacimiento: state.user.usuarioEditar.fechaDeNacimiento
    }
  }
}



let formularioEditar = reduxForm({
  form: 'editarUsuario',
  enableReinitialize: true
})(editar)

export default withRouter(connect(mapStateToProps, { actionCargarInformacionDeUsuario, actualizarMensajeEditar, actionEditarUsuario, actionActualizarUsuarios, actionConsultarDocumentos, actionConsultarActividadesUsuario })(formularioEditar));

