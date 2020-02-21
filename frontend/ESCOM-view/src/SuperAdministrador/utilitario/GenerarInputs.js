
import React from 'react';

export const generarSelect = ({ input, label, type, meta: { touched, error }, children }) => (
  <div>
    <div>
      <select {...input} className="form-control letra" style={{ height: "32px", fontSize: "13px" }}>
        {children}
      </select>
      {touched && ((error && <span className="text-danger form-group" style={{fontSize: '12px',fontFamily: 'sans-serif'}}>{error}</span>))}
    </div>
  </div>
)

export const generarInput = ({ input,disabled, label, type, meta: { touched, error, warning } }) => (
  <div>
    <div>
      
      <input {...input} disabled={disabled} placeholder={label} type={type} style={{ height: "35px", fontSize: "13px" ,fontFamily: 'sans-serif'}} className="form-control placeholder-no-fix" />
      {touched && ((error && <span className="text-danger form-group" style={{fontSize: '12px',fontFamily: 'sans-serif'}}>{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
)

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
      <textarea {...input} placeholder={label} style={{ fontSize: "13px"}} className="form-control letra form-control-solid placeholder-no-fix" />
      {touched && ((error && <span className="text-danger form-group" style={{fontSize: '12px',fontFamily: 'sans-serif'}}>{error}</span>) || (warning && <span>{warning}</span>))}
    </div>
  </div>
);