import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';


import '../../css/menu.css';

//imagenes
import home from '../../img/icono-home.png'
import logo from '../../img/logo.png'


class MenuLateral extends Component {

	state = {
		hoverInicio: false,
		hoverUsuario: false,
		hoverActividad: false,
		hoverModulo: false,
		hoverReportes: false,
		visible: false
	}


	componentWillMount() {

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
					<Link to="/" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
						<img src={home} alt="" width="25" height="25" />
						<br />
						<span className="title">INICIO</span>
					</Link>
				</li>

                <li className="nav-item">
					<Link to="/ListProgram" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
						<img src={home} alt="" width="25" height="25" />
						<br />
						<span className="title">PROGRAMAS</span>
					</Link>
				</li>

                <li className="nav-item">
					<Link to="/ListDocument" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
						<img src={home} alt="" width="25" height="25" />
						<br />
						<span className="title">DOCUMENTOS</span>
					</Link>
				</li>

				<li className="nav-item">
					<Link to="/Prueba" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.state.hoverInicio ? fondoHover : fondoMenuLateral} >
						<img src={home} alt="" width="25" height="25" />
						<br />
						<span className="title">PRUEBA</span>
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

    }
}

export default connect(mapStateToProps, { })(MenuLateral)