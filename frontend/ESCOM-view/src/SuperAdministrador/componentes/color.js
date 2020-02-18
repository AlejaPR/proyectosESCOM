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
import Defecto from '../imagenes/defecto.jpg';

class Configuracion extends React.Component {

	state = {
		activeStep: 0,
		completed: {}
	}

	getSteps() {
		return ['Color barra superior', 'Color barra lateral', 'Color de botones', 'Imagen del login', 'Imagen del logo'];
	}

	completedSteps = () => {
		return Object.keys(this.state.completed).length;
	};

	handleChangeComplete = (color) => {
        // this.props.actionActualizarBarraLateral(color.hex);
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
				return (<SketchPicker disableAlpha={true} color="#7D8A62" onChangeComplete={this.handleChangeComplete} />)
			case 1:
				return (<SketchPicker disableAlpha={true} color="#7D8362" onChangeComplete={this.handleChangeCompleteSuperior}/>)
			case 2:
				return (<SketchPicker disableAlpha={true} onChangeComplete={this.handleChangeCompleteBotones} />);
			case 3:
				return (<>
					<div style={{ padding: "30px 30px 30px 77px" }}>
						<img src={Defecto} alt="preview"
							className="preview-image"
							style={{ height: "200px", width: "200px", borderRadius: "50%", objectFit: "cover" }} />
					</div>
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
				</>);
			case 4:
				return (
					<>
						<div style={{ padding: "30px 30px 30px 77px" }}>
							<img src={Defecto} alt="preview"
								className="preview-image"
								style={{ height: "200px", width: "200px", borderRadius: "50%", objectFit: "cover" }} />
						</div>
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
		console.log('formvalues',values);
	}


	render() {
		return (
			<>
			{this.getStepContent(0)}
			</>
		);
	}
}

let formularioModulo = reduxForm({
	form: "pruebaColor"
})(Configuracion);

export default formularioModulo;
