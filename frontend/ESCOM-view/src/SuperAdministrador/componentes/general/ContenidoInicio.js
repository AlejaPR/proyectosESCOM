import React from 'react';

import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import MaterialTable from 'material-table';
import { withRouter } from 'react-router-dom';

import { actionConsultarModulosAcceso } from '../../actions/actionsUsuario.js'
import { connect } from 'react-redux';

class ContenidoInicio extends React.Component {

	componentDidUpdate() {
		console.log('modiulos',this.props.modulosAcceso);
	}

	componentDidMount() {
		this.props.actionConsultarModulosAcceso(localStorage.getItem('Token'));
	}

	onClickCancelar = (event) => {
		event.preventDefault();
		console.log('history', this.props.history);
		this.props.history.push(this.props.modulosAcceso.url);
	}

	render() {
		return (
			<>
			
				<Modal isOpen={true}
					toggle={this.toggle}
					className={this.props.className}
					size="col-md-6"
				>
					<ModalBody>
						<MaterialTable
							title="Modulos disponibles"
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
									emptyDataSourceMessage: 'Aun no hay ningun usuario registrado'
								},
								toolbar: {
									searchTooltip: 'Buscar',
									searchPlaceholder: 'Buscar'
								}
							}}
							columns={[
								{ title: '', field: 'nombreModulo', headerStyle: estiloCabecera, cellStyle: estiloFila }
							]}
							data={this.props.modulosAcceso}
							options={{
								search: false,
								rowStyle: estiloFila
							}}
							actions={[
								{
									icon: 'subdirectory_arrow_right',
									tooltip: 'Ir',
									onClick: (event, rowData) => {
										this.onClickCancelar(event);
									}
								}
							]}
						/>
						<ModalFooter>

						</ModalFooter>

					</ModalBody>
				</Modal>
			</>
		);
	}


}

const estiloCabecera = {
	fontSize: '13px',
	fontFamily: 'sans-serif',
	padding: '8px',
	background: 'white'

}

const estiloFila = {
	fontSize: '12px',
	fontFamily: 'sans-serif',
	padding: '8px',
}


function mapStateToProps(state) {
	return {
		configuracion: state.conf.configuracion,
		modulosAcceso: state.user.modulosAcceso
	}
}

export default withRouter(connect(mapStateToProps, { actionConsultarModulosAcceso })(ContenidoInicio));