import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import { Button } from 'reactstrap';


//componentes
import Barra from '../general/BarraDirecciones';

class EditarModulo extends React.Component{

    render(){
      return ( 
      <div>
            <div class="text-left titulo" style={estiloLetrero}>
				<h4>Editar modulo</h4>
		    </div>
            <Barra texto="Inicio > Editar modulo"/>
            <div className="col-sm-12" style={{
					paddingTop: "0px",
					paddingRight: "40px",
					paddingLeft: "40px",
					paddingBottom: "0px",
					margin: "0px 0px 30px"
                   
			}}>

                <div className="container shadow" style={fondoBarraSuperior}>
                    <br/>
                    <form>
                         <label for="form_control_1">Nombre de modulo</label>
                         <div className="row">
                         <div className="col-sm-5">
                            <input type="text" className="form-control" id="form_control_1" placeholder="" />
                        </div>
                        </div>
                    <br/>
                        <label for="form_control_1">Descripción de modulo</label>
                        <div className="row">                        
                        <div className="col-sm-5">
                            <textarea type="text" className="form-control inputsm" id="form_control_2" placeholder="" />
                        </div>
                        </div>
                    <br/>
                        <label for="form_control_1">Elegir imagen representativa</label>
                        <div class="row">
                        <div class="col-sm-12">
                            <input type="file" name="files[]" multiple="" width="200" height="40"/> 
                        </div>
                        </div>
                        <br/>
                        <div>
                            <Button style={fondoBoton} onClick={this.onSubmit}>Guardar</Button>{''}
                            <Button style={fondoBotonS} color="secondary" onClick={this.toggle}>Cancelar</Button>
                        </div>
                    </form>
                    <br/>
                </div>
            </div>
            

            <div className="col-sm-12" style={{
					paddingTop: "7px",
					paddingRight: "40px",
					paddingLeft: "40px",
					paddingBottom: "20px",
					margin: "0px 0px 32px"
                   
			}}>
                <div class="text-left" style={estiloTitulo}>
				    <h4>Actividades actualmente asignadas al modulo</h4>
			    </div>  
                <div className="container shadow" style={fondoBarraSuperior}>
                    <br/>
                    <div className="jumbotron p-1 jumbotron-fluid" style={fondoTabla}>
					    <table className="table table-hover table-bordered table-checkable" style={fondoBarraSuperior}>
								<thead className="table table-hover table-striped">
									<tr>
										<th> Nombre actividad </th>
										<th> Descripción </th>
										<th> </th>
										
									</tr>
								</thead>
								<tbody> 
                                    <tr>
                                        <td>Crear documento </td> 
                                        <td className ="text-justify">
                                            <span >Lorem ipsum dorem ipsum dorem ipsum dorem ipsum dorem ipsum dolt </span>
                                        </td>
                                        <td className="text-center">
                                            <button class="btn btn-dark btn-circle btn-sm" style={fondoBoton} type="submit">Suspender actividad</button>
                                        </td>    
                                    </tr>
								</tbody>
					    </table>
				    </div>
                </div>
            </div>
        </div>
       )
      

    }
}

const estiloTitulo = {
    
	paddingTop: "7px",
	paddingRight: "12px",
	paddingLeft: "5px",
	paddingBottom: "1px"
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


const fondoBoton = {
    background: "#ec671d",
    fontSize: "14px",
    fontFamily: "Open sans, sans-serif"
  
  }

  const fondoBotonS = {
    background: "secondary",
    fontSize: "14px",
    fontFamily: "Open sans, sans-serif"
  
  }


    
export default EditarModulo;
    
