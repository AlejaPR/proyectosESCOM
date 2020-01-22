import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'


import { Button } from 'reactstrap';
import Select from 'react-select'
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { seleccione } from '../../utilitario/validacionCampos.js';
import MaterialTable from 'material-table';
import Divider from '@material-ui/core/Divider';
import Alerta from '@icons/material/AlertIcon.js';

//componentes
import Barra from '../general/BarraDirecciones.js';

//redux
import { actionConsultarModulos, actionConsultarActividadesSinAsignar, actionAsignarActividades, actionConsultarActividadesUsuario } from '../../actions/actionsUsuario.js'
import { connect } from 'react-redux';

class AsignarActividadUsuario extends React.Component {

    state = {
        selectedOption: 0
    }

    componentWillMount() {
        this.props.actionConsultarModulos(localStorage.getItem('Token'));
        this.props.actionConsultarActividadesUsuario(this.props.cedula, localStorage.getItem('Token'));
        if (this.state.selectedOption !== 0) {
            this.props.actionConsultarActividadesSinAsignar(localStorage.getItem('Token'), this.props.cedula, this.state.selectedOption);
        }
    }

    componentDidMount() {
        if (this.props.cedula === undefined || this.props.cedula.length === 0) {
            this.props.history.push('/adminUsuario');
        }
    }
    componentDidUpdate() {
    }

    opciones = () => {
        let respuesta = [];
        this.props.modulos.map(
            modulo => {
                let objeto = {
                    label: modulo.nombreModulo,
                    value: modulo.idModulo,
                }
                respuesta.push(objeto);
            }
        )
        return respuesta;
    }

    actividades = () => {
        let respuesta = [];
        if (this.props.actividadesSinAsignar !== null) {
            this.props.actividadesSinAsignar.map(
                actividad => {
                    let objeto = {
                        label: actividad.nombre,
                        value: actividad.idActividad,
                    }
                    respuesta.push(objeto);
                }
            )
        } else {
            return null;
        }
        return respuesta;
    }



    handleChange = selectedOption => {
        console.log(selectedOption.value);
        this.setState({ selectedOption });
        this.props.actionConsultarActividadesSinAsignar(localStorage.getItem('Token'), this.props.cedula, selectedOption.value);
    };

    handleSubmit = formValues => {
        console.log('form', formValues)
    }
    render() {
        return (
            <>
                <div class="text-left titulo" style={estiloLetrero}>
                    <h4>Asignar actividad a usuario</h4>
                </div>
                <Barra texto="Inicio > Administracion de usuarios > Asignación de actividad a usuario" />
                <div className="container" style={{
                    paddingTop: "7px",
                    paddingRight: "44px",
                    paddingLeft: "40px",
                    paddingBottom: "20px",
                    margin: "0px 0px 32px"

                }}>

                    <div className="container shadow" style={
                        {
                            background: "white",
                            paddingTop: "37px",
                            paddingRight: "31px",
                            paddingLeft: "31px",
                            paddingBottom: "21px"
                        }} >
                        {
                            this.props.habilitado ? <div className="col-sm-12"> <span className="col-sm-2 center" style={{
                                textShadow: "none!important",
                                fontSize: "16px",
                                fontFamily: "Open Sans,sans-serif",
                                fontWeight: "300",
                                padding: "13px 122px",
                                color: "#fff",
                                background: "rgb(158, 35, 45)"
                            }}><Alerta />No tiene los permisos suficientes para administrar las actividades de los usuarios</span></div> :
                                <form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                    <br />
                                    <label for="form_control_1">Seleccione el modulo que tiene asignada la actividad</label>
                                    <div className="row">
                                        <div className="col-md-4">
                                            <Field name="currentUser" validate={[seleccione]} onChange={this.handleChange} component={ReduxFormSelect} options={this.opciones()} />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Seleccione la actividad que quiere asignar al usuario</label>
                                    <div className="row">
                                        <div className="col-md-4">
                                            <Field name="actividad" validate={[seleccione]} component={ReduxFormSelectDos} options={this.actividades()} />
                                        </div>
                                    </div>
                                    <br />
                                    <Button style={fondoBoton} type="submit">Registrar</Button>{''}
                                    <Button color="secondary" className="letra" >Cancelar</Button>
                                    <br />
                                    <br />
                                    <Divider variant="middle" />
                                    <br />
                                    <MaterialTable
                                        title="Actividades actualmente asignadas al usuario"
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
                                                emptyDataSourceMessage: 'Aun no hay ningun modulo registrado'
                                            },
                                            toolbar: {
                                                searchTooltip: 'Buscar',
                                                searchPlaceholder: 'Buscar'
                                            }
                                        }}
                                        columns={[
                                            { title: 'Nombre de modulo', field: 'idActividad', headerStyle: estiloCabecera, cellStyle: estiloFila },
                                            { title: 'Descripcion del modulo', field: 'nombre', headerStyle: estiloCabecera, cellStyle: estiloFila }
                                        ]}
                                        data={this.props.actividades}
                                        options={{
                                            search: true,
                                            rowStyle: estiloFila,
                                            selection: true
                                        }}
                                        onSelectionChange={(rows) => {
                                            this.setState({ actividadesSeleccionadas: rows });
                                            console.log('You selected ', rows)
                                        }}
                                    />
                                </form>
                        }
                        <br />
                    </div>
                </div>
            </>

        )
    }
}

export const ReduxFormSelect = props => {
    const { input, options } = props;
    const { touched, error } = props.meta;
    return (
        <>
            <Select
                {...input}
                isSearchable={false}
                placeholder='Seleccione un modulo'
                onChange={value => input.onChange(value)}
                onBlur={() => input.onBlur(input.value)}
                options={options}
            />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
        </>
    )
}

export const ReduxFormSelectDos = props => {
    const { input, options } = props;
    const { touched, error } = props.meta;
    { console.log('asnjsakska', props) }
    return (
        <>
            <Select
                {...input}
                isSearchable={false}
                placeholder='Seleccione un modulo'
                onChange={value => input.onChange(value)}
                onBlur={() => input.onBlur(input.value)}
                options={options}
            />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
        </>
    )
}

const estiloCabecera = {
    fontSize: '13px',
    fontFamily: 'sans-serif',
    padding: '8px',
    background: '#e7ecf1'

}

const estiloFila = {
    fontSize: '12px',
    fontFamily: 'sans-serif',
    padding: '8px',
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

function mapStateToProps(state) {
    return {
        modulos: state.user.modulosAsignar,
        habilitado: state.user.estadoAsignar,
        cedula: state.user.cedula,
        actividades: state.user.actividadesUsuario,
        actividadesSinAsignar: state.user.actividadesSinAsignar
    }
}

let asignarActividadUsuario = reduxForm({
    form: 'asignarActividadUsuario'
})(AsignarActividadUsuario)

export default withRouter(connect(mapStateToProps, { actionConsultarModulos, actionConsultarActividadesSinAsignar, actionAsignarActividades, actionConsultarActividadesUsuario })(asignarActividadUsuario));
