import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import { Button } from 'reactstrap';


//componentes
import Barra from '../general/BarraDirecciones.js'


class ContenidoReportes extends React.Component {

	render() {
		return (
			<div>
				<div className="text-left titulo" style={estiloLetrero}>
					<h4>Reportes</h4>
				</div>
				<Barra texto="Inicio > Reportes"/>
				<div className="col-sm-12" style={{
					paddingTop: "20px",
					paddingRight: "46px",
					paddingLeft: "25px",
					paddingBottom: "7px",
				}}>
					<div className="input-group">
                        <div className="col-sm-3">
                            <select className="bs-select form-control" name="tipoDocumento" onChange={this.onChange} id="selectDDL" title="ACCIONES">
                                <option value="0">Seleccione...</option>
                                <option value="1">Usuarios</option>
                                <option value="2">Modulos</option>
                                <option value="3">Actividades</option>
                            </select>
                        </div>
						<input type="text" style={{fontSize:"14px"}}  className="form-control" placeholder="" />
						<span className="input-group-btn">
						<Button style={fondoBoton}>Buscar</Button>
						</span>
					</div>
				</div>
                <div className="col-sm-12" style={{
					paddingTop: "20px",
					paddingRight: "46px",
					paddingLeft: "55px",
					paddingBottom: "7px",
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
				<div className="container" style={{
					paddingTop: "7px",
					paddingRight: "12px",
					paddingLeft: "40px",
					paddingBottom: "20px",
					margin: "0px 0px 32px"
				}}>
					<div className="container shadow" style={fondoBarraSuperior}>
						<br />
						<div className="jumbotron p-1 jumbotron-fluid" style={fondoTabla}>
							<table className="table table-hover table-bordered table-checkable" style={fondoBarraSuperior}>
								<thead className="table table-hover table-striped col-md-12">
									<tr>
										<th> Usuario </th>
										<th> Actividad que realizó </th>
										<th> Fecha </th>
									</tr>
								</thead>
								<tbody>
									{										
									}
								</tbody>
							</table>
						</div>
					</div>
				</div>
                <div className="container" style={{
					paddingTop: "0px",
					paddingRight: "12px",
					paddingLeft: "40px",
					paddingBottom: "10px",
					margin: "0px 0px 32px"
				}}>
                  <Button style={fondoBoton} onClick={this.onSubmit}>Generar Reporte</Button>{''}
                </div>
			</div>
		);
	}


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

const fondoBarraSuperior = {
	background: "#FFFFFF"

}

const fondoTabla = {
	background: "#EAF2F2"
}


export default ContenidoReportes;