export const requerido = value => (value || typeof value === 'number' ? undefined : 'Required');

export const correo = value => value && !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(value)? 'Invalid email address' : undefined;

export const fechaNacimiento = value =>{
    if (!value){
        return 'Required';
    }else {
        var x=new Date(value);
        var actual=new Date();
        var resta=actual.getFullYear()-x.getFullYear();
        if(resta>99 || resta <15){
            return 'La fecha ingresada es incorrecta';
        }
    }
};

export const seleccione = value =>{
    console.log('value es',value)
    if(value==='0' || value === undefined){
        return 'Seleccione un tipo de documento';
    }
};

