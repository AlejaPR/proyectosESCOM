import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { reduxForm, Field } from 'redux-form';
import { select } from '../../../utilitarian/validations.js';
import { getListRelational, addMessageAdd, addMessageDelete, addRelational, deleteRelational } from '../../../../redux/actions/relationalClassA.js';
import { toast } from 'react-toastify';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class PtDistinctive extends Component {

    componentDidMount() {

    }

    componentDidUpdate() {
        if (this.props.messageAdd !== '') {
            switch (this.props.messageAdd) {
                case 'addD':
                    toast.success('Se agrego con éxito.');
                    this.props.getListRelational(localStorage.getItem('Token'), sessionStorage.getItem('programT'), 'PtDistinctive');
                    this.props.addMessageAdd('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAdd('');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDelete !== '') {
            switch (this.props.messageDelete) {
                case 'deleteD':
                    toast.success('Se elimino correctamente.');
                    this.props.addMessageDelete('');
                    this.props.getListRelational(localStorage.getItem('Token'), sessionStorage.getItem('programT'), 'PtDistinctive');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDelete('');
                    break;
                default:
                    break;
            }
        }
    }

    handleSubmit = formValues => {
        let relationalN = {
            id: 0,
            idProgramaThematic: sessionStorage.getItem('programT'),
            idRelation: formValues.distinctive,
            nameRelation: "",
            table: 'PtDistinctive',
            requestData: null
        }
        this.props.addRelational(localStorage.getItem('Token'), relationalN);
        formValues.competition = '';
    }


    delete(id) {
        let relationalN = {
            id: id,
            idRelation: "",
            nameRelation: "",
            table: 'PtDistinctive',
            requestData: null
        }
        this.props.deleteRelational(localStorage.getItem('Token'), relationalN)
    }

    loadList() {
        return this.props.listGeneralC.map((general) => {
            return (
                <option value={general.id}>{general.name}</option>
            )
        })
    }

    loadTable() {
        return this.props.listRelation.map((relation) => {
            return (
                <tr key={relation.id} >
                    <td>{relation.nameRelation}</td>
                    <td>
                        <a onClick={() => this.delete(relation.id)}>
                            <DeleteForeverIcon />
                        </a>
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div >
                <br />
                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addDistinctive" >
                        <i class="fas fa-plus"></i> Agregar
                    </button>
                    <div class="modal fade" id="addDistinctive" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Nueva relación</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <label for="form_control_1">Rasgo distintivo: </label>
                                        <div className="row">
                                            <div className="col-sm">
                                                <Field name="distinctive" validate={[select]} className="bs-select form-control" component={generarSelect}>
                                                    <option value="0">Seleccione...</option>
                                                    {this.loadList()}
                                                </Field>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                        <button type="submit" className="btn btn-default naranja">Agregar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <br />
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Nombre del rasgo distintivo</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.loadTable()}
                        </tbody>
                    </table>

                </div>
            </div >
        );
    }

}

const generarSelect = ({ input, label, type, meta: { touched, error }, children }) => (
    <div>
        <div>
            <select {...input} className="form-control letra" style={{ height: "35px", fontSize: "13px" }}>
                {children}
            </select>
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
        </div>
    </div>
)

function mapStateToProps(state) {
    return {
        listGeneralC: state.generalClass.listGeneralClassR,
        listRelation: state.relationalClass.listRelationalClassR,
        messageAdd: state.relationalClass.messageAddR,
        messageDelete: state.relationalClass.messageDeleteR
    }
}

let formAdd = reduxForm({
    form: 'addProcess',
    enableReinitialize: true
})(PtDistinctive)

export default withRouter(connect(mapStateToProps, { addMessageAdd, addMessageDelete, getListRelational, addRelational, deleteRelational })(formAdd));