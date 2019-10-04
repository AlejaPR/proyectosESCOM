// import React from 'react'
// import ColorPicker from 'react-color-picker'
 
// import 'react-color-picker/index.css'
 
// class App extends React.Component {
 
//   constructor(props) {
//     super(props)
//     this.state = {
//       color: 'red'
//     }
//   }
 
//   onDrag(color, c) {
//     this.setState({
//       color
//     })
//   }
 
//   render() {
//     return <div>
//       <ColorPicker value={this.state.color} onDrag={this.onDrag.bind(this)} />
//       <div style={{
//         background: this.state.color,
//         width: 100,
//         height: 50,
//         color: 'white'
//       }}>
//         {this.state.color}
//       </div>
//     </div>
//   }
 
// }

import React from 'react'
import { SketchPicker } from 'react-color'
 
class Component extends React.Component {
 
  state = {
    background: '#fff',
  };

  handleChangeComplete = (color) => {
    console.log('cambio');
    this.setState({ background: color.hex });
  };

  render() {
    return <SketchPicker color={this.state.background} onChangeComplete={ this.handleChangeComplete } />
  }
}

export default(Component);

