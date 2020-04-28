import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { reduxForm, Field } from 'redux-form';
import { toast } from 'react-toastify';
import { getListComments, deleteCommentary, addCommentary, addMessageDelete, addMessageAdd } from '../../redux/actions/commentaryA.js';
import { required, thousand, minimum } from '../utilitarian/validations.js';
import { confirmAlert } from 'react-confirm-alert';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

class ProcessCommentary extends Component {

    componentWillMount() {
        this.props.getListComments(localStorage.getItem('Token'), sessionStorage.getItem('activity'));
    }

    handleSubmit = formValues => {
        let commentaryN = {
            id: 0,
            message: formValues.message,
            idActivity: sessionStorage.getItem('activity'),
            date: new Date(),
            requestData: null
        }
        this.props.addCommentary(localStorage.getItem('Token'), commentaryN);
        formValues.message = '';
    }

    componentDidUpdate() {
        if (this.props.messageAddC !== '') {
            switch (this.props.messageAddC) {
                case 'add':
                    toast.success('Se agrego con éxito.');
                    this.props.getListComments(localStorage.getItem('Token'), sessionStorage.getItem('activity'));
                    this.props.addMessageAdd('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageAdd('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAdd('');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDeleteC !== '') {
            switch (this.props.messageDeleteC) {
                case 'delete':
                    toast.success('Se elimino con éxito.');
                    this.props.getListComments(localStorage.getItem('Token'), sessionStorage.getItem('activity'));
                    this.props.addMessageDelete('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageDelete('')
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

    delete(id) {
        confirmAlert({
            title: 'Eliminar',
            message: '¿Desea eliminar este elemento de forma permanente?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        this.props.deleteCommentary(localStorage.getItem('Token'), id)
                    },
                },
                {
                    label: 'No',
                    onClick: () => { },
                }
            ],
        })
    }

    loadTable() {
        return this.props.listComments.map((commentary) => {
            if (commentary.nameUser === localStorage.getItem('Nombre')) {
                return (
                    <tr key={commentary.id}>
                        <div className="row">
                            <div className="col-6">
                                <h6><strong>{commentary.nameUser}</strong></h6>
                                <div className="row">
                                    <div className="col-10">
                                        <p>{commentary.message}</p>
                                    </div>
                                    <div className="col-2">
                                        <button onClick={() => this.delete(commentary.id)} className="btn btn-sm float-right">
                                            <i class="fas fa-ban"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div className="col-6"></div>
                        </div>
                        <hr />
                    </tr>
                )
            } else {
                return (
                    <tr key={commentary.id}>
                        <div className="row">
                            <div className="col-6"></div>
                            <div className="col-6">
                                <h6><strong>{commentary.nameUser}</strong></h6>
                                <div className="row">
                                    <div className="col-10">
                                        <p>{commentary.message}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />
                    </tr>
                )
            }
        });
    }


    render() {
        return (
            <div >
                {
                    this.props.enabled ? <div className="col-sm-12">
                        <Alert severity="error" variant="outlined">
                            <AlertTitle>Sin permiso</AlertTitle>
                        No tiene permisos suficientes para listar los Comentarios</Alert>
                    </div> :
                        <div>
                            <h5><strong>Comentarios</strong></h5>
                            <table class="table border">
                                <tbody>
                                    {this.loadTable()}
                                </tbody>
                            </table>
                            <br />

                            <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addModalComment" >
                                <i class="fas fa-plus"></i> Agregar
                            </button>
                            <div class="modal fade" id="addModalComment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                            <div class="modal-header">
                                                <h4 class="modal-title">Nuevo comentario</h4>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            </div>
                                            <div class="modal-body">
                                                <label for="form_control_1">Mensaje: </label>
                                                <div className="row">
                                                    <div className="col-sm-8">
                                                        <Field name="message" validate={[required, thousand, minimum]} component={generarText} label="Nombre" />
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
                }

            </div>
        );
    }

}

const generarText = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
    <div>
        <div>
            <textarea {...input} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
        </div>
    </div>
)

function mapStateToProps(state) {
    return {
        listComments: state.commentary.listCommentsR,
        messageAddC: state.commentary.messageAdd,
        messageDeleteC: state.commentary.messageDelete,
        enabled: state.commentary.stateActivityAnnex
    }
}

let formAdd = reduxForm({
    form: 'addCommentary',
    enableReinitialize: true
})(ProcessCommentary)


export default withRouter(connect(mapStateToProps, { getListComments, deleteCommentary, addCommentary, addMessageDelete, addMessageAdd })(formAdd));