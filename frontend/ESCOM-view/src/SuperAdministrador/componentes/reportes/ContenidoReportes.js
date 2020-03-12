import React from 'react';

import 'react-notifications/lib/notifications.css';


import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import MaterialTable from 'material-table';
//componentes
import { generarInput, generarDate } from '../../utilitario/GenerarInputs.js'

import Barra from '../general/BarraDirecciones.js'
import { formatoFecha } from '../../utilitario/MensajesError.js';
import { seleccione } from '../../utilitario/validacionCampos.js';
import Select from 'react-select'
import SearchIcon from '@material-ui/icons/Search';
import ReplayIcon from '@material-ui/icons/Replay';
import PictureAsPdfIcon from '@material-ui/icons/PictureAsPdf';
import { connect } from 'react-redux';
import { actionConsultarReporte, actualizarReporte, actualizarMensaje } from '../../actions/actionReporte.js';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import jsPDF from "jspdf";
import "jspdf-autotable";

class ContenidoReportes extends React.Component {
	constructor(props) {
		super(props);
		this.reiniciar = this.reiniciar.bind(this);
		this.exportPDF = this.exportPDF.bind(this);
	}
	state = {
		valor: null,
		textoAyuda: 'Buscar',
		fechaInicio: [],
		fechaFin: []
	}
	retornarValor = () => {
		return this.state.valor;
	}

	handleChangeDos = selectedOption => {
		switch (selectedOption.value) {
			case 1:
				this.setState({ textoAyuda: 'Ingrese el correo o numero de identificacion del usuario' })
				break;
			case 2:
				this.setState({ textoAyuda: 'Ingrese el nombre del modulo ' })

				break;
			case 3:
				this.setState({ textoAyuda: 'Ingrese una palabra clave de la actividad' })

				break;
			default:
				this.setState({ textoAyuda: 'Buscar' })
				break
		}
		this.setState({ valor: selectedOption });
	};

	actividades = () => {
		let opciones = [];
		opciones[0] = {
			label: 'Usuarios',
			value: 1,
		}
		opciones[1] = {
			label: 'Modulos',
			value: 2,
		}
		opciones[2] = {
			label: 'Actividades',
			value: 3,
		}
		return opciones;
	}
	componentDidUpdate() {
		switch (this.props.mensaje) {
			case 'No se encontraron reportes':
				NotificationManager.warning('No se encontraron resultados');
				break;
			case 'Sin permiso':
				NotificationManager.warning('No tiene permisos para realizar reportes');
				break;
			default:
				break;

		}
		this.props.actualizarMensaje('');
	}



	handleSubmit = formValues => {
		this.props.actualizarReporte();
		this.props.actualizarMensaje('');
		if (this.validarFechas(formatoFecha(formValues.fechaInicio), formatoFecha(formValues.fechaFin))) {
			let reporte = {
				idBusqueda: formValues.actividad.value,
				palabraBusqueda: formValues.palabraBusqueda,
				fechaInicio: formatoFecha(formValues.fechaInicio),
				fechaFin: formatoFecha(formValues.fechaFin)
			}
			this.setState({ fechaInicio: formatoFecha(formValues.fechaInicio) });
			this.setState({ fechaFin: formatoFecha(formValues.fechaFin) });
			this.props.actionConsultarReporte(localStorage.getItem('Token'), reporte);
		}
	}

	validarFechas = (fechaInicio, fechaFin) => {
		if ((fechaInicio > fechaFin) | (fechaInicio.getTime() === fechaFin.getTime())) {
			NotificationManager.warning('Ingrese un rango de fechas valido');
			return false;
		} else {
			return true;
		}
	}

	reiniciar() {
		this.props.actualizarMensaje('');
		this.setState({ valor: null });
		this.setState({ textoAyuda: 'Buscar' })
		this.props.actualizarReporte();
		this.props.reset();
	}

	devolverFechaString = fechaRecibida => {
		let meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
		let dia = fechaRecibida.getDate();
		let mes = fechaRecibida.getUTCMonth();
		let ano = fechaRecibida.getFullYear();
		return `${dia}-${meses[mes]}-${ano}`;
	}

	calcularNumeroDePaginas() {
		if (!(this.props.reporte.length === 0 | this.props.reporte === undefined)) {
			return Math.round(this.props.reporte.length / 30) + 1;
		}
	}

