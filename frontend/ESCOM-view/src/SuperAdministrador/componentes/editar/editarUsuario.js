import React from 'react';


//componentes
import Barra from '../general/BarraDirecciones.js'
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { withRouter } from 'react-router-dom';
import { generarInput, generarSelect } from '../../utilitario/GenerarInputs.js'
import { nombre, requerido, seleccione, apellido, fechaNacimiento, correo, documentoIdentificacion } from '../../utilitario/validacionCampos.js';

//iconos
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import DoneOutlineIcon from '@material-ui/icons/DoneOutline';
import SaveIcon from '@material-ui/icons/Save';
import CancelIcon from '@material-ui/icons/Cancel';
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
        NotificationManager.error('El correo o numero de identificacion ya estan registrados');
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
      token: '', 
      datosSolicitud:null
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
                this.state.habilitado ? <div className="col-sm-12">
                  <Alert severity="error" variant="outlined">
                    <AlertTitle>Sin permiso</AlertTitle>
                    No tiene permisos suficientes para editar la informacion de los usuarios</Alert>
                  <div style={{ padding: "25px 44px 25px 395px" }}>
                    <Button style={{ background: this.props.configuracion.botones, fontSize: "14px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark" variant="contained" onClick={this.onClickCancelar} startIcon={<DoneOutlineIcon />} type="submit">Aceptar</Button>{''}
                  </div>
                </div> :
                  <form className="letra" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                    <br />

                    <div className="row">
                      <div className="col-sm-6">
                        <label>Nombre</label>
                        <Field name="nombre" validate={[requerido, nombre]} component={generarInput} label="Nombre" />
                      </div>
                      <div className="col-sm-6">
                        <label>Apellidos</label>
                        <Field name="apellido" validate={[requerido, apellido]} component={generarInput} label="Apellido" />
                      </div>
                    </div>
                    <br />
                    <div className="row">
                      <div className="col-sm-6">
                        <label>Tipo de documento</label>
                        <Field name="tipoDocumento" validate={[seleccione]} style={{ height: "35px", fontSize: "13px" }} className="form-control" component={generarSelect} label="Username">
                          <option className="letra" style={{ height: "35px", fontSize: "13px" }} value="0">Seleccione</option>
                          {this.props.documentos.map(documento => <option key={documento.idTipoDocumento} className="letra" style={{ height: "35px", fontSize: "13px" }} value={documento.idTipoDocumento}>{documento.tipoDocumento}</option>)}
                        </Field>
                      </div>
                      <div className="col-sm-6">
                        <label>Numero de documento</label>
                        <Field name="numeroDocumento" type="number" validate={[requerido, documentoIdentificacion]} component={generarInput} label="Numero de documento" />
                      </div>
                    </div>
                    <br />
                    <div className="row">
                      <div className="col-sm-6">
                        <label>Correo</label>
                        <Field name="correo" validate={[requerido, correo]} component={generarInput} label="Correo electronico" />
                      </div>
                      <div className="col-sm-6">
                        <label>Fecha de nacimiento</label>
                        <Field name="fechaNacimiento" type="date" validate={[requerido, fechaNacimiento]} component={generarInput} label="Fecha de nacimiento" />
                      </div>
                    </div>
                    <br />
                    <Divider variant="middle" />
                    <br/>
                    <div className="row">
                      <div  className="col-sm-6" style={{paddingLeft:"350px"}}>
                        <Button style={{ background: this.props.configuracion.botones, fontSize: "14px", fontFamily: "sans-serif", textTransform: "none" }} startIcon={<SaveIcon />} className="btn btn-dark" variant="contained" type="submit">Guardar</Button>{''}
                      </div>
                      <div  className="col-sm-6">
                        <Button style={fondoBotonCancelar} variant="contained" className="btn btn-dark" startIcon={<CancelIcon />} onClick={this.onClickCancelar}>Salir</Button>
                      </div>
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

const fondoBotonCancelar = {
  background: "gray",
  fontSize: "14px",
  fontFamily: "sans-serif",
  textTransform: "none"
}


function mapStateToProps(state) {
  return {
    cedula: state.user.cedula,
    configuracion: state.conf.configuracion,
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

