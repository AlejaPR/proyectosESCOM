import React from 'react';

import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import MaterialTable from 'material-table';
//componentes
import { generarInput } from '../../utilitario/GenerarInputs.js'

import Barra from '../general/BarraDirecciones.js'
import { formatoFecha } from '../../utilitario/MensajesError.js';
import { seleccione } from '../../utilitario/validacionCampos.js';
import Select from 'react-select'
import SearchIcon from '@material-ui/icons/Search';
import ReplayIcon from '@material-ui/icons/Replay';
import PictureAsPdfIcon from '@material-ui/icons/PictureAsPdf';
import { connect } from 'react-redux';
import { actionConsultarReporte } from '../../actions/actionReporte.js'
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import jsPDF from "jspdf";
import "jspdf-autotable";

class ContenidoReportes extends React.Component {
	constructor(props) {
		super(props);
		this.reiniciar = this.reiniciar.bind(this);
		this.exportPDF=this.exportPDF.bind(this);
	}
	state = {
		valor: null,
		logo:''
	}
	retornarValor = () => {
		return this.state.valor;
	}
	handleChangeDos = selectedOption => {
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
		// console.log('conf', this.props.configuracion)
		if(this.state.logo!==''){this.setState({logo:this.props.configuracion.logo})}
	}

	handleSubmit = formValues => {
		let reporte = {
			idBusqueda: formValues.actividad.value,
			palabraBusqueda: formValues.palabraBusqueda,
			fechaInicio: formatoFecha(formValues.fechaInicio),
			fechaFin: formatoFecha(formValues.fechaFin)
		}
		this.props.actionConsultarReporte(localStorage.getItem('Token'), reporte);
	}

	reiniciar() {
		this.setState({ valor: null })
		this.props.reset();
	}

	exportPDF = () => {
		var doc = new jsPDF()
		var totalPagesExp = '{total_pages_count_string}'
		const headers = [["OPERACION", "MODULO", "TABLA INVOLUCRADA", "FECHA BITACORA"]];
		const data = this.props.reporte.map(elt => [elt.operacion, elt.nombreModulo, elt.tablaInvolucrada, elt.fechaBitacora]);
		const logo=this.props.configuracion.logo;
		doc.autoTable({
			head: headers,
			body: data,
			didDrawPage: function (data) {
				// Header
				doc.setFontSize(5)
				doc.setTextColor(40)
				doc.setFontStyle('normal')

				
				doc.addImage(logo, 'PNG', data.settings.margin.left, 14, 43, 15)

				doc.text('', data.settings.margin.left + 35, 22)
				
				// Footer
				var str = 'Pagina ' + doc.internal.getNumberOfPages()
				// Total page number plugin only available in jspdf v1.0+
				if (typeof doc.putTotalPages === 'function') {
					str = str + ' of ' + totalPagesExp
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
							<div className="input-group">
								<div className="col-sm-4">
									<Field name="actividad" validate={[seleccione]} valor={this.retornarValor()} onChange={this.handleChangeDos} component={ReduxFormSelectDos} options={this.actividades()} />
								</div>
								<div className="col-sm-8">
									<Field name="palabraBusqueda" component={generarInput} className="form-control" label="Buscar" />
								</div>
							</div>
							<div className="col-sm-12" style={{
								paddingTop: "20px",
								paddingRight: "2px",
								paddingLeft: "15px",
								paddingBottom: "2px",
							}}>

								<div className="row">
									<label for="form_control_3">Fecha inicial</label>
									<div className="col-sm-5">
										<Field name="fechaInicio" type="date" component={generarInput} style={{ height: "35px" }} className="form-control" />
									</div>
									<label for="form_control_3">Fecha final</label>
									<div className="col-sm-5">
										<Field name="fechaFin" type="date" component={generarInput} style={{ height: "35px" }} className="form-control" />
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
									emptyDataSourceMessage: 'Ningun registro de actividad encontrado'
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

			</div >
		);
	}


}

export const ReduxFormSelectDos = props => {
	const { input, options } = props;
	const { touched, error } = props.meta;
	return (
		<div>
			<Select
				{...input}
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


const fondoBoton = {
	background: "#ec671d",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

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

export default withRouter(connect(mapStateToProps, { actionConsultarReporte })(reporte));
