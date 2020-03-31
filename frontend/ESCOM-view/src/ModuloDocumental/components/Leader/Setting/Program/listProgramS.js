import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListPrograms, getProgramId, addMessageEdit, addMessageAdd } from '../../../../redux/actions/programA.js';
import { ToastContainer, toast } from 'react-toastify';
import Add from './add.js';
import Edit from './edit.js';
import View from './view.js';


class ListPrograms extends Component {

    componentDidUpdate() {
        if (this.props.messageEditPr !== '') {
            switch (this.props.messageEditPr) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListPrograms(localStorage.getItem('Token'))
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddPr !== '') {
            switch (this.props.messageAddPr) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListPrograms(localStorage.getItem('Token'))
                    this.props.addMessageAdd('')
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
        this.props.getListPrograms(localStorage.getItem('Token'))
    }

    save(id) {
        this.props.getProgramId(localStorage.getItem('Token'),id)
    }


    loadTable() {
        return this.props.listProgram.map((program) => {
            return (
                <tr key={program.id}>
                    <td>{program.name}</td>
                    <td>{program.levelEducation}</td>
                    <td>
                        <button onClick={() => this.save(program.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#viewModal">
                            <i class="far fa-eye"></i>
                        </button>
                        <View />
                    </td>
                    <td>
                        <button onClick={() => this.save(program.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#editModal">
                            <i class="fas fa-pen"></i>
                        </button>
                        <Edit />
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
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center"><strong>LISTA DE PROGRAMAS</strong></h4>
                        <div>
                            <Add />
                        </div>

                        <table class="table border table-striped">
                            <thead class="colorBlue text-light">
                                <tr>
                                    <th scope="col">Programa</th>
                                    <th scope="col">Nivel Educativo</th>
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
        listProgram: state.program.listProgramR,
        messageEditPr: state.program.messageEdit,
        messageAddPr: state.program.messageAdd,
        messageDisablePr: state.program.messageDisable

    }
}

export default withRouter(connect(mapStateToProps, { getListPrograms, getProgramId, addMessageEdit, addMessageAdd })(ListPrograms));
