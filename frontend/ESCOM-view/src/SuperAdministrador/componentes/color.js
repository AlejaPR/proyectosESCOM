import React from 'react';
import ImageUploader from 'react-images-upload';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import 'react-notifications/lib/notifications.css';

var fs = require('fs')
var path = require('path')
var util = require('util')

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = { pictures: [] };
        this.onDrop = this.onDrop.bind(this);
    }

    onDrop(picture) {
        console.log('Picture ', picture);
        this.setState({
            pictures: this.state.pictures.concat(picture),
        });
    }

    votar = () => {
        NotificationManager.success('Usuario registrado');
        storeWithOriginalName(this.state.pictures,'public/uploads');
    }

    render() {
        return (
            <>
                <ImageUploader
                    withIcon={true}
                    buttonText='Choose images'
                    onChange={this.onDrop}
                    imgExtension={['.jpg', '.gif', '.png', '.gif']}
                    maxFileSize={5242880}
                    label="cosasoaj"
                />
                <button onClick={this.votar}>oprimir</button>
                <NotificationContainer />
            </>
        );
    }
}

function storeWithOriginalName(file,destination) {
    var fullNewPath = path.join(destination,file[0].name)
    console.log("FS ",fs.rename)
    var rename = util.promisify(fs.rename)

    var pathq='public\\uploads\\cc8b951ef8ce45d6195ec787947462c3';
    return rename(pathq, fullNewPath)
        .then(() => {
            return file[0].name
        })
}

export default App;