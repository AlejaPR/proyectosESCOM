import React, { Component } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import { connect } from 'react-redux';
import moment from "moment";
import { getListConditions } from '../../../redux/actions/conditionA.js';



const localizer = momentLocalizer(moment);//array de eventos

class ViewCalendar extends Component {

    componentDidMount() {
        this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
    }

    listEvents() {
        let myEventsList = []

        this.props.listConditionsR.map((condition) => {
            let event = {
                title: condition.name,
                start: condition.startDateS,
                end: condition.finalDateS
            }
            myEventsList.push(event)

        })
        return myEventsList
    }

    render() {
        return (
            <div className="container color" >
                <div class="card">
                    <div class="card-header text-center">
                        Calendario condiciones
                    </div>
                    <div class="card-body">
                        <Calendar
                            localizer={localizer}
                            events={this.listEvents()}
                            startAccessor="start"
                            endAccessor="end"
                            style={{ height: 700 }}
                            messages={{
                                next: "sig",
                                previous: "ant",
                                today: "Hoy",
                                month: "Mes",
                                week: "Semana",
                                day: "Día"
                            }}
                        />
                    </div>
                </div>
            </div>
        );
    }

}
function mapStateToProps(state) {
    return {
        listConditionsR: state.condition.listConditions
    }
}

export default connect(mapStateToProps, { getListConditions })(ViewCalendar);