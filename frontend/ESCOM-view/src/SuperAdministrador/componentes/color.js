import React from 'react';
// import ImageUploader from 'react-images-upload';
// import { NotificationContainer, NotificationManager } from 'react-notifications';
// import 'react-notifications/lib/notifications.css';
import {browserHistory} from 'react-dom';
// var fs = require('fs')
// var path = require('path')
// var util = require('util')

// class App extends React.Component {

//     constructor(props) {
//         super(props);
//         this.state = { pictures: [] };
//         this.onDrop = this.onDrop.bind(this);
//     }

//     onDrop(picture) {
//         console.log('Picture ', picture);
//         this.setState({
//             pictures: this.state.pictures.concat(picture),
//         });
//     }

//     votar = () => {
//         NotificationManager.success('Usuario registrado');
//         storeWithOriginalName(this.state.pictures,'public/uploads');
//     }

//     render() {
//         return (
//             <>
//                 <ImageUploader
//                     withIcon={true}
//                     buttonText='Choose images'
//                     onChange={this.onDrop}
//                     imgExtension={['.jpg', '.gif', '.png', '.gif']}
//                     maxFileSize={5242880}
//                     label="cosasoaj"
//                 />
//                 <button onClick={this.votar}>oprimir</button>
//                 <NotificationContainer />
//             </>
//         );
//     }
// }

// function storeWithOriginalName(file,destination) {
//     var fullNewPath = path.join(destination,file[0].name)
//     console.log("FS ",fs.rename)
//     var rename = util.promisify(fs.rename)

//     var pathq='public\\uploads\\cc8b951ef8ce45d6195ec787947462c3';
//     return rename(pathq, fullNewPath)
//         .then(() => {
//             return file[0].name
//         })
// }

export default function() {
    class AutoLogout extends React.Component {
      constructor(props) {
        super(props);
        this.state = {
          warningTime: 1000 * 60 * 10,
          signoutTime: 1000 * 60 * 15,
        };
      }
  
      componentDidMount() {
        this.events = [
          'load',
          'mousemove',
          'mousedown',
          'click',
          'scroll',
          'keypress'
        ];
  
        for (var i in this.events) {
          window.addEventListener(this.events[i], this.resetTimeout);
        }
  
        this.setTimeout();
      }
  
      clearTimeoutFunc = () => {
        if (this.warnTimeout) clearTimeout(this.warnTimeout);
  
        if (this.logoutTimeout) clearTimeout(this.logoutTimeout);
      };
  
      setTimeout = () => {
        this.warnTimeout = setTimeout(this.warn, this.state.warningTime);
        this.logoutTimeout = setTimeout(this.logout, this.state.signoutTime);
      };
  
      resetTimeout = () => {
        this.clearTimeoutFunc();
        this.setTimeout();
      };
  
      warn = () => {
        window.alert("You will be logged out automatically in 1 minute")
        console.log('You will be logged out automatically in 1 minute.');
      };
  
      logout = () => {
        // Send a logout request to the API
        console.log('Sending a logout request to the API...');
        this.destroy();
      };
  
      destroy = () => {
       //clear the session
        browserHistory.push('/');
        window.location.assign('/');
      };
  
      render() {
        return (
          <div>
            <div style={{background:"black"}}></div>
          </div>
        );
      }
    }
  }