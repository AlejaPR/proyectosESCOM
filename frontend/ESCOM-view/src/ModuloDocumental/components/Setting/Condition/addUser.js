import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { select } from '../../utilitarian/validations.js';
import { getListUsersCondition, getListUser, associateUserCondition, addMessageAssociate } from '../../../redux/actions/userConditionA.js';
import { toast } from 'react-toastify';

class AddUser extends Component {

    componentDidUpdate() {
        if (this.props.messageAssociate !== '') {
            switch (this.props.messageAssociate) {
                case 'associate':
                    toast.success('Usuario asociado.');
                    this.props.getListUsersCondition(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.addMessageAssociate('')
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para asociar este elemento.');
                    this.props.addMessageAssociate('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAssociate('')
                    break;
                default:
                    break;
            }
        }
    }

    componentWillMount() {
        this.props.getListUser(localStorage.getItem('Token'))
    }

    handleSubmit = formValues => {
        let userCondition = {
            id: 0,
            idUser: formValues.user,
            idCondition: sessionStorage.getItem('condition'),
            requestData: null
        }
        this.props.associateUserCondition(localStorage.getItem('Token'), userCondition);
    }

    loadList() {
        return this.props.listUsers.map((user) => {
            return (
                <option value={user.id}>{user.nombre}</option>
            )
        })
    }

    render() {
        return (
            <div>
                <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addModalUser" >
                    <i class="fas fa-plus"></i> Agregar
                </button>
                <div class="modal fade" id="addModalUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Agregar usuario</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="form_control_1">Usuarios: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="user" validate={[select]} className="bs-select form-control" component={generarSelect}>
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
            </div>
        )
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
        listUsers: state.userCondition.listUsersR,
        messageAssociate: state.userCondition.messageAssociate
    }
}

let formAdd = reduxForm({
    form: 'addAssociate',
    enableReinitialize: true
})(AddUser)

export default withRouter(connect(mapStateToProps, { getListUsersCondition, getListUser, associateUserCondition, addMessageAssociate })(formAdd));
