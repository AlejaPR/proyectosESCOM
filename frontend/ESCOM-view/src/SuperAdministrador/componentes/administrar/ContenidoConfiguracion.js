import React from 'react';


import { makeStyles } from '@material-ui/core/styles';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepButton from '@material-ui/core/StepButton';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { SketchPicker } from 'react-color';
import { Field, reduxForm } from "redux-form";
import PropTypes from "prop-types";
import Barra from '../general/BarraDirecciones.js';
import { connect } from 'react-redux';
import { consultarConfiguracion, actionActualizarBarraLateral, actionActualizarBarraSuperior, actionActualizarBotones, actionConsultarConfiguracionCompleta } from '../../actions/actionConfiguracion.js'
import { campo } from '../../utilitario/GenerarInputs.js'

import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import NavigateNextIcon from '@material-ui/icons/NavigateNext';
import SaveIcon from '@material-ui/icons/Save';
import PhotoCamera from '@material-ui/icons/PhotoCamera';
import DoneIcon from '@material-ui/icons/Done';
import Alert from '@material-ui/lab/Alert';

class Configuracion extends React.Component {

    state = {
        activeStep: 0,
        completed: {},
        configuracion: null,
        imagenLogin: null,
        imagenLogo: null
    }

    componentDidMount() {
        this.props.actionConsultarConfiguracionCompleta(localStorage.getItem('Token'));
    }

    componentDidUpdate() {
        if (this.state.configuracion === null & this.props.configuracionCompleta[0] == !undefined) {
            console.log('props', this.props.configuracionCompleta[0]);

            // this.setState({ configuracion: this.props.configuracionCompleta[0] })
        }

    }

    getSteps() {
        return ['Color barra lateral', 'Color barra superior', 'Color de botones', 'Imagen del login', 'Imagen del logo'];
    }

    completedSteps = () => {
        return Object.keys(this.state.completed).length;
    };

    handleChangeComplete = (color) => {
        this.props.actionActualizarBarraLateral(color.hex);
    };

    handleChangeCompleteSuperior = (color) => {
        this.props.actionActualizarBarraSuperior(color.hex);
    };

    handleChangeCompleteBotones = (color) => {
        this.props.actionActualizarBotones(color.hex);
    };
    //
    getStepContent(step) {
        switch (step) {
            case 0:
                return (
                    <div style={{
                        paddingTop: '3px',
                        paddingBottom: '25px',
                        paddingLeft: '267px'
                    }}>
                        <SketchPicker disableAlpha={true} color={this.props.configuracionCompleta.barraLateral} onChangeComplete={this.handleChangeComplete} />
                    </div>
                )
            case 1:
                return (
                    <div style={{
                        paddingTop: '3px',
                        paddingBottom: '25px',
                        paddingLeft: '267px'
                    }}>
                        <SketchPicker disableAlpha={true} color={this.props.configuracionCompleta.barraSuperior} onChangeComplete={this.handleChangeCompleteSuperior} />
                    </div>
                )
            case 2:
                return (
                    <div style={{
                        paddingTop: '3px',
                        paddingBottom: '25px',
                        paddingLeft: '267px'
                    }}>
                        <SketchPicker disableAlpha={true} color={this.props.configuracionCompleta.botones} onChangeComplete={this.handleChangeCompleteBotones} />
                    </div>
                );
            case 3:
                return (<>
                    <div style={{
                        paddingTop: '3px',
                        paddingBottom: '25px',
                        paddingLeft: '57px'
                    }}>
                        <img src={campo(this.props.configuracionCompleta.imagenLogin)} alt="preview"
                            className="preview-image"
                            style={{ height: "780px", width: "750px", objectFit: "cover" }} />
                        <Field
                            name="image"
                            type="file"
                            validate={[
                                this.validateImageWeight,
                                this.validarAnchoImagenLogin,
                                this.validarAltoImagenLogin,
                                this.validateImageFormat
                            ]}
                            component={this.renderFileInput}
                        />
                    </div>

                </>);
            case 4:
                return (
                    <>
                        <div style={{
                            paddingTop: '3px',
                            paddingBottom: '25px',
                            paddingLeft: '241px'
                        }}>
                            <img src={campo(this.props.configuracionCompleta.logo)} alt="preview"
                                className="preview-image-dos"
                                style={{ height: "170px", width: "400px", borderRadius: "50%", objectFit: "cover" }} />
                            <Field
                                name="imagenDos"
                                type="file"
                                validate={[
                                    this.validateImageWeight,
                                    this.validarAnchoImagenLogo,
                                    this.validarAltoImagenLogo,
                                    this.validateImageFormat
                                ]}
                                component={this.renderFileInputLogo}
                            />
                        </div>

                    </>)

            default:
                return 'Unknown step';
        }
    }

