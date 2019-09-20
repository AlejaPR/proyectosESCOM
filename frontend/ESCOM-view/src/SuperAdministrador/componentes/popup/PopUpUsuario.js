import React from 'react';

//estilos
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

//componentes
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { reduxForm, Field } from 'redux-form';

//redux
import { actionAgregarUsuario } from '../../actions/actionsUsuario.js'
import { connect } from 'react-redux';

class PopUpUsuario extends React.Component {
  constructor(props) {

    super(props);
    this.state = {
      modal: false
    }
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState(prevState => ({
      modal: !prevState.modal
    }));
  }

  handleSubmit = formValues => {
    let usuario = {
      'nombre': formValues.nombre,
      'apellido': formValues.apellido,
      'tipoDocumento': 1,
      'cedula': formValues.numeroDocumento,
      'correo': formValues.correo,
      'contrasena': formValues.contrasena,
      'fechaNacimiento': formValues.fechaNacimiento,
      'estado': "Activo"
    };
    this.props.reset();
    this.props.actionAgregarUsuario(usuario);
    NotificationManager.success('Usuario registrado')
  }


  render() {
    return (
      <div>
        <Button color="danger" className="btn btn-dark" style={fondoBoton} onClick={this.toggle}>Crear usuario +</Button>
        <Modal isOpen={this.state.modal}
          toggle={this.toggle}
          className={this.props.className}
          size="col-md-6"
        >
          <ModalHeader toggle={this.toggle} className="center letra">Crear usuario</ModalHeader>
          <ModalBody>
            <form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
              <div className="contenedor-inputs">
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="nombre" component={generarInput} label="Nombre" />
                    <br />
                    <Field name="apellido" component={generarInput} label="Apellido" />
                    <br />
                  </div>
                </div>
                <div className="row">
                  <div className="col-sm-5">
                    <span>Tipo de documento </span>
                  </div>
                  <div className="col-sm-7">
                    <Field name="favoriteColor" className="bs-select form-control" component="select">
                      <option></option>
                      <option value="0">Seleccione</option>
                      <option value="1">Cedula de ciudadania</option>
                      <option value="2">Tarjeta de identidad</option>
                    </Field>
                  </div>
                </div>
                <br />
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="numeroDocumento" component={generarInput} label="Numero de documento" />
                  </div>
                </div>
                <br />
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="correo" component={generarInput} label="Correo electronico" />
                  </div>
                </div>
                <br />
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="contrasena" component={generarInput} label="Contraseña" />
                  </div>
                </div>
                <br />
                <div className="row">
                  <div className="col-sm-12">
                    <Field name="fechaNacimiento" type="date" component={generarInput} label="Fecha de nacimiento" />
                  </div>
                </div>
              </div>
              <ModalFooter>
                <Button style={fondoBoton} type="submit">Registrar</Button>{''}
                <Button color="secondary" onClick={this.toggle}>Cancelar</Button>
              </ModalFooter>
            </form>
            <NotificationContainer />
          </ModalBody>
        </Modal>
      </div>
    );
  }
}

const generarInput = ({ input, label, type, meta: { touched, error, warning } }) => (
  <div>
    <div>
      <input {...input} placeholder={label} type={type} className="form-control letra form-control-solid placeholder-no-fix" />
      {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
)

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

const fondoBoton = {
  background: "#ec671d",
  fontSize: "14px",
  fontFamily: "Open sans, sans-serif"

}

function mapStateToProps(state) {
  return {
    users: state.user.list
  }
}

let formulario = reduxForm({
  form: 'registrarUsuario', validate
})(PopUpUsuario)

export default connect(mapStateToProps, { actionAgregarUsuario })(formulario);