	exportPDF = () => {
		if (this.props.reporte.length === 0 | this.props.reporte === undefined) {
			NotificationManager.error('No hay datos para exportar');
		}
		else {
			var doc = new jsPDF()
			var totalPagesExp = this.calcularNumeroDePaginas();
			const headers = [["OPERACION", "MODULO", "TABLA INVOLUCRADA", "FECHA BITACORA", "CORREO DEL RESPONSABLE", "IP         "]];
			const data = this.props.reporte.map(elt => [elt.operacion, elt.nombreModulo, elt.tablaInvolucrada, elt.fechaBitacora, elt.correo, elt.ip]);
			const logo = this.props.configuracion.logo;
			let fechaHoy = new Date();
			const fecha = this.devolverFechaString(fechaHoy);
			const fechaInicio = this.devolverFechaString(this.state.fechaInicio);
			const fechaFin = this.devolverFechaString(this.state.fechaFin);
			doc.autoTable({

				head: headers,
				body: data,
				headStyles: {
					fontSize: 8,
				},
				bodyStyles: {
					fontSize: 8,
				},
				alternateRowStyles: {
					fontSize: 8,
				},
				didDrawPage: function (data) {
					// Header
					doc.setFontSize(10);
					doc.setTextColor(40);
					doc.setFontStyle('normal');
					doc.addImage(logo, 'PNG', data.settings.margin.left, 8, 43, 15);
					doc.text('Fecha de generacion: ' + fecha, data.settings.margin.left + 125, 12);
					doc.text('Fecha de inicio: ' + fechaInicio, data.settings.margin.left + 125, 16);
					doc.text('Fecha de fin: ' + fechaFin, data.settings.margin.left + 125, 20);
					// Footer
					var str = 'Pagina ' + doc.internal.getNumberOfPages()
					// Total page number plugin only available in jspdf v1.0+
					if (typeof doc.putTotalPages === 'function') {
						str = str + ' de ' + totalPagesExp
					}
					doc.setFontSize(10)

					// jsPDF 1.4+ uses getWidth, <1.4 uses .width
					var pageSize = doc.internal.pageSize
					var pageHeight = pageSize.height ? pageSize.height : pageSize.getHeight()
					doc.text(str, data.settings.margin.left, pageHeight - 10)
				},
				margin: { top: 30 },
			})
			doc.save('myrepo.pdf');
		}
	}

