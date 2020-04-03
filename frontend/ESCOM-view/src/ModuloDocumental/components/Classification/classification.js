import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getListActivitiesInfo } from '../../redux/actions/activityA.js';
import { reduxForm, Field, FormSection } from 'redux-form';



class Classification extends Component {

    componentWillMount() {
        this.props.getListActivitiesInfo(31)
    }

    handleSubmit = formValues => {
        console.log(FormSection[0])
        console.log(formValues)
    }

    loadTable() {
        return this.props.activities.map((act) => {
            return (
                <tr key={act.id}>                    
                    <td><Field name="id" component="input" value={act.id} label="Nombre" style={{ display:"none", width: "0%" }} />{act.name}</td>
                    <td>{act.description}</td>
                    <td><Field name={"investigacion"+act.id} component={generarInput} label="Nombre" /></td>
                    <td><Field name={"dificultad"+act.id} component={generarInput} label="Nombre" /></td>
                    <td><Field name={"riesgo"+act.id} component={generarInput} label="Nombre" /></td>
                    <td><button className="btn naranja " type="submit">Guardar</button></td>

                </tr>
            )
        })
    }


    render() {
        return (
            <div className="container color" style={{ width: "90%" }} >
                <form onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                <div className="">
                    <table class="table border table-striped">
                        <thead class="colorBlue text-light">
                            <tr>
                                <th scope="col">Anexo</th>
                                <th scope="col">description</th>
                                <th scope="col">Investigacion</th>
                                <th scope="col">dificultad</th>
                                <th scope="col">riesgo</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>                            
                                {this.loadTable()}                            
                        </tbody>
                    </table>
                    <br />
                </div>
                </form>
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
        activities: state.activity.listActivityR
    }
}

let formCla = reduxForm({
    form: 'searchAnnex',
    enableReinitialize: true
})(Classification)
export default connect(mapStateToProps, { getListActivitiesInfo })(formCla);