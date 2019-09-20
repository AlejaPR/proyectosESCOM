import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'
import '../../css/registro.css'

import {Button} from 'reactstrap';

class Login extends React.Component {
	pruebita = () => {
		this.props.cambiar('e');
	}
	
	render() {
		return (
			<div>
				<div className="container-fluid fondo-blanco">
					<div className="row no-gutter">
						<div className="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
						<div className="col-md-8 col-lg-6">
							<div className="login d-flex align-items-center py-5">
								<div className="container">
									<div className="row">
										<div className="col-md-6 col-lg-10 mx-auto">
											<form id="idformulario" className="center">
												<h3 className="login-heading mb-4">Sistema para el apoyo administrativo</h3>
												<h6 className="login-heading mb-4">Modulo super administrador</h6>
												<div className="row">
													<div className="col-md-12">
														<input type="text" id="inputCorreo" className="form-control form-control-solid placeholder-no-fix form-group" required placeholder="Correo" />
													</div>
												</div>
												<div className="row">
													<div className="col-md-12 center">
														<input type="text" id="inputCorreo" className="form-control form-control-solid placeholder-no-fix form-group" required placeholder="Contraseña" />
													</div>
												</div>
												<div className="row">
													<div className="col-sm-4">
														<div className="rem-password">
															<label className="rememberme mt-checkbox mt-checkbox-outline">
																<input type="checkbox" name="remember" value="1" /> Recordar datos
				          					                   <span></span>
															</label>
														</div>
													</div>
													<div className="forgot-password col-sm-4">
														<a href="/editar" id="forget-password" className="small" >¿Olvido su contraseña?</a>
													</div>
													<div className="col-sm-4">
														<Button className="btn btn-dark" style={fondoBoton} onClick={this.pruebita}>Iniciar sesion</Button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		)
	}
}


const fondoBoton = {
	background: "#ec671d"
}



export default Login;