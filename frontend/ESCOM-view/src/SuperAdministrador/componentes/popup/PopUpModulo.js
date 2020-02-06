import React from 'react';


import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';


//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'
import 'react-notifications/lib/notifications.css';

import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import Defecto from '../../imagenes/defecto.jpg';
import PropTypes from "prop-types";
import {requerido,validacionCuarentaCaracteres,validacionDoscientosCaracteres} from '../../utilitario/validacionCampos.js';


import { withRouter } from 'react-router-dom';
import { Field, reduxForm } from "redux-form";
import { actionAgregarModulo } from '../../actions/actionsModulo.js';
import { connect } from 'react-redux';

class PopUpModulo extends React.Component {
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
    this.props.reset();
  }

  componentDidUpdate() {
    if (this.props.mensajeRegistrar !== '') {
      switch (this.props.mensaje) {
        case 'modulo registrado':
          NotificationManager.info('Modulo registrado correctamente');
          break;
        default:
          break;
      }
    }
  }

  static propTypes = {
    previewLogoUrl: PropTypes.string,
    tipoDeImagen: PropTypes.string,
    pesoMaximo: PropTypes.number,
    anchuraMaxima: PropTypes.number,
    alturaMaxima: PropTypes.number,
    handleSubmit: PropTypes.func.isRequired
  };

  static defaultProps = {
    previewLogoUrl: "https://imgplaceholder.com/400x300",
    tipoDeImagen: "image/jpeg, image/png",
    pesoMaximo: 100,
    anchuraMaxima: 100,
    alturaMaxima: 100
  };
  validateImageWeight = imageFile => {
    if (imageFile && imageFile.size) {
      const imageFileKb = imageFile.size / 1024;
      const { pesoMaximo } = this.props;

      if (imageFileKb > pesoMaximo) {
        return `El tamaño de la imagen debe ser menor o igual a ${pesoMaximo}kb`;
      }
    }
  };
  validateImageWidth = imageFile => {
    if (imageFile) {
      const { anchuraMaxima } = this.props;
      if (imageFile.width > anchuraMaxima) {
        return `El ancho de la imagen debe ser menor o igual a ${anchuraMaxima}px`;
      }
    }
  };
  validateImageHeight = imageFile => {
    if (imageFile) {
      const { alturaMaxima } = this.props;

      if (imageFile.height > alturaMaxima) {
        return `La altura de la imagen debe ser menor o igual a ${alturaMaxima}px`;
      }
    }
  };

  validateImageFormat = imageFile => {
    if (imageFile) {
      const { tipoDeImagen } = this.props;
      if (!tipoDeImagen.includes(imageFile.type)) {
        return `El tipo de imagen debe ser ${tipoDeImagen}`;
      }
    }
  };

  handlePreview = imageUrl => {
    const previewImageDom = document.querySelector(".preview-image");
    previewImageDom.src = imageUrl;
  };

  handleChange = (event, input) => {
    event.preventDefault();
    let imageFile = event.target.files[0];
    const { tipoDeImagen } = this.props;
    if (imageFile) {
      if (!tipoDeImagen.includes(imageFile.type)) {
        NotificationManager.error('Seleccione un archivo de imagen .jpg o .png');
        event.target.value = null;
      } else {

        const localImageUrl = URL.createObjectURL(imageFile);
        const imageObject = new window.Image();

        imageObject.onload = () => {
          imageFile.width = imageObject.naturalWidth;
          imageFile.height = imageObject.naturalHeight;
          input.onChange(imageFile);
          URL.revokeObjectURL(imageFile);
        };
        imageObject.src = localImageUrl;
        this.handlePreview(localImageUrl);
      }
    }
  };

  renderFileInput = ({ input, type, meta }) => {
    const { tipoDeImagen } = this.props;
    const { touched, error, warning } = meta;
    return (
      <div>
        <input
          name={input.name}
          type={type}
          accept={tipoDeImagen}
          onChange={event => this.handleChange(event, input)}
        />
        {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
      </div>
    );
  };


  handleSubmitForm = values => {
    if (!(values.image === undefined | values.image === null)) {
      this.getBase64(values.image, (result) => {
        let modulo = {
          nombreModulo: values.nombre,
          descripcionModulo: values.descripcion,
          imagenModulo: result,
          estadoModulo: 'Activo'
        }
        this.props.actionAgregarModulo(modulo, localStorage.getItem('Token'));
      });
    } else {
      NotificationManager.error('Seleccione un archivo de imagen .jpg o .png');
    }
  };

  getBase64(file, cb) {
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      cb(reader.result)
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };
  }

  render() {
    return (
      <>
        <Button color="danger" className="btn btn-dark letra" style={fondoBoton} onClick={this.toggle}>Crear modulo +</Button>
        <Modal isOpen={this.state.modal}
          toggle={this.toggle}
          style={{ width: "400px" }}
        >
          <ModalHeader toggle={this.toggle} style={{ height: "50px", width: "400px" }} className="center">Crear modulo</ModalHeader>
          <ModalBody>
            <div style={{ padding: "30px 30px 30px 77px" }}>
              <img src={Defecto} alt="preview"
                className="preview-image"
                style={{ height: "200px", width: "200px", borderRadius: "50%", objectFit: "cover" }} />
            </div>
            <form onSubmit={this.props.handleSubmit(this.handleSubmitForm)}>

              <Field
                name="image"
                type="file"
                validate={[
                  this.validateImageWeight,
                  this.validateImageWidth,
                  this.validateImageHeight,
                  this.validateImageFormat
                ]}
                component={this.renderFileInput}
              />
              <br />
              <div className="row">
                <div className="col-sm-12">
                  <Field name="nombre" validate={[requerido,validacionCuarentaCaracteres]} component={generarInput} label="Nombre" />
                </div>
              </div>
              <br />
              <div className="row">
                <div className="col-sm-12">
                  <Field name="descripcion" validate={[requerido,validacionDoscientosCaracteres]} component={renderTextArea} label="descripcion" />
                </div>
              </div>
              <ModalFooter>
                <Button
                  type="submit"
                  style={fondoBoton} >
                  Registrar
                </Button>
                <Button color="secondary" style={fondoBotonCancelar} className="letra" onClick={this.toggle}>Cancelar</Button>
              </ModalFooter>
            </form>
          </ModalBody>
        </Modal>
        <NotificationContainer />
      </>
    );
  }
}


const generarInput = ({ input, label, type, meta: { touched, error, warning } }) => (
  <div>
    <div>
      <input {...input} placeholder={label} type={type} style={{ height: "35px", fontSize: "12px" }} className="form-control letra placeholder-no-fix" />
      {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
)


const renderTextArea = ({ input, label, meta: { touched, error, warning } }) => (
  <div>
    <div>
      <textarea {...input} placeholder={label} style={{ fontSize: "12px" }} className="form-control letra form-control-solid placeholder-no-fix" />
      {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
);

const fondoBotonCancelar = {
  background: "gray",
  fontSize: "12px",
  fontFamily: "Open sans, sans-serif"

}

const fondoBoton = {
  background: "#ec671d",
  fontSize: "13px",
  fontFamily: "sans-serif"

}

function mapStateToProps(state) {
  return {
    mensaje: state.mod.mensajeRegistrarModulo
  }
}


let formularioModulo = reduxForm({
  form: "formularioModulo"
})(PopUpModulo);

export default withRouter(connect(mapStateToProps, { actionAgregarModulo })(formularioModulo));
