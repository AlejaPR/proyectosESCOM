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
import { nombre,requerido,seleccione,apellido,fechaNacimiento,correo,contrasena, documentoIdentificacion} from '../../utilitario/validacionCampos.js';
// import MaterialTable from 'material-table';

//redux
import { actionCargarInformacionDeUsuario, actionEditarUsuario, actionConsultarDocumentos ,actionConsultarActividadesUsuario,actionActualizarUsuarios} from '../../actions/actionsUsuario.js'
import { connect } from "react-redux";
import { reduxForm, Field } from 'redux-form';


class editar extends React.Component {

  componentDidUpdate() {
    console.log('this ',this.props)
    if (this.props.mensajeEditar === 'Sin permiso') {
      NotificationManager.error('No tiene permiso para editar la informacion de los usuarios');
      this.props.history.push('/adminUsuario');
    }
  }

  componentDidMount() {
    if (this.props.cedula === undefined || this.props.cedula.length === 0) {
      this.props.history.push('/adminUsuario');
    } else {
      this.props.actionCargarInformacionDeUsuario(this.props.cedula, localStorage.getItem('Token'), this.cambiarPermiso);
      this.props.actionConsultarDocumentos(localStorage.getItem('Token'));
      // this.props.actionConsultarActividadesUsuario(this.props.cedula,localStorage.getItem('Token'));
    }
  }

  onClickCancelar = (event) => {
    event.preventDefault();
    this.props.history.push('/adminUsuario');
  }

  handleSubmit = formValues => {
    let nuevo = [];
    let date = new Date(formValues.fechaNacimiento);
    this.props.usuarios.map((post, index) => {
      if (post.numeroDocumento === this.props.cedula) {
        let usuario = {
          id:0,
          correoElectronico: formValues.correo,
          estado: post.estado,
          numeroDocumento: formValues.numeroDocumento,
          nombre: formValues.nombre,
          apellido: formValues.apellido,
          contrasena: formValues.contrasena,
          fechaNacimiento: date,
          tipoDocumento: formValues.tipoDocumento,
          token:''
        }
        console.log('usuario es',usuario);
        this.props.actionEditarUsuario(usuario, this.props.cedula,localStorage.getItem('Token'));
        nuevo.push(usuario);
        console.log('nuevo ',nuevo);
      } else {
        nuevo.push(post);
      }
    });
    NotificationManager.info('Informacion actualizada correctamente');
    console.log('actualizada',this.props.usuarios);
    this.props.actionActualizarUsuarios([]);
    this.props.actionActualizarUsuarios(nuevo);
    this.props.history.push('/adminUsuario');

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
                <label>Nombre</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="nombre" validate={[requerido,nombre]} component={generarInput} label="Nombre" />
                  </div>
                </div>
                <br />
                <label>Apellidos</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="apellido" validate={[requerido,apellido]} component={generarInput} label="Apellido" />
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
                    <Field name="numeroDocumento" type="number" validate={[requerido,documentoIdentificacion]} component={generarInput} label="Numero de documento" />
                  </div>
                </div>
                <br />
                <label>Fecha de nacimiento</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="fechaNacimiento" type="date" validate={[requerido,fechaNacimiento]}  component={generarInput} label="Fecha de nacimiento" />
                  </div>
                </div>
                <br />
                <label>Correo</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="correo" validate={[requerido,correo]} component={generarInput} label="Correo electronico" />
                  </div>
                </div>
                <br />
                <label >Contraseña</label>
                <div className="row">
                  <div className="col-sm-5">
                    <Field name="contrasena" validate={[contrasena]} type="password" component={generarInput} label="Contraseña" />
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
          <div className="text-left titulo letra" style={estiloTitulo}>
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
            {/* <MaterialTable
              title="Actions On Selected Rows Preview"
              columns={[
                { title: 'Name', field: 'name' },
                { title: 'Surname', field: 'surname' },
                { title: 'Birth Year', field: 'birthYear', type: 'numeric' },
                {
                  title: 'Birth Place',
                  field: 'birthCity',
                  lookup: { 34: 'İstanbul', 63: 'Şanlıurfa' },
                },
              ]}
              data={[
                { name: 'Mehmet', surname: 'Baran', birthYear: 1987, birthCity: 63 },
                { name: 'Zerya Betül', surname: 'Baran', birthYear: 2017, birthCity: 34 },
              ]}
              options={{
                selection: true
              }}
              actions={[
                {
                  tooltip: 'Remove All Selected Users',
                  icon: 'delete',
                  onClick: (evt, data) => alert('You want to delete ' + data.length + ' rows')
                }
              ]}
            /> */}
          </div>
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

const estiloTitulo = {

  paddingTop: "7px",
  paddingRight: "12px",
  paddingLeft: "5px",
  paddingBottom: "1px"
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
      <input {...input} type={type} className="form-control letra form-control-solid placeholder-no-fix" />
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
      tipoDocumento:state.user.usuarioEditar.tipoDocumento,
      fechaNacimiento:'1990-12-19'
    }
  }
}



let formularioEditar = reduxForm({
  form: 'editarUsuario',
  enableReinitialize: true
})(editar)

export default withRouter(connect(mapStateToProps, { actionCargarInformacionDeUsuario, actionEditarUsuario,actionActualizarUsuarios, actionConsultarDocumentos ,actionConsultarActividadesUsuario})(formularioEditar));

