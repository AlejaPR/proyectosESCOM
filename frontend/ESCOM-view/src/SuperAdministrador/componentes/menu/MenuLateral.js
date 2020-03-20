import React from 'react';

//iconos
import Persona from '@material-ui/icons/Person';
import Folder from '@material-ui/icons/LocalLibrary';
import Bombillo from '@material-ui/icons/EmojiObjects';
import Libro from  '@material-ui/icons/Description';
import Configuracion from '@material-ui/icons/Palette';
import logoDefecto from '../../imagenes/defectoLogo.png';
import HomeIcon from '@material-ui/icons/Home';

import { connect } from 'react-redux';
import { consultarConfiguracion } from '../../actions/actionConfiguracion.js'

class MenuLateral extends React.Component {

	state={
		hoverInicio:false,
		hoverUsuario:false,
		hoverActividad:false,
		hoverModulo:false,
		hoverReportes:false
	}

	componentDidMount() {
		this.props.consultarConfiguracion(localStorage.getItem('Token'));
	}


	hoverOn=(evento)=>{
		this.setState({ [evento.target.name]: true });
	}

	hoverOff=(evento)=>{ 
		this.setState({ [evento.target.name]: false });    
	}

	fondoBarr=()=>{
		return( 
			this.props.configuracion.barraLateral===undefined?
			{
				background:'#0E3D38',
				fontSize: "14px",
				fontFamily: "Open sans, sans-serif"
			}:{
				background:this.props.configuracion.barraLateral,
				fontSize: "14px",
				fontFamily: "Open sans, sans-serif"
			}
			)
	}

	render() {
		return (
			<div  id="sidebar-wrapper" className="toggled" style={this.fondoBarr()}>
				<div className="col-sm" style={this.fondoBarr()}>
					<div className="container text-center" style={this.fondoBarr()}>
						{this.props.configuracion.logo===undefined?<img src={logoDefecto} alt="" width="130" height="50" />:<img src={this.props.configuracion.logo} alt="" width="130" height="50" />}
						
					</div>
				</div>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/inicio" name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					 style={this.fondoBarr()}>
						<HomeIcon />
						<br />
						<span className="title letra">Inicio</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/AdminUsuario" name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					 style={this.fondoBarr()}>
						<Persona />
						<br />
						<span className="title letra">Administrar usuario</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/AdminModulo" name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center" 
					style={this.fondoBarr()}>
						<Folder/>
						<br />
						<span className="title letra">Administrar modulo</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/AdminActividad" name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					style={this.fondoBarr()} >
						<Bombillo/>
						<br />
						<span className="title letra">Administrar actividad</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/reportes" name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff}   className="list-group-item list-group-item-action text-light text-center" 
					style={this.fondoBarr()}>
						<Libro/>
						<br />
						<span className="title letra">Reportes</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/configuracion" name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff}   className="list-group-item list-group-item-action text-light text-center" 
					style={this.fondoBarr()}>
						<Configuracion/>
						<br />
						<span className="title letra">Configuracion</span>
					</a>
				</li>
			</div>

		)
	}
}

function mapStateToProps(state) {
    return {
        configuracion:state.conf.configuracion
    }
}


export default connect(mapStateToProps, { consultarConfiguracion })(MenuLateral);