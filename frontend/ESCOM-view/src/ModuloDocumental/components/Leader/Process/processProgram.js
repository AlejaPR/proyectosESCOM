import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getConditionsPer } from '../../../redux/actions/conditionA.js';
import { getAllInformation } from '../../../redux/actions/activityA.js'

class ProcessPrograma extends Component {

    componentWillMount() {
        this.props.getConditionsPer(localStorage.getItem('Token'), sessionStorage.getItem('processP'))
        this.props.getAllInformation(localStorage.getItem('Token'), sessionStorage.getItem('processP'))
    }

    convertHtmlToPdf() {
        fetch('https://v2018.api2pdf.com/chrome/html', {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': '65f55388-ac76-43a3-b28b-ea4720dfe2f2' //Get your API key from https://portal.api2pdf.com
            },
            body: JSON.stringify({ html: this.props.allInformation.data, inlinePdf: true, fileName: 'test.pdf' })
        }).then(res => res.json())
            .then(res => {
                var downloadLink = document.createElement("a");
                document.body.appendChild(downloadLink);
                downloadLink.href = res.pdf;
                downloadLink.target = "_blank";
                downloadLink.download = "nombres";
                downloadLink.click();
                document.body.removeChild(downloadLink);

            }
            )
    }

    convertHtmlToWord() {
        var preHtml = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='utf-8'><title>Export HTML To Doc</title></head><body>";
        var postHtml = "</body></html>";
        var html = preHtml + this.props.allInformation.data + postHtml;

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

    load() {
        return this.props.conditions.map((ConditionView) => {
            return (
                <tr key={ConditionView.id}>
                    <td>{ConditionView.number}</td>
                    <td>{ConditionView.name}</td>
                    <td>{ConditionView.state === 1 ? 'Activo' : 'Finalizada'}</td>
                    <td>
                        <div className="progress">
                            <div className="progress-bar" style={bar(ConditionView.percentage)} role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">{ConditionView.percentage}%</div>
                        </div>
                    </td>
                    <td>
                        <button onClick={() => this.save(ConditionView.id)} className="btn btn-sm text-light naranja">
                            Ver
                        </button>
                    </td>
                </tr >
            )
        })
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <br />
                <div class="card">
                    <div class="card-body">
                        <button onClick={() => this.convertHtmlToWord()} className="btn btn-sm float-right naranja">
                            <i class="fas fa-download"></i>
                        </button>
                        <h3 class="card-title text-center"><strong>Lista de condiciones</strong></h3>
                        <table class="table border">
                            <thead class="colorBlue text-light">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Condición</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Proceso</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.load()}
                            </tbody>
                        </table>
                        <br />
                        <div>
                            {/*
                            <button onClick={() => this.convertHtmlToPdf()} className="btn btn-sm naranja">
                                PDF
                                </button>
                            */}
                        </div>
                    </div>
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
        allInformation: state.activity.allInformation
    }
}


export default withRouter(connect(mapStateToProps, { getConditionsPer, getAllInformation })(ProcessPrograma));