import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import { Button } from 'reactstrap';
import Divider from '@material-ui/core/Divider';
import MaterialTable from 'material-table';
//componentes
import { generarInput } from '../../utilitario/GenerarInputs.js'

import Barra from '../general/BarraDirecciones.js'
import { seleccione } from '../../utilitario/validacionCampos.js';
import Select from 'react-select'


import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';

class ContenidoReportes extends React.Component {

	state = {
		valor: null
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
	handleSubmit = formValues => {

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
					<div className="container shadow" style={{ background: "white", padding: "30px" }}>
						<form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
							<div className="input-group">
								<div className="col-sm-4">
									<Field name="actividad" validate={[seleccione]} valor={this.retornarValor()} onChange={this.handleChangeDos} component={ReduxFormSelectDos} options={this.actividades()} />
								</div>
								<div className="col-sm-7">
									<Field name="nombre" component={generarInput} className="form-control" label="Buscar" />
								</div>
								<div >
									<Button style={fondoBoton}>Buscar</Button>
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
										<input type="date" name="fechaNacimiento" style={{ height: "34px" }} id="user1" className="form-control" readonly />
									</div>
									<label for="form_control_3">Fecha final</label>
									<div className="col-sm-5">
										<input type="date" name="fechaNacimiento" style={{ height: "34px" }} id="user1" className="form-control" readonly />
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
								{ title: 'Nombre de la actividad', field: 'nombre', headerStyle: estiloCabecera, cellStyle: estiloFila }
							]}
							data={this.props.actividades}
							options={{
								search: false,
								rowStyle: estiloFila,
								selection: false
							}}
							onSelectionChange={(rows) => {
								this.setState({ actividadesSeleccionadas: rows });
							}}

							actions={[
								{
									tooltip: 'Eliminar actividades seleccionadas',
									icon: 'delete',
									onClick: (evt, data) => this.props.actionEliminarActividades(this.state.actividadesSeleccionadas, localStorage.getItem('Token'), this.props.cedula)
								}
							]}
						/>
						<br />
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

	}
}

let reporte = reduxForm({
	form: 'reporte'
})(ContenidoReportes)

export default withRouter(connect(mapStateToProps, {})(reporte));
