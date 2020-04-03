import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getListAnnexes, searchAnnexS, getAnnexId, addMessageEdit, addMessageAdd, addMessageDisable, disableAnnex } from '../../../redux/actions/annexA.js';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import Add from './add.js';
import Edit from './edit.js';
import View from './view.js';
import { ToastContainer, toast } from 'react-toastify';

class ListAnnex extends Component {

    componentDidUpdate() {
        if (this.props.messageEditX !== '') {
            switch (this.props.messageEditX) {
                case 'edit':
                    toast.success('Se agrego con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'));
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddX !== '') {
            switch (this.props.messageAddX) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    this.props.addMessageAdd('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDisableX !== '') {
            switch (this.props.messageDisableX) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    this.props.addMessageDisable('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
    }

    componentDidMount() {
        this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    handleSubmit = formValues => {
        console.log(formValues)
        let searchAnn = {
            idProgram: sessionStorage.getItem('programId'),
            name: formValues.words
        }
        this.props.searchAnnexS(localStorage.getItem('Token'), searchAnn)
    }

    loadList() {
        return this.props.programs.map((pro) => {
            return (
                <option value={pro.id}>{pro.name}</option>
            )
        })
    }

    save(id) {
        this.props.getAnnexId(localStorage.getItem('Token'), id);
    }

    disable(id) {
        this.props.disableAnnex(localStorage.getItem('Token'), id)
    }

    loadTable() {
        return this.props.annexes.map((annex) => {
            return (
                <tr key={annex.id}>
                    <td>{annex.name}</td>
                    <td>{annex.description}</td>
                    <td>{annex.keywords}</td>
                    <td>
                        <button onClick={() => this.save(annex.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#viewModal">
                            <i class="far fa-eye"></i>
                        </button>
                        <View />
                    </td>
                    <td>
                        <button onClick={() => this.save(annex.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#editModal" >
                            <i class="fas fa-pen"></i>
                        </button>
                        <Edit />
                    </td>
                    <td>
                        <button onClick={() => this.disable(annex.id)} className="btn btn-sm text-light naranja">
                            <i class="fas fa-ban"></i>
                        </button>
                    </td>
                </tr>
            )
        })
    }


    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <ToastContainer />
                <br />
                {/* main element */}
                <div class="card" >
                    <div class="card-body">
                        <h3 class="card-title text-center"><strong>ANEXOS DEL PROGRAMA</strong></h3>
                        <br />
                        <form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                            <div className="row">
                                <div className="col-6">
                                    <Field name="words" component={generarInput} label="" />
                                </div>
                                <div className="col-2">
                                    <button className="btn naranja btn-sm" type="submit">
                                        Buscar
                                    </button>
                                </div>
                                <div className="col-4">
                                    <Add />
                                </div>
                            </div>
                        </form>
                        <br />
                        <div className="">
                            <table class="table table-hover">
                                <thead class="colorBlue text-light">
                                    <tr>
                                        <th scope="col">Anexo</th>
                                        <th scope="col">Descripción</th>
                                        <th scope="col">Palabras claves</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {this.loadTable()}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <br />
            </div >
        )
    }

}

const generarInput = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
    <div>
        <div>
            <input {...input} type={type} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
        </div>
    </div>
)

function mapStateToProps(state) {
    return {
        annexes: state.annex.listAnnexR,
        messageEditX: state.annex.messageEdit,
        messageAddX: state.annex.messageAdd,
        messageDisableX: state.annex.messageDisable
    }
}

let formSearch = reduxForm({
    form: 'searchAnnex',
    enableReinitialize: true
})(ListAnnex)
export default withRouter(connect(mapStateToProps, { searchAnnexS, disableAnnex, addMessageDisable, getListAnnexes, getAnnexId, addMessageEdit, addMessageAdd })(formSearch));