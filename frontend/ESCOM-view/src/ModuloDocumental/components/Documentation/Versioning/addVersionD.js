import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { required } from '../../utilitarian/validations.js';
import { ToastContainer, toast } from 'react-toastify';
import { addMessageAdd, addDocumentVersion, getOldVersions } from '../../../redux/actions/documentVersionA.js';

class AddVersionD extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedFile: null,
            loaded: 0
        }

    }
    checkMimeType = (event) => {
        let files = event.target.files

        let exte = files[0].name.split('.').pop()
        if (exte !== 'doc' && exte !== 'docx' && exte !== 'pdf' && exte !== 'rtf' && exte !== 'xlsx') {
            toast.error(exte + ' es un formato no soportado')
            event.target.value = null
            return false
        }
        return true;
    }
    maxSelectFile = (event) => {
        let files = event.target.files
        if (files.length > 1) {
            const msg = 'Solo puede subir un documento al tiempo'
            event.target.value = null
            toast.warn(msg)
            return false;
        }
        return true;
    }
    checkFileSize = (event) => {
        let files = event.target.files
        let size = 50000000
        if (files[0].size > size) {
            toast.error('El documento es muy pesado')
            event.target.value = null
            return false
        }
        return true;
    }
    onChangeHandler = event => {
        var files = event.target.files
        if (this.maxSelectFile(event) && this.checkMimeType(event) && this.checkFileSize(event)) {
            // if return true allow to setState
            this.setState({
                selectedFile: files,
                loaded: 0
            })
        }
    }

    randomInt(min, max) {
        return min + Math.floor((max - min) * Math.random());
    }


    handleAdd = formValue => {
        let documentN = {
            id: 0,
            date: new Date(),
            location: '',
            description: formValue.descriptionA,
            state: 2,
            version: 0,
            document: sessionStorage.getItem('documentId'),
            requestData: null
        }
        const data = new FormData()
        data.append('file', this.state.selectedFile[0])
        this.props.addDocumentVersion(localStorage.getItem('Token'), data, documentN);
    }

    componentDidUpdate() {
        if (this.props.messageAdd !== '') {
            switch (this.props.messageAdd) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getOldVersions(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    this.props.addMessageAdd('');
                    break;
                case 'Sin permiso':
                    toast.error('No tiene permisos suficientes para agregar un elemento.');
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
    }

    render() {
        return (
            <div>
                <ToastContainer />
                <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addModal" >
                    <i class="fas fa-plus"></i> Agregar
                </button>

                {/** modal element add */}
                <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form name="agregar" className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleAdd)} id="hola">

                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Agregar una version</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">

                                    <label for="form_control_1">Descripción: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="descriptionA" validate={[required]} component={generarText} label="Descripcion" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Documento: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <input type="file" class="form-control" multiple onChange={this.onChangeHandler} />
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
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
        messageAdd: state.documentVersion.messageAdd
    }
}

let formAdd = reduxForm({
    form: 'addVersionD',
    enableReinitialize: true
})(AddVersionD)
export default withRouter(connect(mapStateToProps, { addDocumentVersion, getOldVersions, addMessageAdd })(formAdd));