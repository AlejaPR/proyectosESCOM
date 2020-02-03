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
import { nombre, requerido, apellido} from '../../utilitario/validacionCampos.js';
import Alerta from '@icons/material/AlertIcon.js';
import { campo } from '../../utilitario/GenerarInputs.js';
import PropTypes from "prop-types";
//redux
import { actionCargarInformacionDeModulo,actionEditarModulo, actionConsultarActividadesModulo } from '../../actions/actionsModulo.js'
import { connect } from "react-redux";
import { reduxForm, Field } from 'redux-form';


class EditarModulo extends React.Component {

    state = {
        habilitado: false,
        actividadesSeleccionadas: []
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

            console.log('tipo', imageFile.type)

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
        const { touched, error, warning } = meta;
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
        console.log('values image',values.image);
        if(values.image===undefined){
            let modulo = {
                nombreModulo: values.nombre,
                descripcionModulo: values.descripcion,
                imagenModulo: campo(this.props.initialValues.imagen),
                estadoModulo: 'Activo'
            }
            this.props.actionEditarModulo(modulo,this.props.codigoModulo,localStorage.getItem('Token'));
            // this.props.actionAgregarModulo(modulo, localStorage.getItem('Token'));
        }else{
            this.getBase64(values.image, (result) => {
                let modulo = {
                    nombreModulo: values.nombre,
                    descripcionModulo: values.descripcion,
                    imagenModulo: result,
                    estadoModulo: 'Activo'
                }
                this.props.actionEditarModulo(modulo,this.props.codigoModulo,localStorage.getItem('Token'));
                // this.props.actionAgregarModulo(modulo, localStorage.getItem('Token'));
            });
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

    componentDidUpdate() {
        if (this.props.mensajeEditar === 'Sin permiso') {
            if (!this.state.habilitado) { this.setState({ habilitado: true }) };
            // this.props.history.push('/adminUsuario');
        }
        if (this.props.mensajeEditar === 'modulo editado') {
            NotificationManager.success('Informacion actualizada correctamente');
            this.props.history.push('/adminModulo');
        }
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
                                <>
                                    <div style={{ padding: "30px 30px 30px 77px" }}>
                                        <img src={campo(this.props.initialValues.imagen)} alt="preview"
                                            className="preview-image"
                                            style={{ height: "200px", width: "200px", borderRadius: "50%", objectFit: "cover" }} />
                                    </div>
                                    <form className="letra" onSubmit={this.props.handleSubmit(this.handleSubmitForm)}>
                                        <br />
                                        <label>Imagen</label>
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
                                                <Field name="descripcion" validate={[requerido, apellido]} component={renderTextArea} label="Apellido" />
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
                                    <NotificationContainer/>
                                </>
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

const renderTextArea = ({ input, meta: { touched, error, warning } }) => (
    <div>
        <div>
            <textarea {...input} style={{ fontSize: "12px" }} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
        </div>
    </div>
);

const estiloLetrero = {
    paddingTop: "20px",
    paddingRight: "12px",
    paddingLeft: "40px",
    paddingBottom: "1px"
}


const fondoBarraSuperior = {
    background: "#FFFFFF"

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
        mensajeEditar: state.mod.mensajeEditarModulo,
        initialValues: {
            nombre: state.mod.moduloEditar.nombreModulo,
            descripcion: state.mod.moduloEditar.descripcionModulo,
            imagen: state.mod.moduloEditar.imagenModulo,
        }
    }
}


let formularioEditar = reduxForm({
    form: 'editarModulo',
    enableReinitialize: true
})(EditarModulo)

export default withRouter(connect(mapStateToProps, { actionCargarInformacionDeModulo, actionEditarModulo,actionConsultarActividadesModulo })(formularioEditar));