    allStepsCompleted = () => {
        return this.completedSteps() === this.totalSteps();
    };

    totalSteps = () => {
        return this.getSteps().length;
    };

    isLastStep = () => {
        return this.state.activeStep === this.totalSteps() - 1;
    };

    useStyles = makeStyles(theme => ({
        root: {
            width: '100%',
        },
        button: {
            marginRight: theme.spacing(1),
        },
        completed: {
            display: 'inline-block',
        },
        instructions: {
            marginTop: theme.spacing(1),
            marginBottom: theme.spacing(1),
        },
    }));

    handleReset = () => {
        this.setState({ activeStep: 0, completed: {} })
    };

    handleComplete = () => {
        const newCompleted = this.state.completed;
        newCompleted[this.state.activeStep] = true;
        this.setState({ completed: newCompleted })
        this.handleNext();
    };

    handleNext = () => {
        const newActiveStep =
            this.isLastStep() && !this.allStepsCompleted()
                ? // It's the last step, but not all steps have been completed,
                // find the first step that has been completed
                this.getSteps().findIndex((step, i) => !(i in this.state.completed))
                : this.state.activeStep + 1;
        this.setState({ activeStep: newActiveStep })
    };

    handleStep = step => () => {
        this.setState({ activeStep: step })
    };

    handleBack = () => {
        let cuenta = this.state.activeStep - 1;
        this.setState({ activeStep: cuenta })
    };

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
                return `El peso de la imagen debe ser menor o igual a ${pesoMaximo}kb`;
            }
        }
    };
    validarAnchoImagenLogin = imageFile => {
        if (imageFile) {
            if (imageFile.width > 750) {
                return `El ancho de la imagen debe ser menor o igual a 750px`;
            }
        }
    };

    validarAltoImagenLogin = imageFile => {
        if (imageFile) {
            if (imageFile.height > 780) {
                return `La altura de la imagen debe ser menor o igual a 780px`;
            }
        }
    };

    validarAnchoImagenLogo = imageFile => {
        if (imageFile) {
            if (imageFile.width > 400) {
                return `El ancho de la imagen debe ser menor o igual a 400px`;
            }
        }
    };

    validarAltoImagenLogo = imageFile => {
        if (imageFile) {
            if (imageFile.height > 170) {
                return `La altura de la imagen debe ser menor o igual a 170px`;
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
                // NotificationManager.error('Seleccione un archivo de imagen .jpg o .png');
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

    handlePreviewLogo = imageUrl => {
        const previewImageDom = document.querySelector(".preview-image-dos");
        previewImageDom.src = imageUrl;
    };

    handleChangeLogo = (event, input) => {
        event.preventDefault();
        let imageFile = event.target.files[0];
        const { tipoDeImagen } = this.props;
        if (imageFile) {
            if (!tipoDeImagen.includes(imageFile.type)) {
                // NotificationManager.error('Seleccione un archivo de imagen .jpg o .png');
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
                this.handlePreviewLogo(localImageUrl);
            }
        }
    };


    renderFileInput = ({ input, type, meta }) => {
        const { tipoDeImagen } = this.props;
        const { touched, error, warning } = meta;
        return (
            <div>

                <input
                    id="numeroUno"
                    style={{ display: 'none' }}
                    name={input.name}
                    type={type}
                    accept={tipoDeImagen}
                    onChange={event => this.handleChange(event, input)}
                />
                <label htmlFor="numeroUno">
                    <Button component="span" startIcon={<PhotoCamera />}>Seleccionar imagen</Button>
                </label>
                {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
            </div>
        );
    };

    renderFileInputLogo = ({ input, type, meta }) => {
        const { tipoDeImagen } = this.props;
        const { touched, error, warning } = meta;
        return (
            <div>
                <input
                    id="numeroDos"
                    style={{ display: 'none' }}
                    name={input.name}
                    type={type}
                    accept={tipoDeImagen}
                    onChange={event => this.handleChangeLogo(event, input)}
                />
                <label htmlFor="numeroDos">
                    <Button component="span" startIcon={<PhotoCamera />}>Seleccionar imagen</Button>
                </label>
                {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
            </div>
        );
    };

    handleSubmitForm = values => {
        if(this.completedSteps()+1===6){
            debugger;
        }
        // console.log('formvalues', this.completedSteps(), ' values ', values);
    }


    render() {
        return (
            <>
                <div className="text-left titulo" style={estiloLetrero}>
                    <h4>Configuracion de aspecto</h4>
                </div>
                <Barra texto="Inicio > Configuracion de aspecto" />
                <div className="col-sm-12" style={{
                    paddingTop: "20px",
                    paddingRight: "46px",
                    paddingLeft: "40px",
                    paddingBottom: "7px"
                }}>
                    <div className="container shadow" style={{ background: "white" }} >
                        <div>
                            <Stepper activeStep={this.state.activeStep}>
                                {this.getSteps().map((label, index) => (
                                    <Step key={label}>
                                        <StepButton onClick={this.handleStep(index)} completed={this.state.completed[index]}>
                                            {label}
                                        </StepButton>
                                    </Step>
                                ))}
                            </Stepper>

                            <div className="container" style={{
                                paddingTop: "7px",
                                paddingRight: "12px",
                                paddingLeft: "40px",
                                paddingBottom: "20px",
                                margin: "0px 0px 32px"
                            }}>
                                <form onSubmit={this.props.handleSubmit(this.handleSubmitForm)}>
                                    {this.allStepsCompleted() ? (
                                        <div>
                                            <Typography className={this.useStyles.instructions}>
                                                Todos los cambios han sido guardados
            </Typography>
                                            <Button onClick={this.handleReset}>Aceptar</Button>
                                        </div>
                                    ) : (
                                            <div>
                                                <Typography className={this.useStyles.instructions}>{this.getStepContent(this.state.activeStep)}</Typography>
                                                <div className="row">
                                                    <div className="col-md-4">
                                                        <Button disabled={this.state.activeStep === 0} startIcon={<ArrowBackIosIcon />} onClick={this.handleBack} className={this.useStyles.button}>
                                                            Volver
                                                        </Button>
                                                    </div>
                                                    <div className="col-md-4">
                                                        <Button
                                                            variant="contained"
                                                            color="primary"
                                                            onClick={this.handleNext}
                                                            startIcon={<NavigateNextIcon />}
                                                            className={this.useStyles.button}>
                                                            Siguiente
                                                        </Button>
                                                    </div>
                                                    {this.state.activeStep !== this.getSteps().length &&
                                                        (this.state.completed[this.state.activeStep] ? (
                                                            <div className="col-md-4">
                                                                <Alert severity="success">Paso {this.state.activeStep + 1} guardado</Alert>
                                                                {/* <Typography variant="caption" className={this.useStyles.completed}>
                                                                <DoneIcon/>Paso {this.state.activeStep + 1} guardado
                                                            </Typography> */}
                                                            </div>
                                                        ) : (
                                                                <div className="col-md-4">
                                                                    <Button variant="contained" 
                                                                        type="submit"
                                                                        startIcon={<SaveIcon />}
                                                                        color="primary" onClick={this.handleComplete}>
                                                                        {this.completedSteps() === this.totalSteps() - 1 ? 'Confirmar cambios' : 'Guardar'}
                                                                    </Button>
                                                                </div>

                                                            ))}
                                                </div>
                                            </div>
                                        )}
                                </form>
                            </div>
                        </div>

                    </div >
                </div>
            </>
        );
    }
}
const estiloLetrero = {
    paddingTop: "20px",
    paddingRight: "12px",
    paddingLeft: "40px",
    paddingBottom: "1px"
}

function mapStateToProps(state) {
    return {
        configuracionCompleta: state.conf.configuracion,
        mensaje: state.conf.mensaje
    }
}

let formularioConfiguracion = reduxForm({
    form: "formularioConfiguracion"
})(Configuracion);


export default connect(mapStateToProps, { consultarConfiguracion, actionActualizarBarraLateral, actionActualizarBarraSuperior, actionActualizarBotones, actionConsultarConfiguracionCompleta })(formularioConfiguracion);

