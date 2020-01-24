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
import { NotificationContainer } from 'react-notifications';
import Defecto from '../../imagenes/defecto.jpg';
import PropTypes from "prop-types";
import { Field, reduxForm } from "redux-form";

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
  }

  static propTypes = {
    previewLogoUrl: PropTypes.string,
    mimeType: PropTypes.string,
    maxWeight: PropTypes.number,
    maxWidth: PropTypes.number,
    maxHeight: PropTypes.number,
    // redux-form porps
    handleSubmit: PropTypes.func.isRequired
  };
  static defaultProps = {
    previewLogoUrl: "https://imgplaceholder.com/400x300",
    mimeType: "image/jpeg, image/png",
    maxWeight: 100,
    maxWidth: 100,
    maxHeight: 100
  };
  validateImageWeight = imageFile => {
    if (imageFile && imageFile.size) {
      // Get image size in kilobytes
      const imageFileKb = imageFile.size / 1024;
      const { maxWeight } = this.props;

      if (imageFileKb > maxWeight) {
        return `Image size must be less or equal to ${maxWeight}kb`;
      }
    }
  };
  validateImageWidth = imageFile => {
    if (imageFile) {
      const { maxWidth } = this.props;

      if (imageFile.width > maxWidth) {
        return `Image width must be less or equal to ${maxWidth}px`;
      }
    }
  };
  validateImageHeight = imageFile => {
    if (imageFile) {
      const { maxHeight } = this.props;

      if (imageFile.height > maxHeight) {
        return `Image height must be less or equal to ${maxHeight}px`;
      }
    }
  };
  validateImageFormat = imageFile => {
    if (imageFile) {
      const { mimeType } = this.props;

      if (!mimeType.includes(imageFile.type)) {
        return `Image mime type must be ${mimeType}`;
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
    if (imageFile) {
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
  };
  renderFileInput = ({ input, type, meta }) => {
    const { mimeType } = this.props;
    const { touched, error, warning }=meta;
    return (
      <div>
        <input
          name={input.name}
          type={type}
          accept={mimeType}
          onChange={event => this.handleChange(event, input)}
        />
         {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
      </div>
    );
  };
  handleSubmitForm = values => {
    console.log("Form Values: ", values);
  };
  render() {
    const {
      previewLogoUrl,
      maxWidth,
      maxHeight,
      maxWeight,
      handleSubmit
    } = this.props;
    return (
      <>
        <Button color="danger" className="btn btn-dark letra" style={fondoBoton} onClick={this.toggle}>Crear modulo +</Button>
        <Modal isOpen={this.state.modal}
          toggle={this.toggle}
          style={{ width: "400px" }}
        >
          <ModalHeader toggle={this.toggle} style={{ height: "50px", width: "400px" }} className="center">Crear modulo</ModalHeader>
          <ModalBody>
            <div style={{padding:"30px 30px 30px 77px"}}>
            <img src={Defecto} alt="preview"
              className="preview-image"
              style={{ height: "200px",width:"200px",borderRadius:"50%", objectFit: "cover" }} />
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
              <br/>
              <div className="row">
                <div className="col-sm-12">
                  <Field name="nombre"  component={generarInput} label="Nombre" />
                </div>
              </div>
              <br/>
              <div className="row">
                <div className="col-sm-12">
                  <Field name="descripcion"  component={renderTextArea} label="descripcion" />
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

const renderTextArea = ({ input,label, meta: { touched, error, warning } }) => (
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


export default reduxForm({
  form: "formularioModulo"
})(PopUpModulo);
