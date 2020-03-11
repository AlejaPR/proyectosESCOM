import React from "react";
import ReactDOM from "react-dom";

import TextField from "@material-ui/core/TextField";
import { withStyles } from "@material-ui/core/styles";

const styles = {
    inputRoot: {
        fontSize: 14
    },
    labelRoot: {
        fontSize: 14,
        color: "red",
        heigth: '3px',
        "&$labelFocused": {
            color: "purple",
            fontSize: 16
        }
    },
    labelFocused: {}
};
function renderAlgo({
    input,
    label,classes,
    meta: { touched, error },
    ...custom
}) {
    return (
        <TextField
            id="standard-with-placeholder"
            label="Nombre"
            InputProps={{ classes: { root: classes.inputRoot } }}
            InputLabelProps={{
                classes: {
                    root: classes.labelRoot,
                    focused: classes.labelFocused
                }
            }}
            {...input}
            {...custom}
            variant='outlined'
            margin="normal"
            size='small'
        />
    )
}

let formulario =  withStyles(styles)(renderAlgo);

export default formulario;

