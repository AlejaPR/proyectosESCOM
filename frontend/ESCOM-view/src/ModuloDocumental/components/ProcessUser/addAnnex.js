import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';
import { getActivityAnnex, associateAnnex, addMessageAssociate, changeStatus, addMessageChange } from '../../redux/actions/activityA.js';
import { getListAnnexes } from '../../redux/actions/annexA';
import { ToastContainer, toast } from 'react-toastify';
import { reduxForm, Field } from 'redux-form';
import { Link } from 'react-router-dom';
import { select } from '../utilitarian/validations.js';

class AddAnnex extends Component {

    componentWillMount() {
        this.props.getActivityAnnex(localStorage.getItem('Token'), sessionStorage.getItem('activity'))
        this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    componentDidUpdate() {
        if (this.props.messageAssociateA !== '') {
            switch (this.props.messageAssociateA) {
                case 'associate':
                    toast.success('Se asocio con éxito.');
                    this.props.addMessageAssociate('');
                    this.props.getActivityAnnex(localStorage.getItem('Token'), sessionStorage.getItem('activity'))
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para asociar este elemento.');
                    this.props.addMessageAssociate('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAssociate('');
                    break;
                default:
                    break;
            }
        }

        if (this.props.messageChange !== '') {
            switch (this.props.messageChange) {
                case 'notify':
                    toast.success('Notificada con éxito.');
                    this.props.addMessageChange('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para notificar este elemento.');
                    this.props.addMessageEdit('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageChange('');
                    break;
                default:
                    break;
            }
        }
    }

    loadList() {
        return this.props.annexes.map((annex) => {
            return (
                <option value={annex.id}>{annex.name}</option>
            )
        })
    }
    notifyActivity() {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        let activityN = {
                            id: sessionStorage.getItem('activity'),
                            state: 3,
                            requestData: null
                        }
                        this.props.changeStatus(localStorage.getItem('Token'), activityN)
                    }
                },
                {
                    label: 'No',
                    onClick: () => { }
                }
            ]
        });
    }


    handleSubmit = formValues => {
        this.props.associateAnnex(localStorage.getItem('Token'), sessionStorage.getItem('activity'), formValues.annex);
        formValues.annex = '0';
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Información de anexo</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    <button type="button" onClick={() => this.notifyActivity()} className="btn text-light btn-sm float-right naranja " >
                        Notificar
                        </button>
                    <h5 className="card-title text-center"><strong>{this.props.activityAnnex.nameActivity}</strong></h5>
                    <h6><strong>Descripción:</strong></h6>
                    <p>-- {this.props.activityAnnex.descriptionActivity}</p>
                    <h6><strong>Anexo asociado:</strong></h6>
                    <p>-- {this.props.activityAnnex.nameAnnex === "" ? 'Ningún anexo asociado ' : this.props.activityAnnex.nameAnnex}</p>
                    {() => {
                        if (this.props.activityAnnex.url !== "") {
                            return (
                                <Link to={'/' + this.props.activityAnnex.url} target="_blank" download><i class="fas fa-download"></i></Link>
                            )
                        }
                    }
                    }
                    <hr />
                    <h5>Asociar un anexo a la actividad:</h5>
                    <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                        <div className="row">
                            <div className="col-sm-4">
                                <Field name="annex" validate={[select]} className="bs-select form-control" component={generarSelect}>
                                    <option selected value="0">Seleccione...</option>
                                    {this.loadList()}
                                </Field>
                            </div>
                        </div>
                        <br />
                        <button type="submit" className="btn btn-default naranja">Agregar</button>
                    </form>
                </div>

                <br />
            </div>
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
        activityAnnex: state.activity.activityAnnexR,
        annexes: state.annex.listAnnexR,
        messageAssociateA: state.activity.messageAssociate,
        messageChange: state.activity.messageChange
    }
}

let formAdd = reduxForm({
    form: 'addAnnex',
    enableReinitialize: true
})(AddAnnex)

export default withRouter(connect(mapStateToProps, { getActivityAnnex, changeStatus, addMessageChange, associateAnnex, addMessageAssociate, getListAnnexes })(formAdd));