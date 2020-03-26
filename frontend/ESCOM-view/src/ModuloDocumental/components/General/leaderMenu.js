import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { getListProcesses } from '../../redux/actions/processA.js';
import { getConditionsPer } from '../../redux/actions/conditionA.js';


import '../../css/menu.css';

//imagenes
import home from '../../img/icono-home.png'
import condicion from '../../img/icono-condicion.png'
import modulo from '../../img/icono-folder.png'
import documento from '../../img/icono-reporte.png'
import logo from '../../img/logo.png'
import next from '../../img/next.png'


class MenuLateral extends Component {

	state = {
		hoverInicio: false,
		hoverUsuario: false,
		hoverActividad: false,
		hoverModulo: false,
		hoverReportes: false,
		visible: false
	}

	saveId(id) {
		sessionStorage.setItem('processP', id)
		this.props.getConditionsPer(localStorage.getItem('Token'), id)
	}

	componentDidMount() {
		this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
	}



	viewProcess() {
		return this.props.processes.map((process) => {
			return (
				<li className="nav-item " key={process.id} >
					<Link to="/ProcessProgram" onClick={() => this.saveId(process.id)} name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
						<img src={next} alt="" width="15" height="15" />
						<span className="title">{process.name}</span>
					</Link>
				</li>
			)
		})
	}

	hoverOn = (evento) => {
		this.setState({ [evento.target.name]: true });
	}

	hoverOff = (evento) => {
		this.setState({ [evento.target.name]: false });
	}

	render() {
		return (
			<div id="sidebar-wrapper" className="toggled" style={fondoMenuLateral}>
				<div className="col-sm" style={fondoMenuLateral}>
					<div className="container text-center" style={fondoMenuLateral}>
						<img src={logo} alt="" width="140" height="72" />
					</div>
				</div>


				<li className="nav-item">
					<Link to="/HomeDef" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
						<img src={home} alt="" width="25" height="25" />
						<br />
						<span className="title">INICIO</span>

					</Link>

				</li>

				<li className="nav-item" data-toggle="collapse" data-target="#collapseProceso" aria-expanded="false" aria-controls="collapseProceso">
					<Link name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverUsuario ? fondoHover : fondoMenuLateral}>
						<img src={condicion} alt="" width="25" height="25" />
						<br />
						<span className="title">PROCESO</span>
					</Link>
				</li>

				<div className="collapse" id="collapseProceso">
					{this.viewProcess()}
				</div>

				<li className="nav-item" data-toggle="collapse" data-target="#collapseConfig" aria-expanded="false" aria-controls="collapseConfig">
					<Link name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverModulo ? fondoHover : fondoMenuLateral}>
						<img src={modulo} alt="" width="25" height="25" />
						<br />
						<span className="title">CONFIGURACION</span>
					</Link>
				</li>
				<div className="collapse" id="collapseConfig">
					<li className="nav-item ">
						<Link to="/ListProcess" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
							<img src={next} alt="" width="15" height="15" />
							<span className="title">Procesos</span>
						</Link>
					</li>
					<li className="nav-item ">
						<Link to="/ListCondition" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
							<img src={next} alt="" width="15" height="15" />
							<span className="title">Condiciones</span>
						</Link>
					</li>
					<li className="nav-item ">
						<Link to="/ListAnnex" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
							<img src={next} alt="" width="15" height="15" />
							<span className="title">Anexos</span>
						</Link>
					</li>
					<li className="nav-item ">
						<Link to="/" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
							<img src={next} alt="" width="15" height="15" />
							<span className="title">Cronograma</span>
						</Link>
					</li>
				</div>

				<li className="nav-item" data-toggle="collapse" data-target="#collapseDoc" aria-expanded="false" aria-controls="collapseDoc">
					<Link name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverReportes ? fondoHover : fondoMenuLateral}>
						<img src={documento} alt="" width="25" height="25" />
						<br />
						<span className="title letra">DOCUMENTACION</span>
					</Link>
				</li>

				<div className="collapse" id="collapseDoc">
					<li className="nav-item ">
						<Link to="/SearchAnnexs" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
							<img src={next} alt="" width="15" height="15" />
							<span className="title">Busqueda anexos</span>
						</Link>
					</li>
					<li className="nav-item ">
						<Link to="/VersionDocument" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
							style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
							<img src={next} alt="" width="15" height="15" />
							<span className="title">versiones documento</span>
						</Link>
					</li>
				</div>

				<li className="nav-item">
					<Link to="/UserCondition" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
						<img src={home} alt="" width="25" height="25" />
						<br />
						<span className="title">INFORMACION</span>
					</Link>
				</li>

				<li className="nav-item">
					<Link to="/Classification" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
						<img src={home} alt="" width="25" height="25" />
						<br />
						<span className="title">CLASIFICACION</span>
					</Link>
				</li>

			</div>

		)
	}
}

const fondoMenuLateral = {
	background: "#26344B",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

}

const fondoHover = {
	background: "#2b3b55",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

}

function mapStateToProps(state) {
	return {
		processes: state.process.listProcessR,
		documentIdG: state.document.documentIdG
	}
}

export default connect(mapStateToProps, { getListProcesses, getConditionsPer })(MenuLateral)