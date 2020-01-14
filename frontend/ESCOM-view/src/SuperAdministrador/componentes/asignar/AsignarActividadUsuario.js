import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'


import { Button } from 'reactstrap';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { seleccione } from '../../utilitario/validacionCampos.js';


//componentes
import Barra from '../general/BarraDirecciones.js';

//redux
import { actionConsultarModulos } from '../../actions/actionsModulo.js'
import { connect } from 'react-redux';

class AsignarActividadUsuario extends React.Component {

    componentWillMount() {
        this.props.actionConsultarModulos(localStorage.getItem('Token'));
    }
    componentDidUpdate(){
        console.log('prosp',this.props);

    }

    activar(){
        console.log('cambio');
    }

    render() {
        return (
            <>
                <div class="text-left titulo" style={estiloLetrero}>
                    <h4>Asignar actividad a usuario</h4>
                </div>
                <Barra texto="Inicio > Administracion de usuarios > Asignación de actividad a usuario" />
                <div className="col-sm-12" style={{
                    paddingTop: "0px",
                    paddingRight: "40px",
                    paddingLeft: "40px",
                    paddingBottom: "0px",
                    margin: "0px 0px 30px"

                }}>
                    <div className="container shadow" style={fondoBarraSuperior}>
                        <form>
                            <br />
                            <label for="form_control_1">Seleccione el modulo que tiene asignada la actividad</label>
                            <div className="row">
                                <div className="col-md-4">
                                    <div className="col-sm-7">
                                        <Field name="tipoDocumento" validate={[seleccione]} style={{ height: "35px", fontSize: "13px" }} className="form-control" component={generarSelect} label="Username">
                                            <option className="letra" style={{ height: "35px", fontSize: "13px" }} value="0">Seleccione</option>
                                            {this.props.modulos.map(modulo => <option key={modulo.idModulo} className="letra" style={{ height: "35px", fontSize: "13px" }} value={modulo.idModulo}>{modulo.nombreModulo}</option>)}
                                        </Field>
                                    </div>
                                </div>
                            </div>
                            <br />
                            <label for="form_control_1">Seleccione la actividad que quiere asignar al usuario</label>
                            <div className="row">
                                <div className="col-md-4">
                                    <select className="bs-select form-control " id="selectDDL" onchange="redirige(this.value)" title="ACCIONES">
                                        <option value="0">Seleccione...</option>
                                        <option value="1">Crear usuario</option>
                                        <option value="2">Generar reporte</option>
                                        <option value="3">Crear modulo</option>
                                    </select>
                                </div>
                            </div>
                            <br />
                            <div>
                                <Button style={fondoBoton} onClick={this.onSubmit}>Guardar</Button>{''}
                                <Button style={fondoBotonS} color="secondary" onClick={this.toggle}>Cancelar</Button>
                            </div>
                        </form>
                        <br />
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
                        <br />
                        <div className="jumbotron p-1 jumbotron-fluid" style={fondoTabla}>
                            <table className="table table-hover table-bordered table-checkable" style={fondoBarraSuperior}>
                                <thead className="table table-hover table-striped">
                                    <tr>
                                        <th> Nombre actividad </th>
                                        <th> Descripción </th>
                                        <th> Modulo </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Crear documento </td>
                                        <td className="text-justify">
                                            <span >Lorem ipsum dorem ipsum dorem ipsum dorem ipsum dorem ipsum dolt </span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <br />
                </div>
            </>

        )
    }
}

const generarSelect = ({ input, label, type, meta: { touched, error }, children }) => (
    <div>
        <div>
            {console.log(input.onChange)}
            <select {...input} className="form-control letra"  style={{ height: "35px", fontSize: "13px" }}>
                {children}
            </select>
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
        </div>
    </div>
)




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

function mapStateToProps(state) {
    return {
        modulos: state.mod.modulosRegistrados,
        habilitado:state.mod.estadoModulos
    }
}

let asignarActividadUsuario = reduxForm({
    form: 'asignarActividadUsuario'
})(AsignarActividadUsuario)

export default withRouter(connect(mapStateToProps, {actionConsultarModulos})(asignarActividadUsuario));
