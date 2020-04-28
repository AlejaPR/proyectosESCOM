import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { getListProcesses } from '../../redux/actions/processA.js';
import { getConditionsPer } from '../../redux/actions/conditionA.js';
import { consultarConfiguracion } from '../../../SuperAdministrador/actions/actionConfiguracion.js';
import { addIdDocument } from '../../redux/actions/documentA.js';

import logoDefecto from '../../../SuperAdministrador/imagenes/defectoLogo.png';
import HomeIcon from '@material-ui/icons/Home';
import LibraryBooksIcon from '@material-ui/icons/LibraryBooks';
import SettingsIcon from '@material-ui/icons/Settings';
import AccountTreeIcon from '@material-ui/icons/AccountTree';
import ArchiveIcon from '@material-ui/icons/Archive';
import ViewListIcon from '@material-ui/icons/ViewList';


class MenuLateral extends Component {
	state = {
		hoverInicio: false,
		hoverUsuario: false,
		hoverActividad: false,
		hoverModulo: false,
		hoverReportes: false
	}

	saveId(id) {
		sessionStorage.setItem('processP', id)
		this.props.getConditionsPer(localStorage.getItem('Token'), id)
	}

	viewProcess() {
		return this.props.processes.map((process) => {
			return (
				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/ProcessProgram" onClick={() => this.saveId(process.id)} name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<span className="title letra">{process.name}</span>
					</Link>
				</li>
			)
		})
	}

	componentDidMount() {
		this.props.consultarConfiguracion(localStorage.getItem('Token'));
		this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))

	}


	hoverOn = (evento) => {
		this.setState({ [evento.target.name]: true });
	}

	hoverOff = (evento) => {
		this.setState({ [evento.target.name]: false });
	}

	fondoBarr = () => {
		return (
			this.props.configuracion.barraLateral === undefined ?
				{
					background: '#0E3D38',
					fontSize: "14px",
					fontFamily: "Open sans, sans-serif"
				} : {
					background: this.props.configuracion.barraLateral,
					fontSize: "14px",
					fontFamily: "Open sans, sans-serif"
				}
		)
	}

	render() {
		return (
			<div id="sidebar-wrapper" className="toggled" style={this.fondoBarr()}>
				<div className="col-sm" style={this.fondoBarr()}>
					<div className="container text-center" style={this.fondoBarr()}>
						{this.props.configuracion.logo === undefined ? <img src={logoDefecto} alt="" width="130" height="50" /> : <img src={this.props.configuracion.logo} alt="" width="130" height="50" />}

					</div>
				</div>
				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/HomeDocumentary" onClick={() => this.props.addIdDocument(0)} name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<HomeIcon />
						<br />
						<span className="title letra">Inicio</span>
					</Link>
				</li>
				<li className="nav-item" style={{ height: "65px" }} data-toggle="collapse" data-target="#collapseProceso" aria-expanded="false" aria-controls="collapseProceso">
					<Link name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<AccountTreeIcon />
						<br />
						<span className="title letra">Procesos</span>
					</Link>
				</li>

				<div className="collapse" id="collapseProceso">
					{this.viewProcess()}
				</div>

				<li className="nav-item" style={{ height: "65px" }} data-toggle="collapse" data-target="#collapseConfig" aria-expanded="false" aria-controls="collapseProceso">
					<Link name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<SettingsIcon />
						<br />
						<span className="title letra">Configuración</span>
					</Link>
				</li>
				<div className="collapse" id="collapseConfig">
					<li className="nav-item" style={{ height: "65px" }}>
						<Link to="/ListProcess" name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.fondoBarr()}>
							<span className="title letra">Procesos</span>
						</Link>
					</li>
					<li className="nav-item" style={{ height: "65px" }}>
						<Link to="/ListCondition" name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.fondoBarr()}>
							<span className="title letra">Condiciones</span>
						</Link>
					</li>
					<li className="nav-item" style={{ height: "65px" }}>
						<Link to="/ListAnnex" name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.fondoBarr()}>
							<span className="title letra">Anexos</span>
						</Link>
					</li>
					<li className="nav-item" style={{ height: "65px" }}>
						<Link to="/ViewCalendar" name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.fondoBarr()}>
							<span className="title letra">Cronograma</span>
						</Link>
					</li>

				</div>

				<li className="nav-item" style={{ height: "65px" }} data-toggle="collapse" data-target="#collapseDoc" aria-expanded="false" aria-controls="collapseDoc">
					<Link name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()} >
						<ArchiveIcon />
						<br />
						<span className="title letra">Documentación</span>
					</Link>
				</li>

				<div className="collapse" id="collapseDoc">
					<li className="nav-item" style={{ height: "65px" }}>
						<Link to="/SearchAnnexs" name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.fondoBarr()} >
							<span className="title letra">Búsqueda anexos</span>
						</Link>
					</li>
					<li className="nav-item" style={{ height: "65px" }}>
						<Link to="/VersionDocument" name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.fondoBarr()} >
							<span className="title letra">Versiones Documento</span>
						</Link>
					</li>
				</div>

				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/UserCondition" name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<LibraryBooksIcon />
						<br />
						<span className="title letra">Digitación documento</span>
					</Link>
				</li>
				{/*
				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/Classification" name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<AmpStoriesIcon />
						<br />
						<span className="title letra">Clasificación Documental</span>
					</Link>
				</li>
				*/
				}
				<li className="nav-item" style={{ height: "65px" }} data-toggle="collapse" data-target="#collapseMatrix" aria-expanded="false" aria-controls="collapseMatrix">
					<Link name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<ViewListIcon />
						<br />
						<span className="title letra">Matrices</span>
					</Link>
				</li>
				<div className="collapse" id="collapseMatrix">
					<li className="nav-item" style={{ height: "65px" }} data-toggle="collapse" >
						<Link to="/ItemList" name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.fondoBarr()} >
							<span className="title letra">Configuración</span>
						</Link>
					</li>
					<li className="nav-item" style={{ height: "65px" }} data-toggle="collapse" >
						<Link to="/ThematicList" name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.fondoBarr()} >
							<span className="title letra">Digitación</span>
						</Link>
					</li>
				</div>
			</div>

		)
	}
}

function mapStateToProps(state) {
	return {
		configuracion: state.conf.configuracion,
		processes: state.process.listProcessR,
		documentIdG: state.document.documentIdG
	}
}


export default connect(mapStateToProps, { getListProcesses, addIdDocument, getConditionsPer, consultarConfiguracion })(MenuLateral)