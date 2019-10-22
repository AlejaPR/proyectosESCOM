import React from 'react';

//Menu lateral, superior y contenido de inicio
import MenuSuperior from "./SuperAdministrador/componentes/menu/MenuBlancoSuperior.js"
import MenuLateral from "./SuperAdministrador/componentes/menu/MenuLateral.js"
import RedireccionarLogin from "./SuperAdministrador/componentes/redirecciones/RedireccionarLogin.js"
import Inicio from "./SuperAdministrador/componentes/general/ContenidoInicio.js"


//administrar usuario
import EditarUsuario from './SuperAdministrador/componentes/editar/editarUsuario.js'
import AsignarActividadUsuario from './SuperAdministrador/componentes/asignar/AsignarActividadUsuario.js'

//administrar modulo
import AdminModulo from './SuperAdministrador/componentes/administrar/ContenidoAdminModulo.js'
import EditarModulo from './SuperAdministrador/componentes/editar/editarModulo.js';
import AsignarActividadModulo from './SuperAdministrador/componentes/asignar/asignarActividadModulo.js';

import Color from './SuperAdministrador/componentes/color.js';
import Configuracion from './SuperAdministrador/componentes/administrar/ContenidoConfiguracion.js';
//Administrar actividad
import Actividad from './SuperAdministrador/componentes/administrar/ContenidoAdminActividad.js'

import RedireccionarUsuario from './SuperAdministrador/componentes/redirecciones/RedireccionarUsuario.js';
import ContenidoReportes from './SuperAdministrador/componentes/reportes/ContenidoReportes.js';


//estilos
import './SuperAdministrador/css/business-casual.css'
import './SuperAdministrador/css/estilos.css'
import './SuperAdministrador/css/bootstrap.min.css'
import './SuperAdministrador/css/menu.css'

//rutas
import { BrowserRouter as Router, Route } from "react-router-dom"

//store
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import reducers from './SuperAdministrador/reducers';
import thunk from 'redux-thunk'

const createStoreWithMiddleware = applyMiddleware(thunk)(createStore);

class App extends React.Component {


	state = {
		usuarioModificar: ''
	}

	asignarUsuario = (usuario) => {
		console.log(usuario);
		this.setState({
			usuarioModificar: usuario
		})
	}

	render() {
		return (
			<Provider store={createStoreWithMiddleware(reducers)}>
				<Router>

					{/*Menus*/}

					{/*Ruta para inicio*/}
					<Route exact path="/" render={() => {
						return <div>
							<RedireccionarLogin/>
						</div>
					}}>
					</Route>

					{/*Ruta para inicio*/}
					<Route exact path="/inicio" render={() => {
						return <>
							<MenuLateral />
							<MenuSuperior />
							<div id="wrapper">
								<Inicio />
							</div>
						</>
					}}>
					</Route>

					{/*Ruta para administrar usuario*/}
					<Route exact path="/AdminUsuario" render={() => {
						return <><MenuLateral />
							<MenuSuperior />
							<div id="wrapper">
								<RedireccionarUsuario funcionModificar={this.asignarUsuario} />
							</div>
						</>
					}}>
					</Route>

					{/*Ruta para editar usuario*/}
					{/* <Route exact path="/editarUsuario" render={() => {
						return <div id="wrapper">
							<MenuLateral />
							<MenuSuperior />
							<EditarUsuario cedula={this.state.usuarioModificar} />
						</div>
					}}>
					</Route> */}


					{/*Ruta para asignar actividad a usuario */}
					{/* <Route exact path="/asignarActividadUsuario" render={() => {
						return <div id="wrapper">
							<MenuLateral />
							<MenuSuperior />
							<AsignarActividadUsuario />
						</div>
					}}>
					</Route> */}


					{/* Ruta para administrar Modulo */}
					<Route exact path="/AdminModulo" render={() => {
						return <>
							<MenuLateral />
							<MenuSuperior />
							<div id="wrapper">
								<AdminModulo />
							</div>
						</>
					}}>
					</Route>

					{/* Ruta para editarModulo */}
					{/* <Route exact path="/editarModulo" render={() => {
						return <div id="wrapper">
							<MenuLateral />
							<MenuSuperior />
							<EditarModulo />

						</div>
					}}>
					</Route> */}

					{/*Ruta para asignar actividad a Modulo */}
					{/* <Route exact path="/asignarActividadModulo" render={() => {
						return <div id="wrapper">
							<MenuLateral />
							<MenuSuperior />
							<AsignarActividadModulo />
						</div>
					}}>
					</Route> */}

					{/*Ruta para aadministrar actividad */}
					<Route exact path="/adminActividad" render={() => {
						return <>
							<MenuLateral />
							<MenuSuperior />
							<div id="wrapper">
								<Actividad />
							</div>
						</>
					}}>
					</Route>

					{/*Ruta para reportes */}
					<Route exact path="/reportes" render={() => {
						return <>
							<MenuLateral />
							<MenuSuperior />
							<div id="wrapper">
								<ContenidoReportes />
							</div>
						</>
					}}>
					</Route>


					{/*Ruta para reportes */}
					<Route exact path="/configuracion" render={() => {
						return <>
							<MenuLateral />
							<MenuSuperior />
							<div id="wrapper">
								<Configuracion />
							</div>
						</>
					}}>
					</Route>

				</Router>
			</Provider>
		);
	}
}

export default App;
