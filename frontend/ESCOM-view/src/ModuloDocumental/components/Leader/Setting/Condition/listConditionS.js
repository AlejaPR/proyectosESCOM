import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListConditions, getConditionId, disableCondition, addMessageEdit, addMessageAdd, addMessageDisable } from '../../../../redux/actions/conditionA.js';
import { ToastContainer, toast } from 'react-toastify';
import Add from './add.js';
import Edit from './edit.js';

class FormCondition extends Component {

    componentDidUpdate() {
        if (this.props.messageEditC !== '') {
            switch (this.props.messageEditC) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'));
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddC !== '') {
            switch (this.props.messageAddC) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.addMessageAdd('')
                    this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'));
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDisableC !== '') {
            switch (this.props.messageDisableC) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.addMessageDisable('')
                    this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'));
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
        this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
    }

    saveView(id) {
        sessionStorage.setItem('condition', id);
        this.props.history.push('/ViewCondition');
    }

    saveEdit(id) {
        this.props.getConditionId(localStorage.getItem('Token'), id)
    }

    disable(id) {
        this.props.disableCondition(localStorage.getItem('Token'), id)
    }

    loadTable() {
        return this.props.conditions.map((condition) => {
            return (
                <tr key={condition.id}>
                    <td>{condition.name}</td>
                    <td>{condition.description}</td>
                    <td>
                        <button onClick={() => this.saveView(condition.id)} className="btn btn-sm">
                            <i class="far fa-eye"></i>
                        </button>
                    </td>
                    <td>
                        <button onClick={() => this.saveEdit(condition.id)} className="btn btn-sm" data-toggle="modal" data-target="#editModal">
                            <i class="fas fa-pen"></i>
                        </button>
                        <Edit />
                    </td>
                    <td>
                        <button onClick={() => this.disable(condition.id)} className="btn btn-sm">
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
                <div class="card" >
                    <div class="card-body">
                        <h3 class="card-title text-center"><strong>LISTA CONDICIONES</strong></h3>
                        <br />
                        <div>
                            <Add />
                        </div>
                        <table class="table border">
                            <thead class="colorBlue text-light">
                                <tr>
                                    <th scope="col">Condición</th>
                                    <th scope="col">Descripción</th>
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
        conditions: state.condition.listConditions,
        messageEditC: state.condition.messageEdit,
        messageAddC: state.condition.messageAdd,
        messageDisableC: state.condition.messageDisable
    }
}

export default withRouter(connect(mapStateToProps, { getListConditions, addMessageDisable, getConditionId, disableCondition, addMessageEdit, addMessageAdd })(FormCondition))
