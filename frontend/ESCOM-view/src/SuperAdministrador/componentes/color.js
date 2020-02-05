import React from "react";
import PropTypes from "prop-types";
import { Field, reduxForm } from "redux-form";
import { Button } from "reactstrap";

class SimpleForm extends React.Component {
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
		const { mimeType } = this.props;
		if (!mimeType.includes(imageFile.type)) {
			console.log('Seleccione un archivo de imagen jpg o png');
			event.target.value = null;
		} else {
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
			}
		}

	};
	renderFileInput = ({ input, type, meta }) => {
		const { mimeType } = this.props;
		return (
			<div>
				<input
					name={input.name}
					type={type}
					accept={'image/*'}
					onChange={event => this.handleChange(event, input)}
				/>
				{meta && meta.invalid && meta.error && (
					<div >{meta.error}</div>
				)}
			</div>
		);
	};
	handleSubmitForm = values => {
		console.log("Form Values: ", values);
		if (!(values.image === undefined | values.image === null)) {
			let idCardBase64 = '';
			this.getBase64(values.image, (result) => {
				console.log('result ', result);
				idCardBase64 = result;
			});
			console.log('base64', idCardBase64);
		} else {
			console.log('seleccione una imagen .jpg o .png')
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
		const {
			handleSubmit
		} = this.props;
		return (
			<>
				<form>
					<Field
						name="image"
						type="file"
						validate={[
							this.validateImageFormat
						]}
						component={this.renderFileInput}
					/>
					<Button
						primary
						type="submit"
						className="form-submit-button"
						onClick={handleSubmit(this.handleSubmitForm)}
					>
						Submit
                </Button>
				</form>
				{/* <img style={{width:}} src={base64}/> */}
			</>
		);
	}
}

export default reduxForm({
	form: "simple"
})(SimpleForm);