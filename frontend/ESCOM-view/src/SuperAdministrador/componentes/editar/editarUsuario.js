import React from 'react';


//componentes
import Barra from '../general/BarraDirecciones.js'

import { Button } from 'reactstrap';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

//redux
import { actionCargarInformacionDeUsuario, actionEditarUsuario } from '../../actions/actionsUsuario.js'
import { connect } from "react-redux";
import { reduxForm, Field } from 'redux-form';

class editar extends React.Component {

  componentWillMount() {
    console.log("Propiedades del editar", this.props);
    this.props.actionCargarInformacionDeUsuario(this.props.cedula);
  }

  onClickCancelar = (event) => {
    event.preventDefault();
    this.props.cambiar('c');
  }

  handleSubmit = formValues => {
    let nuevo = [];
    this.props.actuales.map((post, index) => {
      if (post.cedula === this.props.cedula) {
        let usuario = {
          nombre: formValues.nombre,
          correo: formValues.correo,
          cedula: formValues.cedula,
          apellido: post.apellido,
          tipoDocumento: 1,
          contrasena: post.contrasena,
          fechaNacimiento: "1997/12/18",
          estado: post.estado
        }
        this.props.actionEditarUsuario(usuario, this.props.cedula);
        nuevo.push(usuario);
      } else {
        nuevo.push(post);
      }
    });

    // // this.props.anadirUser({ id: formValues.nombre, nombre: formValues.apellido })
  }

  render() {

    return (
      <div>
        <div class="text-left titulo letra" style={estiloLetrero}>
          <h4>Editar usuario</h4>
        </div>
        <Barra texto="Inicio > Administracion de usuarios > Editar usuario" />
        <div className="container" style={{
          paddingTop: "7px",
          paddingRight: "12px",
          paddingLeft: "40px",
          paddingBottom: "20px",
          margin: "0px 0px 32px"
        }}>
          <div className="container shadow" style={
            {
              background: "white",
              paddingTop: "12px",
              paddingRight: "30px",
              paddingLeft: "30px",
              paddingBottom: "0px"
            }}>
            <div>
              <div className="col-md-12 separador"></div>
              <form className="letra" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                <br />
                <label for="form_control_1">Nombre</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="nombre" component={generarInput} placeholder={this.props.usuarios.nombre} label="Nombre" />
                  </div>
                </div>
                <br />
                <label for="form_control_1">Apellidos</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="apellido" component={generarInput} placeholder={this.props.usuarios.apellido} label="Apellido" />
                  </div>
                </div>
                <br />
                <label for="form_control_1">Tipo de documento</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="tipoDocumento" className="bs-select form-control" component="select">
                      <option value="0">Seleccione</option>
                      <option value="1">Cedula de ciudadania</option>
                      <option value="2">Tarjeta de identidad</option>
                    </Field>
                  </div>
                </div>
                <br />
                <label for="form_control_1">Numero de documento</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="numeroDocumento" component={generarInput} placeholder={this.props.usuarios.cedula} label="Numero de documento" />
                  </div>
                </div>
                <br />
                <label for="form_control_3">Fecha de nacimiento</label>
                <div className="row">
                  <div className="col-sm-4">
                    <Field name="fechaNacimiento" component={generarInput} placeholder={this.props.usuarios.fechaNacimiento} label="Fecha de nacimiento" />
                  </div>
                </div>
                <br />
                <label for="form_control_1">Correo</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="correo" component={generarInput} placeholder={this.props.usuarios.correo} label="Correo electronico" />
                  </div>
                </div>
                <br />
                <label for="form_control_1">Contraseña</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="contrasena" component={generarInput} placeholder={this.props.usuarios.contrasena} label="Contraseña" />
                  </div>
                </div>
                <br />
                <div>
                  <Button style={fondoBoton} type="submit">Guardar</Button>{''}
                  <Button style={fondoBotonS} color="secondary" onClick={this.onClickCancelar}>Cancelar</Button>
                </div>
                <br />
                <br />
              </form>
            </div>
          </div>
          <br />
          <br />
          <div class="text-left titulo letra" style={estiloTitulo}>
            <h4>Actividades actualmente asignadas al usuario</h4>
          </div>
          <div className="container shadow" style={
            {
              background: "white",
              paddingTop: "12px",
              paddingRight: "30px",
              paddingLeft: "30px",
              paddingBottom: "0px"
            }}>
            <table className="table table-hover table-bordered table-checkable">
              <thead className="table table-hover table-striped col-md-12">
                <tr>
                  <th> Nombre </th>
                  <th>Descripcion </th>
                  <th> Modulo </th>
                  <th> </th>
                </tr>
              </thead>
              <tbody>

              </tbody>
            </table>
          </div>
        </div>
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

const estiloTitulo = {

  paddingTop: "7px",
  paddingRight: "12px",
  paddingLeft: "5px",
  paddingBottom: "1px"
}

const generarInput = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
  <div>
    <div>
      <input {...input} placeholder={placeholder} type={type} className="form-control letra form-control-solid placeholder-no-fix" />
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

function mapStateToProps(state) {
  return {
    usuarios: state.user.usuarioEditar,
    cedula: state.user.cedula,
    actuales: state.user.usuariosRegistrados
  }
}

let formularioEditar = reduxForm({
  form: 'editarUsuario', validate
})(editar)

export default connect(mapStateToProps, { actionCargarInformacionDeUsuario, actionEditarUsuario })(formularioEditar);

