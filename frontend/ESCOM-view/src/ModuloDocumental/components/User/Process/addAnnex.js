import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom'
import { getActivityAnnex, associateAnnex, addMessageAssociate } from '../../../redux/actions/activityA.js';
import { getListAnnexes } from '../../../redux/actions/annexA';
import { ToastContainer, toast } from 'react-toastify';
import { reduxForm, Field } from 'redux-form';
import { Link } from 'react-router-dom';
import { select } from '../../utilitarian/validations.js';

class AddAnnex extends Component {

    componentWillMount() {
        this.props.getActivityAnnex(localStorage.getItem('Token'), sessionStorage.getItem('activity'))
        this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    componentDidUpdate() {
        if (this.props.messageAssociateA !== '') {
            switch (this.props.messageAssociateA) {
                case 'associate':
                    toast.success('Se asocio con exito.');
                    this.props.addMessageAssociate('');
                    this.props.getActivityAnnex(localStorage.getItem('Token'), sessionStorage.getItem('activity'))
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAssociate('');
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

    handleSubmit = formValues => {
        this.props.associateAnnex(localStorage.getItem('Token'), sessionStorage.getItem('activity'), formValues.annex);
        formValues.annex = '0';
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <ToastContainer />
                <br />
                <div className="card">
                    <div className="card-body">
                        <h3 className="card-title text-center"><strong>{this.props.activityAnnex.nameActivity}</strong></h3>
                        <h5>Descripcion:</h5>
                        <p>-- {this.props.activityAnnex.descriptionActivity}</p>
                        <h5>Anexo asociado: </h5>
                        <p>-- {this.props.activityAnnex.nameAnnex === "" ? 'Ningún anexo asociado ': this.props.activityAnnex.nameAnnex}</p>
                        {() => {
                            if (this.props.activityAnnex.url !== "") {
                                return (
                                    <Link to={'/' + this.props.activityAnnex.url} target="_blank" download><i class="fas fa-download"></i></Link>
                                )
                            }
                        }
                        }
                        <hr />
                        <h4>Asociar un anexo a la activiadad:</h4>
                        <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                            <div className="row">
                                <div className="col-sm-4">
                                    <Field name="annex" validate={[select]} className="bs-select form-control" component={generarSelect}>
                                        <option selected value="0">Seleccione...</option>
                                        {this.loadList()}
                                    </Field>
                                </div>
                            </div>
                            <br/>
                            <button type="submit" className="btn btn-default naranja">Agregar</button>
                        </form>
                    </div>
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
        messageAssociateA: state.activity.messageAssociate
    }
}

let formAdd = reduxForm({
    form: 'addAnnex',
    enableReinitialize: true
})(AddAnnex)

export default withRouter(connect(mapStateToProps, { getActivityAnnex, associateAnnex, addMessageAssociate, getListAnnexes })(formAdd));