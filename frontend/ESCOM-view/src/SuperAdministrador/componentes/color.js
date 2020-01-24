import React from "react";
import PropTypes from "prop-types";
import { Field, reduxForm } from "redux-form";
import { Button } from "reactstrap";

class SimpleForm extends React.Component {
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
		}
	};
	renderFileInput = ({ input, type, meta }) => {
		const { mimeType } = this.props;
		return (
			<div>
				<input
					name={input.name}
					type={type}
					accept={mimeType}
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

		let idCardBase64 = '';
		this.getBase64(values.image, (result) => {
			console.log('result ',result);
			idCardBase64 = result;
		});
		console.log('base64',idCardBase64);
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
			previewLogoUrl,
			maxWidth,
			maxHeight,
			maxWeight,
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
