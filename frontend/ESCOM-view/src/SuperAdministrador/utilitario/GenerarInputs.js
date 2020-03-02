
import React from 'react';

import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputAdornment from '@material-ui/core/InputAdornment';
import IconButton from '@material-ui/core/IconButton';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import FormHelperText from '@material-ui/core/FormHelperText';

export const generarSelect = ({ input, label, type, meta: { touched, error }, children }) => (
  <div>
    <div>
      <select {...input} className="form-control letra" style={{ height: "32px", fontSize: "13px" }}>
        {children}
      </select>
      {touched && ((error && <span className="text-danger form-group" style={{ fontSize: '12px', fontFamily: 'sans-serif' }}>{error}</span>))}
    </div>
  </div>
)

export const generarInput = ({ input, disabled, label, type, meta: { touched, error, warning } }) => (
  <div>
    <div>
      <input {...input} disabled={disabled} placeholder={label} type={type} style={{ height: "35px", fontSize: "13px", fontFamily: 'sans-serif' }} className="form-control placeholder-no-fix" />
      {touched && ((error && <span className="text-danger form-group" style={{ fontSize: '12px', fontFamily: 'sans-serif' }}>{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
)

export default function RenderPasword({
  input,
  label,
  meta: { touched, error },
  ...custom
}) {
  const [values, setValues] = React.useState({
    amount: '',
    password: '',
    weight: '',
    weightRange: '',
    showPassword: false,
  });

  const handleChange = prop => event => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const handleClickShowPassword = () => {
    setValues({ ...values, showPassword: !values.showPassword });
  };

  const handleMouseDownPassword = event => {
    event.preventDefault();
  };

  return (
    <>
      <OutlinedInput
        id="outlined-adornment-password"
        type={values.showPassword ? 'text' : 'password'}
        onChange={handleChange('password')}
        placeholder="Contraseña"
        {...input}
        {...custom}
        error={(touched && error) ? true : false}
        aria-describedby={(touched && error) ? error : ''}
        endAdornment={
          <InputAdornment position="end">
            <IconButton
              aria-label="toggle password visibility"
              onClick={handleClickShowPassword}
              onMouseDown={handleMouseDownPassword}
              edge="end"
            >
              {values.showPassword ? <Visibility /> : <VisibilityOff />}
            </IconButton>
          </InputAdornment>
        }
        style={{ height: "35px" }}
      />
      <FormHelperText id="outlined-weight-helper-text">{error}</FormHelperText>
    </>
  );
}

export const campo = value => {

  if (value !== null && value !== undefined) {
    var arrayDeCadenas = value.substr(0, 24);
    switch (arrayDeCadenas) {
      case 'data:image/jpeg;base64,/':
        return value;
      case 'data:image/png;base64,iV':
        return value;
      case 'dataimage/jpegbase64/9j/':
        var nuevaCadena = value.replace('dataimage/jpegbase64', 'data:image/jpeg;base64,');
        return nuevaCadena;
      default:
        let nuevaCadenadOs = value.replace('dataimage/pngbase64', 'data:image/png;base64,');
        return nuevaCadenadOs + 'g==';
    }
  }
};

export const generarTextArea = ({ input, label, meta: { touched, error, warning } }) => (
  <div>
    <div>
      <textarea {...input} placeholder={label} style={{ fontSize: "13px" }} className="form-control letra form-control-solid placeholder-no-fix" />
      {touched && ((error && <span className="text-danger form-group" style={{ fontSize: '12px', fontFamily: 'sans-serif' }}>{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
);