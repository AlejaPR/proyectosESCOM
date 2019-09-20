import React from 'react';


import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';


//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'


import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';

class ModalExample extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      modal: false,
      nombremodulo: '',
      descripcion: '',
      logo: ''
    };



    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState(prevState => ({
      modal: !prevState.modal
    }));
  }



  onSubmit = () => {
    //console.log(evento);
    console.log('aqui');
    console.log(this.props);

    fetch('http://localhost:8080/SuperadminustradorESCOM-web/api/usu/', {
      method: 'post',
      headers: new Headers({
        'Content-Type': 'application/json'
      }),
      body: JSON.stringify({
        'nombremodulo': this.state.nombremodulo,
        'descripcion': this.state.descripcion,
        'logo': this.state.logo
      })
    });
    this.props.funcion(this.state.nombremodulo, this.state.descripcion, this.state.logo);

  }



  onChange = (evento) => {
    console.log([evento.target.name]);
    this.setState({

      [evento.target.name]: evento.target.value

    });
    console.log(this.state);
  }


  render() {
    return (
      <div>
        <Button color="danger" className="btn btn-dark" style={fondoBoton} onClick={this.toggle}>Crear modulo +</Button>
        <Modal isOpen={this.state.modal}
          toggle={this.toggle}
          className={this.props.className}
          size="col-md-6"
        >
          <ModalHeader toggle={this.toggle} className="center">Crear modulo</ModalHeader>
          <ModalBody>
            <form onSubmit={this.onSubmit}>
              <div className="contenedor-inputs">
                <div className="row">
                  <div className="col-sm-12">
                    <input type="text" name="nombremodulo" id="inputNombreModulo" onChange={this.onChange} value={this.state.nombremodulo} className="form-control form-control-solid placeholder-no-fix form-group" placeholder="Nombre de modulo" />
                  </div>
                  <div className="col-sm-12">
                    <textarea type="text" name="descripcion" id="inputDescripcion" onChange={this.onChange} value={this.state.apellido} className="form-control form-control-solid placeholder-no-fix form-group" placeholder="Descripción de modulo" />
                  </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span> Elegir imagen representativa</span>
                        <input type="file" name="files[]" multiple="" width="200" height="40"/> 
                    </div>
                </div>
              </div>
            </form>

          </ModalBody>
          <ModalFooter>
            <Button style={fondoBoton} onClick={this.onSubmit}>Registrar</Button>{''}
            <Button color="secondary" onClick={this.toggle}>Cancelar</Button>
          </ModalFooter>
        </Modal>
      </div>
    );
  }
}

const fondoBoton = {
  background: "#ec671d",
  fontSize: "13px",
  fontFamily: "sans-serif"

}



export default ModalExample;
