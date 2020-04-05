import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';


class View extends Component {


    render() {
        return (
            <div>
                <div className="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">

                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel" >{this.props.annexV.name}</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                <h6><strong>Descripción: </strong></h6>
                                <p>
                                    {this.props.annexV.description}
                                </p>
                                <h6><strong>Palabras clave</strong></h6>
                                <p>{this.props.annexV.keywords}</p>

                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        )
    }

}
function mapStateToProps(state) {
    return {
        annexV: state.annex.annexR
    }
}

export default withRouter(connect(mapStateToProps, { })(View));
