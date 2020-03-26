import React, { Component } from 'react';
import { Editor } from '@tinymce/tinymce-react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from "moment";
require('moment/locale/es.js');

const localizer = momentLocalizer(moment);//array de eventos
const myEventsList = [{
    title: "today",
    start: new Date('2020-03-05 10:22:00'),
    end: new Date('2020-03-15 10:42:00')
},
{
    title: "string",
    start: new Date('2020-03-05 12:22:00'),
    end: new Date('2020-03-10 13:42:00')
}]

class Prueba extends Component {
    /*
    constructor(props) {
        super(props);
        this.state = {
            selectedFile: null,
            loaded: 0
        }

    }
    checkMimeType = (event) => {
        //getting file object
        let files = event.target.files
        //define message container
        let err = []
        // list allow mime type
        const types = ['image/png', 'image/jpeg', 'image/gif']
        // loop access array
        for (let xer = 0; xer < files.length; xer++) {
            // compare file type find doesn't matach
            if (types.every(type => files[xer].type !== type)) {
                // create error message and assign to container   
                err[xer] = files[xer].type + ' is not a supported format\n';
            }
        };
        for (var z = 0; z < err.length; z++) {// if message not same old that mean has error 
            // discard selected file
            toast.error(err[z])
            event.target.value = null
        }
        return true;
    }
    maxSelectFile = (event) => {
        let files = event.target.files
        if (files.length > 3) {
            const msg = 'Only 3 images can be uploaded at a time'
            event.target.value = null
            toast.warn(msg)
            return false;
        }
        return true;
    }
    checkFileSize = (event) => {
        let files = event.target.files
        let size = 2000000
        let err = [];
        for (var x = 0; x < files.length; x++) {
            if (files[x].size > size) {
                err[x] = files[x].type + 'is too large, please pick a smaller file\n';
            }
        };
        for (var z = 0; z < err.length; z++) {// if message not same old that mean has error 
            // discard selected file
            toast.error(err[z])
            event.target.value = null
        }
        return true;
    }
    onChangeHandler = event => {
        var files = event.target.files
        if (this.maxSelectFile(event) && this.checkMimeType(event) && this.checkFileSize(event)) {
            // if return true allow to setState
            this.setState({
                selectedFile: files,
                loaded: 0
            })
        }
    }
    onClickHandler = () => {
        const data = new FormData()
        for (var x = 0; x < this.state.selectedFile.length; x++) {
            data.append('file', this.state.selectedFile[x])
        }
        axios.post("http://localhost:8000/upload",data, {
            onUploadProgress: ProgressEvent => {
                this.setState({
                    loaded: (ProgressEvent.loaded / ProgressEvent.total * 100),
                })
            },
        })
            .then(res => { // then print response status
                toast.success('upload success')
                console.log(res)
            })
            .catch(err => { // then print response status
                toast.error('upload fail')
            })
    }

    render() {
        return (
            <div class="container">
                <div class="row">
                    <div class="offset-md-3 col-md-6">
                        <div class="form-group files">
                            <label>Upload Your File </label>
                            <input type="file" class="form-control" multiple onChange={this.onChangeHandler} />
                        </div>
                        <div class="form-group">
                            <ToastContainer />
                            <Progress max="100" color="success" value={this.state.loaded} >{Math.round(this.state.loaded, 2)}%</Progress>

                        </div>

                        <button type="button" class="btn btn-success btn-block" onClick={this.onClickHandler}>Upload</button>

                    </div>
                </div>
            </div>
        );
    }*/

    /*
    convertHtmlToPdf() {
        fetch('https://v2018.api2pdf.com/chrome/html', {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': '65f55388-ac76-43a3-b28b-ea4720dfe2f2' //Get your API key from https://portal.api2pdf.com
            },
            body: JSON.stringify({ html: '<p>hello world from reactjs</p>', inlinePdf: true, fileName: 'test.pdf' })
        }).then(res => res.json())
            .then(res => {
                var downloadLink = document.createElement("a");
                document.body.appendChild(downloadLink);
                downloadLink.href = res.pdf;                
                downloadLink.target="_blank";
                downloadLink.download = "nombres";
                downloadLink.click();
                document.body.removeChild(downloadLink);

            }
            )
    }
    render() {
        return (
            <div className="App">
                <button onClick={this.convertHtmlToPdf.bind(this)}>Generate Pdf</button>
            </div>
        );
    }*/

    
    
    ////////////////////////component tiny

    handleEditorChange = (content, editor) => {
        console.log('Content was updated:', content);
    }

    render() {
        return (
            <div className="container color">
                <br/>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title text-center">Agregar informacion</h5>
                        <Editor
                            apiKey="spssdb50vwk3go6qwrl2ktj7y3ltm94smrx3pj4pg92ypbx8"
                            initialValue="<p>This is the initial content of the editor</p>"
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
            </div>
        );
    }

    /*

    render() {
        return (

            <div className="container color" >
                <div class="card">
                    <div class="card-body">
                        <Calendar
                            localizer={localizer}
                            events={myEventsList}
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
    */
}
export default Prueba;