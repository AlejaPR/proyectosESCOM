import React from 'react';
import { Component } from 'react';
import { Editor } from '@tinymce/tinymce-react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom'
import { getActivityId, addInformation } from '../../../redux/actions/activityA.js';

class AddInformation extends Component {

    valor = "";
    saveView() {
        let inf = this.valor
        if (inf === "") {
            inf = this.props.dataModel.information
        }
        let info = {
            id: this.props.dataModel.id,
            information: inf,
            requestData: null
        }
        this.props.addInformation(localStorage.getItem('Token'), info)
        this.props.history.push('/UserActivity')
    }

    componentWillMount() {
        this.props.getActivityId(localStorage.getItem('Token'), sessionStorage.getItem('activity'))
    }

    handleEditorChange = (content, editor) => {
        this.valor = content;
    }

    render() {
        return (
            <div className="container color">
                <br />
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title text-center">Agregar informacion</h5>
                        <div className="text-center">
                            <Editor
                                apiKey="spssdb50vwk3go6qwrl2ktj7y3ltm94smrx3pj4pg92ypbx8"
                                initialValue={this.props.dataModel.information}
                                init={{
                                    language: 'es',
                                    language_url: '../../node_modules/@tinymce/language/es.js',
                                    height: 500,
                                    menubar: false,
                                    plugins: [
                                        'advlist autolink lists link image charmap print preview anchor',
                                        'searchreplace visualblocks code fullscreen',
                                        'insertdatetime media table paste code help wordcount',
                                        'table'
                                    ],
                                    fontsize_formats: '11px 12px 14px 16px 18px 24px 36px 48px',

                                    font_formats: 'Andale Mono=andale mono,times; Arial=arial,helvetica,sans-serif; Arial Black=arial black,avant garde; Book Antiqua=book antiqua,palatino; Comic Sans MS=comic sans ms,sans-serif; Courier New=courier new,courier; Georgia=georgia,palatino; Helvetica=helvetica; Impact=impact,chicago; Symbol=symbol; Tahoma=tahoma,arial,helvetica,sans-serif; Terminal=terminal,monaco; Times New Roman=times new roman,times; Trebuchet MS=trebuchet ms,geneva; Verdana=verdana,geneva; Webdings=webdings; Wingdings=wingdings,zapf dingbats',
                                    toolbar:
                                        'undo redo | formatselect | bold italic backcolor | fontsizeselect  fontselect | \
                                        alignleft aligncenter alignright alignjustify | table | \
                                        bullist numlist outdent indent | removeformat | help '

                                }}
                                onEditorChange={this.handleEditorChange}
                            />
                        </div>

                    </div>

                    <button style={{ width: "10%" }} onClick={() => this.saveView()} className="btn btn-sm text-light naranja">
                        Guardar
                    </button>
                </div>
            </div>
        );
    }

}

function mapStateToProps(state) {
    return {
        dataModel: state.activity.activityR
    }
}

export default withRouter(connect(mapStateToProps, { getActivityId, addInformation })(AddInformation));