	render() {
		return (
			<div>
				<div className="text-left titulo" style={estiloLetrero}>
					<h4>Reportes</h4>
				</div>
				<Barra texto="Inicio > Reportes" />
				<div className="col-sm-12" style={{
					paddingTop: "20px",
					paddingRight: "46px",
					paddingLeft: "40px",
					paddingBottom: "7px",
				}}>
				</div>
				<div className="container" style={{
					paddingTop: "7px",
					paddingRight: "44px",
					paddingLeft: "40px",
					paddingBottom: "20px",
					margin: "0px 0px 32px"
				}}>
					<div className="container shadow" style={{ background: "white", padding: "27px" }}>
						<form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
							<div className="row">
								<div className="col-sm-4" style={{ paddingTop: "15px" }}>
									<Field name="actividad" validate={[seleccione]} valor={this.retornarValor()} onChange={this.handleChangeDos} component={ReduxFormSelectDos} options={this.actividades()} />
								</div>
								<div className="col-sm-8">
									<Field name="palabraBusqueda" component={generarInput} className="form-control" label={this.state.textoAyuda} />
								</div>
							</div>
							<div className="col-sm-12" style={{
								paddingTop: "20px",
								paddingRight: "2px",
								paddingLeft: "15px",
								paddingBottom: "2px",
							}}>

								<div className="row">
									<div className="col-sm-6">

										<Field name="fechaInicio" type="date" component={generarDate} label="Fecha de inicio" />
									</div>
									<div className="col-sm-6">

										<Field name="fechaFin" type="date" component={generarDate} label="Fecha de fin" />
									</div>
								</div>
							</div>
							<div className="col-sm-12" style={{
								paddingTop: "20px",
								paddingRight: "2px",
								paddingLeft: "15px",
								paddingBottom: "2px",
							}}>
								<div className="row">
									<div className="col-sm-6" style={{ paddingLeft: "300px" }}>
										<Button type="submit"
											startIcon={<SearchIcon />}
											className="btn btn-dark"
											style={{ background: this.props.configuracion.botones, fontSize: "14px", textTransform: "none" }}
											variant="contained">Buscar</Button>
									</div>
									<div className="col-sm-6">
										<Button
											onClick={this.reiniciar}
											className="btn btn-dark"
											style={{ background: "gray", fontSize: "14px", textTransform: "none" }}
											variant="contained"><ReplayIcon /></Button>
									</div>
								</div>
							</div>
						</form>
						<br />
						<Divider variant="middle" />
						<br />

						<MaterialTable
							title=''
							localization={{
								header: {
									actions: ' '
								},
								pagination: {
									nextTooltip: 'Siguiente ',
									previousTooltip: 'Anterior',
									labelDisplayedRows: '{from}-{to} de {count}',
									lastTooltip: 'Ultima pagina',
									firstTooltip: 'Primera pagina',
									labelRowsSelect: 'Registros',
									firstAriaLabel: 'oooo'
								},
								body: {
									emptyDataSourceMessage: 'Ningun registro de bitacora encontrado'
								},
								toolbar: {
									searchTooltip: 'Buscar',
									searchPlaceholder: 'Buscar',
									nRowsSelected: '{0} actividades seleccionadas',
								}
							}}
							columns={[
								{ title: 'Operacion', field: 'operacion', headerStyle: estiloCabecera, cellStyle: estiloFila },
								{ title: 'Nombre del modulo', field: 'nombreModulo', headerStyle: estiloCabecera, cellStyle: estiloFila },
								{ title: 'Tabla involucrada', field: 'tablaInvolucrada', headerStyle: estiloCabecera, cellStyle: estiloFila },
								{ title: 'Fecha de bitacora', field: 'fechaBitacora', headerStyle: estiloCabecera, cellStyle: estiloFila },
								{ title: 'Correo del responsable', field: 'correo', headerStyle: estiloCabecera, cellStyle: estiloFila },
								{ title: 'IP', field: 'ip', headerStyle: estiloCabecera, cellStyle: estiloFila },
							]}
							data={this.props.reporte}
							options={{
								search: false,
								rowStyle: estiloFila
							}}
						/>
						<br />
						<div style={{ paddingLeft: "400px" }}>
							<Button
								onClick={() => this.exportPDF()}
								startIcon={<PictureAsPdfIcon />}
								className="btn btn-dark"
								style={{ background: "#DF1417", fontSize: "14px", textTransform: "none" }}
								variant="contained">Generar PDF</Button>
						</div>
					</div>

				</div>
				<NotificationContainer />
			</div >
		);
	}


}

export const ReduxFormSelectDos = props => {
	const customStyles = {
		option: (provided, state) => ({
			...provided,
			fontSize: 13
		}),
		control: styles => ({ ...styles, backgroundColor: 'white', fontSize: 13, fontFamily: 'sans-serif' }),
		singleValue: (provided, state) => {
			const opacity = state.isDisabled ? 0.5 : 1;
			const transition = 'opacity 300ms';
			return { ...provided, opacity, transition };
		}
	}
	const { input, options } = props;
	const { touched, error } = props.meta;
	return (
		<div>
			<Select
				{...input}
				styles={customStyles}
				maxMenuHeight={185}
				isSearchable={true}
				value={props.valor}
				placeholder='Seleccione'
				onChange={value => input.onChange(value)}
				onBlur={() => input.onBlur(input.value)}
				options={options}
				noOptionsMessage={() => 'No hay ninguna actividad que mostrar'}
			/>
			{touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
		</div>
	)
}

const estiloCabecera = {
	fontSize: '14px',
	fontFamily: 'sans-serif',
	padding: '8px',
	background: '#e7ecf1'

}

const estiloFila = {
	fontSize: '13px',
	fontFamily: 'sans-serif',
	padding: '8px',
}


const estiloLetrero = {
	paddingTop: "20px",
	paddingRight: "12px",
	paddingLeft: "40px",
	paddingBottom: "1px"
}

function mapStateToProps(state) {
	return {
		reporte: state.rep.reporte,
		mensaje: state.rep.mensajeReporte,
		configuracion: state.conf.configuracion
	}
}

let reporte = reduxForm({
	form: 'reporte'
})(ContenidoReportes)

export default withRouter(connect(mapStateToProps, { actionConsultarReporte, actualizarReporte, actualizarMensaje })(reporte));
