import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getListPrograms } from '../../../../redux/actions/programA.js';
import { getListAnnexes, searchAnnexS } from '../../../../redux/actions/annexA.js';
import { reduxForm, Field } from 'redux-form';
import { Link, withRouter } from 'react-router-dom';



class searchAnnexs extends Component {

    componentWillMount() {
        this.props.getListPrograms()
        this.props.getListAnnexes(0)
    }

    handleSubmit = formValues => {
        console.log(formValues)
        let searchAnn = {
            idProgram: formValues.program,
            name: formValues.name
        }
        this.props.searchAnnexS(searchAnn)
    }

    loadList() {
        return this.props.programs.map((pro) => {
            return (
                <option value={pro.id}>{pro.name}</option>
            )
        })
    }

    save(id) {
        sessionStorage.setItem('annex', id)
        this.props.history.push('/VersionAnnex')
    }

    loadTable() {
        return this.props.annexes.map((annex) => {
            return (
                <tr key={annex.id}>
                    <td>{annex.name}</td>
                    <td>{annex.description}</td>
                    <td>
                        <button onClick={() => this.save(annex.id)} className="btn btn-sm text-light naranja">
                            <i class="far fa-eye"></i>
                        </button>
                    </td>
                    <td>
                        <Link to={annex.link !== '' ? '/'+annex.link : ''}  target="_blank" download><i class="fas fa-download"></i></Link>
                    </td>
                </tr>
            )
        })
    }


    render() {
        return (
            <div className="container color">
                <br />
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title text-center"><strong>BUSCAR ANEXOS</strong></h3>
                        <br/>
                        <form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                            <div className="row">
                                <div className="input-group col-6">
                                    <Field name="program" className="bs-select form-control" component="select">
                                        <option selected value="0" >Seleccione...</option>
                                        {this.loadList()}
                                    </Field>
                                    <label className="input-group-text" for="inputGroupSelect02">Programas</label>
                                </div>

                                <div className="col-4">
                                    <Field name="name" component={generarInput} label="Nombre" />
                                </div>
                                <div className="col-2">
                                    <button className="btn naranja " type="submit">Buscar</button>
                                </div>
                            </div>
                        </form>
                        <hr />
                        <table class="table border table-striped">
                            <thead class="colorBlue text-light">
                                <tr>
                                    <th scope="col">Anexo</th>
                                    <th scope="col">Descripcion</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.loadTable()}
                            </tbody>
                        </table>
                        <br />
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
        programs: state.program.listProgramR,
        annexes: state.annex.listAnnexR
    }
}

let formSearch = reduxForm({
    form: 'searchAnnex',
    enableReinitialize: true
})(searchAnnexs)
export default withRouter(connect(mapStateToProps, { getListPrograms, getListAnnexes, searchAnnexS })(formSearch));