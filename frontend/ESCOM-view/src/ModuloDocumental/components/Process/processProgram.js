import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getConditionsPer } from '../../redux/actions/conditionA.js';
import { getAllInformation } from '../../redux/actions/activityA.js';

import MaterialTable from 'material-table';
import VisibilityIcon from '@material-ui/icons/Visibility';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import { confirmAlert } from 'react-confirm-alert';

class ProcessPrograma extends Component {

    componentWillMount() {
        this.props.getConditionsPer(localStorage.getItem('Token'), sessionStorage.getItem('processP'))
        this.props.getAllInformation(localStorage.getItem('Token'), sessionStorage.getItem('processP'))
    }

    convertHtmlToWord() {

        var preHtml = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='utf-8'><title>Export HTML To Doc</title></head><body>";
        var postHtml = "</body></html>";
        var html = preHtml + this.props.allInformation.respuesta + postHtml;

        var blob = new Blob(['ufeff', html], {
            type: 'application/msword'
        });

        // Specify link url
        var url = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(html);

        // Specify file name
        var filename = 'document.doc';

        // Create download link element
        var downloadLink = document.createElement("a");

        document.body.appendChild(downloadLink);

        if (navigator.msSaveOrOpenBlob) {
            navigator.msSaveOrOpenBlob(blob, filename);
        } else {
            // Create a link to the file
            downloadLink.href = url;

            // Setting the file name
            downloadLink.download = filename;

            //triggering the function
            downloadLink.click();
        }

        document.body.removeChild(downloadLink);


    }

    save(id) {
        sessionStorage.setItem('condition', id)
        this.props.history.push('/ProcessCondition')
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <div className="text-left titulo">
                    <h4>Lista de condiciones</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabledP ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar las condiciones</Alert>
                        </div> :
                            <div>
                                <button onClick={() => this.convertHtmlToWord()} className="btn btn-sm float-right naranja">
                                    <i class="fas fa-download"></i>
                                </button>
                                <br />
                                <br />
                                <MaterialTable
                                    title=""
                                    localization={{
                                        header: {
                                            actions: ' '
                                        },
                                        pagination: {
                                            nextTooltip: 'Siguiente ',
                                            previousTooltip: 'Anterior',
                                            labelDisplayedRows: '{from}-{to} de {count}',
                                            lastTooltip: 'Ultima pagina',
                                            firstTooltip: 'Primera pagina',
                                            labelRowsSelect: 'Registros',
                                            firstAriaLabel: 'oooo'
                                        },
                                        body: {
                                            emptyDataSourceMessage: 'Aun no hay ninguna condición registrada'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[

                                        { title: 'Nombre de la condición', field: 'name' },
                                        {
                                            title: 'Estado', field: 'description',
                                            render: rowData => {
                                                if (rowData.state === 1) {
                                                    return 'Activo'
                                                } else {
                                                    return 'Finalizado'
                                                }
                                            }
                                        },
                                        {
                                            title: 'Proceso', field: 'percentage',
                                            render: rowData => {
                                                return (
                                                    <div className="progress">
                                                        <div className="progress-bar" style={bar(rowData.percentage)} role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">{rowData.percentage}%</div>
                                                    </div>
                                                )
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.save(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                            <VisibilityIcon />
                                                        </a>
                                                    </div>
                                                )
                                            }
                                        }
                                    ]}
                                    data={this.props.conditions}
                                    options={{
                                        search: true
                                    }}

                                />
                            </div>
                    }
                </div>
            </div>
        )
    }

}

function bar(value) {
    return {
        border: "1",
        color: "#000",
        width: value + "%"
    }

}

function mapStateToProps(state) {
    return {
        conditions: state.condition.listConditionsPer,
        pro: state.condition.processId,
        allInformation: state.activity.allInformation,
        enabledP: state.condition.stateConditionPer

    }
}


export default withRouter(connect(mapStateToProps, { getConditionsPer, getAllInformation })(ProcessPrograma));