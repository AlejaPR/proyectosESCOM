import React from 'react';
import { Component } from 'react';
import { Editor } from '@tinymce/tinymce-react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom'
import { getActivityId, addInformation, addMessageAddInfo, changeStatus, addMessageChange } from '../../../redux/actions/activityA.js';
import ProcessCommentary from '../../Leader/Process/processCommentary.js';
import { ToastContainer, toast } from 'react-toastify';

class AddInformation extends Component {

    componentDidUpdate() {
        if (this.props.messageAddInfo !== '') {
            switch (this.props.messageAddInfo) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.addMessageAddInfo('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAddInfo('');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageChange !== '') {
            switch (this.props.messageChange) {
                case 'notify':
                    toast.success('Notificada con exito.');
                    this.props.addMessageChange('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageChange('');
                    break;
                default:
                    break;
            }
        }
    }

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
    }

    notifyActivity() {
        let activityN = {
            id: sessionStorage.getItem('activity'),
            state: 3,
            requestData: null
        }
        this.props.changeStatus(localStorage.getItem('Token'), activityN)
    }

    componentDidMount() {
        this.props.getActivityId(localStorage.getItem('Token'), sessionStorage.getItem('activity'))
    }

    handleEditorChange = (content, editor) => {
        this.valor = content;
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <ToastContainer />
                <br />
                <div className="card">
                    <div className="card-body">
                        <button type="button" onClick={() => this.notifyActivity()} className="btn text-light btn-sm float-right naranja " >
                            Notificar
                        </button>
                        <h3 className="card-title text-center">Agregar informacion</h3>
                        <h5>Descripción:</h5>
                        <p className="px-3">{this.props.dataModel.description}</p>
                        <hr />
                        <div className="text-center">
                            <Editor
                                apiKey="spssdb50vwk3go6qwrl2ktj7y3ltm94smrx3pj4pg92ypbx8"
                                initialValue={this.props.dataModel.information}
                                init={{
                                    language: 'es',
                                    language_url: '../../node_modules/@tinymce/language/es.js',
                                    height: 500,
                                    menubar: false,
                                    images_upload_handler: function (blobInfo, success, failure) {
                                        success("data:" + blobInfo.blob().type + ";base64," + blobInfo.base64());
                                    },
                                    paste_data_images: true,
                                    plugins: [
                                        'uploadimage', 'image',
                                        'advlist autolink lists link image charmap print preview anchor',
                                        'searchreplace visualblocks code fullscreen',
                                        'insertdatetime media table paste code help wordcount',
                                        'table'
                                    ],
                                    fontsize_formats: '11px 12px 14px 16px 18px 24px 36px 48px',
                                    font_formats: 'Andale Mono=andale mono,times; Arial=arial,helvetica,sans-serif; Arial Black=arial black,avant garde; Book Antiqua=book antiqua,palatino; Comic Sans MS=comic sans ms,sans-serif; Courier New=courier new,courier; Georgia=georgia,palatino; Helvetica=helvetica; Impact=impact,chicago; Symbol=symbol; Tahoma=tahoma,arial,helvetica,sans-serif; Terminal=terminal,monaco; Times New Roman=times new roman,times; Trebuchet MS=trebuchet ms,geneva; Verdana=verdana,geneva; Webdings=webdings; Wingdings=wingdings,zapf dingbats',
                                    toolbar:
                                        'uploadimage image | undo redo | formatselect | bold italic backcolor | fontsizeselect  fontselect | \
                                        alignleft aligncenter alignright alignjustify | table | \
                                        bullist numlist outdent indent  | removeformat | help '

                                }}
                                onEditorChange={this.handleEditorChange}
                            />
                        </div>

                    </div>

                    <button style={{ width: "10%" }} onClick={() => this.saveView()} className="btn btn-sm text-light float-right naranja">
                        Guardar
                    </button>
                </div>
                <br />
                <ProcessCommentary />
            </div>
        );
    }

}

function mapStateToProps(state) {
    return {
        dataModel: state.activity.activityR,
        messageAddInfo: state.activity.messageAddInfo,
        messageChange: state.activity.messageChange
    }
}

export default withRouter(connect(mapStateToProps, { getActivityId, addInformation, addMessageAddInfo, changeStatus, addMessageChange })(AddInformation));