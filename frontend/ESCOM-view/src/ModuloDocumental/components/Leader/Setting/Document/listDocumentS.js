import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListDocuments, getDocumentIdEdit, addMessageEdit, addMessageAdd, addMessageDisable, disableDocument } from '../../../../redux/actions/documentA.js';
import Add from './add.js';
import Edit from './edit.js';
import View from './view.js';
import { ToastContainer, toast } from 'react-toastify';

class ListDocument extends Component {

    componentDidUpdate() {
        if (this.props.messageEditD !== '') {
            switch (this.props.messageEditD) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.getListDocuments(localStorage.getItem('Token'));
                    this.props.addMessageEdit('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddD !== '') {
            switch (this.props.messageAddD) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListDocuments(localStorage.getItem('Token'));
                    this.props.addMessageAdd('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDisableD !== '') {
            switch (this.props.messageDisableD) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.addMessageDisable('');                    
                    this.props.getListDocuments(localStorage.getItem('Token'));
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
    }

    componentWillMount() {
        this.props.getListDocuments(localStorage.getItem('Token'))
    }

    save(id) {
        this.props.getDocumentIdEdit(localStorage.getItem('Token'), id)
    }

    disable(id) {
        this.props.disableDocument(localStorage.getItem('Token'), id)
    }

    loadTable() {
        return this.props.listDocument.map((doc) => {
            return (
                <tr key={doc.id}>
                    <td>{doc.program}</td>
                    <td>{doc.description}</td>
                    <td>
                        <button onClick={() => this.save(doc.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#viewModal">
                            <i class="far fa-eye"></i>
                        </button>
                        <View />
                    </td>
                    <td>
                        <button onClick={() => this.save(doc.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#editModal">
                            <i class="fas fa-pen"></i>
                        </button>
                        <Edit />
                    </td>
                    <td>
                        <button onClick={() => this.disable(doc.id)} className="btn btn-sm text-light naranja">
                            <i class="fas fa-ban"></i>
                        </button>
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div className="container color">
                <ToastContainer />
                <br />
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title text-center"><strong>LISTA DE DOCUMENTOS</strong></h3>
                        <div>
                            <Add />
                        </div>
                        <table className="table border table-striped">
                            <thead className="colorBlue text-light">
                                <tr>
                                    <th scope="col">Programa</th>
                                    <th scope="col">Descripcion</th>
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
                <br />
            </div>
        )
    }

}

function mapStateToProps(state) {
    return {
        listDocument: state.document.listDocumentR,
        messageEditD: state.document.messageEdit,
        messageAddD: state.document.messageAdd,
        messageDisableD: state.document.messageDisable
    }
}

export default withRouter(connect(mapStateToProps, { getListDocuments, getDocumentIdEdit, disableDocument, addMessageEdit, addMessageAdd, addMessageDisable })(ListDocument));