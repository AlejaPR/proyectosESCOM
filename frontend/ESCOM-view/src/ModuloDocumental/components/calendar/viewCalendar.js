import React, { Component } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import { connect } from 'react-redux';
import moment from "moment";
import { getListConditions } from '../../redux/actions/conditionA.js';



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
            <div className="container color" style={{ width: "90%" }}>
                <div className="text-left titulo">
                    <h4>Calendario de condiciones</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    <Calendar
                        popup
                        messages={{
                            showMore: total => (
                                <div
                                    style={{ cursor: 'pointer' }}
                                    onMouseOver={e => {
                                        e.stopPropagation();
                                        e.preventDefault();
                                    }}
                                >{`+${total} more`}
                                </div>
                            ),
                        }}
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
        );
    }

}
function mapStateToProps(state) {
    return {
        listConditionsR: state.condition.listConditions
    }
}

export default connect(mapStateToProps, { getListConditions })(ViewCalendar);