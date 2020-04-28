import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';

import { getListPrograms } from '../../redux/actions/programA.js';
import { getIdDocument } from '../../redux/actions/documentA.js';

class home extends Component {

    componentWillMount() {
        this.props.getListPrograms(localStorage.getItem('Token'))
    }
    componentDidUpdate() {
        if (this.props.idDocument > 0) {
            sessionStorage.setItem('documentId', this.props.idDocument)
            this.props.history.push("/HomeDef")
        } else if (this.props.idDocument === -1) {
            alert('No tiene un documento asociado')
        }

    }

    handleSubmit = formValues => {
        this.props.getIdDocument(localStorage.getItem('Token'), formValues.program);
        sessionStorage.setItem('programId', formValues.program);
    }

    loadList() {
        return this.props.programs.map((pro) => {
            return (
                <option key={pro.id} value={pro.id}>{pro.name + "-" + pro.campus}</option>
            )
        })
    }

    render() {
        return (
            <div className="container color">
                <br />
                <div className="card text-center">
                    <div className="card-body">
                        <h1 className="card-title"><strong>BIENVENIDOS</strong></h1>
                        <br /><br />
                        <h4 className="card-title">SELECCIONE UN PROGRAMA</h4>
                        <br />
                        <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                            <div className="row">
                                <div className="col-4"></div>
                                <div className="col-4">
                                    <div className="text-center">
                                        <Field name="program" className="bs-select form-control text-center" component={generarSelect}>
                                            <option value="0">seleccione</option>
                                            {this.loadList()}
                                        </Field>
                                    </div>
                                </div>
                                <div className="col-4"></div>
                            </div>
                            <br />
                            <div className="form-group">
                                <div className="col-sm-offset-2 col-sm-12">
                                    <button type="submit" title="" className="btn btn-default naranja">
                                        ver
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <br />
            </div>
        )
    }
}

const generarSelect = ({ input, label, type, meta: { touched, error }, children }) => (
    <div >
        <div>
            <select {...input} className="form-control letra " >
                {children}
            </select>
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
        </div>
    </div>
)

const validate = values => {
    const errors = {}
    if (!values.program) {
        errors.program = 'Este campo es obligatorio *'
    }
    return errors
}

function mapStateToProps(state) {
    return {
        programs: state.program.listProgramR,
        idDocument: state.document.documentIdG
    }
}

let formHome = reduxForm({
    form: 'seachProgram', validate,
    enableReinitialize: true
})(home)

export default withRouter(connect(mapStateToProps, { getListPrograms, getIdDocument })(formHome));

