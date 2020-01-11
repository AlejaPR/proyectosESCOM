import React from 'react';

//estilos
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'
import 'react-notifications/lib/notifications.css';

//componentes
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';


//redux
import { actionAgregarUsuario, actionConsultarDocumentos, actualizarMensajeRegistrar } from '../../actions/actionsUsuario.js'
import { connect } from 'react-redux';
import { fechaNacimiento, seleccione, nombre, apellido, contrasena, correo, documentoIdentificacion, requerido } from '../../utilitario/validacionCampos.js';

class PopUpUsuario extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      modal: false
    }
    this.toggle = this.toggle.bind(this);
  }

  componentDidUpdate() {
    if (this.props.mensaje !== '') {
      switch (this.props.mensaje) {
        case 'Usuario registrado':
          NotificationManager.info('Usuario registrado correctamente');
          this.props.actualizarMensajeRegistrar('');
          break;
        case 'Sin permiso':
          NotificationManager.error('No tiene los permisos suficientes para registrar un usuario');
          this.props.reset();
          this.props.actualizarMensajeRegistrar('');
          break;
        case 'Ya existen los datos registrados previamente':
          NotificationManager.warning('El correo o numero de identificacion ya estan registrados');
          this.props.reset();
          this.props.actualizarMensajeRegistrar('');
          break;
        case 'Servidor fuera de servicio temporalmente':
          NotificationManager.error('Servidor fuera de servicio temporalmente');
          this.props.reset();
          this.props.actualizarMensajeRegistrar('');
          break;
        default:
          this.props.history.push('/');
          break;
      }
    }
  }

  componentWillMount() {
    this.props.actionConsultarDocumentos(localStorage.getItem('Token'));
  }

  toggle() {
    this.setState(prevState => ({
      modal: !prevState.modal
    }));
    this.props.reset();
  }

  handleSubmit = formValues => {
    try {
      var crypto = require('crypto');
      var contrasenaEncryp = crypto.createHmac('sha256', formValues.correo).update(formValues.contrasena).digest('hex');
      let date = new Date(formValues.fechaNacimiento);
      let usuario = {
        'nombre': formValues.nombre,
        'apellido': formValues.apellido,
        'tipoDocumento': formValues.tipoDocumento,
        'numeroDocumento': formValues.numeroDocumento,
        'correoElectronico': formValues.correo,
        'contrasena': contrasenaEncryp,
        'fechaNacimiento': date,
        'estado': 'Activo'
      };
      this.props.actionAgregarUsuario(usuario, localStorage.getItem('Token'));
      this.props.reset();
    } catch (error) {
      NotificationManager.error('Ingrese todos los datos');
    }

  }

  cargarDocumentos() {
    return this.props.documentos.map((documento, index) => {
      const { idTipoDocumento, tipoDocumento } = documento //destructuring
      return (
        <option className="letra" style={{ height: "35px", fontSize: "13px" }} value={idTipoDocumento}>{tipoDocumento}</option>
      )
    })

  }


  render() {
    return (
      <div>
        <Button color="danger" className="btn btn-dark letra" style={fondoBoton} onClick={this.toggle}>Crear usuario +</Button>
        <Modal isOpen={this.state.modal}
          toggle={this.toggle}
          style={{ width: "400px" }}
        >
          <ModalHeader toggle={this.toggle} style={{ height: "50px", width: "400px" }} className="center">Crear usuario</ModalHeader>
          <ModalBody>
            <form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
              <div className="contenedor-inputs">
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="nombre" validate={[requerido, nombre]} component={generarInput} label="Nombre" />
                    <br />
                    <Field name="apellido" validate={[requerido, apellido]} component={generarInput} label="Apellido" />
                    <br />
                  </div>
                </div>
                <div className="row">
                  <div className="col-sm-5">
                    <span className="letra">Tipo de documento </span>
                  </div>
                  <div className="col-sm-7">
                    <Field name="tipoDocumento" validate={[seleccione]} style={{ height: "35px", fontSize: "13px" }} className="form-control" component={generarSelect} label="Username">
                      <option className="letra" style={{ height: "35px", fontSize: "13px" }} value="0">Seleccione</option>
                      {this.props.documentos.map(documento => <option key={documento.idTipoDocumento} className="letra" style={{ height: "35px", fontSize: "13px" }} value={documento.idTipoDocumento}>{documento.tipoDocumento}</option>)}
                    </Field>
                  </div>
                </div>
                <br />
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="numeroDocumento" validate={[requerido, documentoIdentificacion]} component={generarInput} label="Numero de documento" />
                  </div>
                </div>
                <br />
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="correo" validate={[requerido, correo]} component={generarInput} label="Correo electronico" />
                  </div>
                </div>
                <br />
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="contrasena" validate={[requerido, contrasena]} type="password" component={generarInput} label="Contraseña" />
                  </div>
                </div>
                <br />
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="fechaNacimiento" type="date" validate={[requerido, fechaNacimiento]} component={generarInput} label="Fecha de nacimiento" />
                  </div>
                </div>
              </div>
              <ModalFooter>
                <Button style={fondoBoton} type="submit">Registrar</Button>{''}
                <Button color="secondary" style={fondoBotonCancelar} className="letra" onClick={this.toggle}>Cancelar</Button>
              </ModalFooter>
            </form>
          </ModalBody>
        </Modal>
        <NotificationContainer />
      </div>
    );
  }
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

const generarInput = ({ input, label, type, meta: { touched, error, warning } }) => (
  <div>
    <div>
      <input {...input} placeholder={label} type={type} style={{ height: "35px", fontSize: "12px" }} className="form-control letra placeholder-no-fix" />
      {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
)


const fondoBoton = {
  background: "#ec671d",
  fontSize: "12px",
  fontFamily: "Open sans, sans-serif"

}

const fondoBotonCancelar = {
  background: "gray",
  fontSize: "12px",
  fontFamily: "Open sans, sans-serif"

}

function mapStateToProps(state) {
  return {
    users: state.user.list,
    token: state.user.token,
    documentos: state.user.tiposDocumento,
    mensaje: state.user.mensajeRegistrar
  }
}

let formulario = reduxForm({
  form: 'registrarUsuario'
})(PopUpUsuario)

export default withRouter(connect(mapStateToProps, { actionAgregarUsuario, actionConsultarDocumentos, actualizarMensajeRegistrar })(formulario));
