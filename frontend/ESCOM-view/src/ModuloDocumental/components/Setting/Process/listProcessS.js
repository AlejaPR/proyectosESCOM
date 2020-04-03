import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListProcesses, getProcessId, addMessageEdit, addMessageAdd, addMessageDisable, disableProcess } from '../../../redux/actions/processA.js';
import { ToastContainer, toast } from 'react-toastify';

import Add from './add.js';
import Edit from './edit.js';

class ListProcess extends Component {

    componentDidUpdate() {
        if (this.props.messageEditP !== '') {
            switch (this.props.messageEditP) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddP !== '') {
            switch (this.props.messageAddP) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
                    this.props.addMessageAdd('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDisableP !== '') {
            switch (this.props.messageDisableP) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
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


    componentWillMount() {
        if (sessionStorage.getItem('documentId') <= 0) {
            this.props.history.push('/')
        }
        this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
    }


    saveEdit(id) {
        this.props.getProcessId(localStorage.getItem('Token'), id)
    }

    disable(id) {
        this.props.disableProcess(localStorage.getItem('Token'), id)
    }

    loadTable() {
        return this.props.listProcess.map((pro) => {
            return (
                <tr key={pro.id}>
                    <td>{pro.name}</td>
                    <td>{pro.description}</td>
                    <td>
                        <button onClick={() => this.saveEdit(pro.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#editModal">
                            <i class="fas fa-pen"></i>
                        </button>
                        <Edit />
                    </td>
                    <td>
                        <button onClick={() => this.disable(pro.id)} className="btn btn-sm text-light naranja">
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
                <div className="card">
                    <div className="card-body">
                        <div>
                            <Add />
                        </div>
                        <h4 className="card-title text-center"><strong>LISTA DE PROCESOS</strong></h4>
                        <table className="table border table-striped">
                            <thead className="colorBlue text-light">
                                <tr>
                                    <th scope="col">Proceso</th>
                                    <th scope="col">Descripcion</th>
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
        listProcess: state.process.listProcessR,
        messageEditP: state.process.messageEdit,
        messageAddP: state.process.messageAdd,
        messageDisableP: state.process.messageDisable
    }
}

export default withRouter(connect(mapStateToProps, { getListProcesses, getProcessId, addMessageEdit, addMessageAdd, addMessageDisable, disableProcess })(ListProcess));