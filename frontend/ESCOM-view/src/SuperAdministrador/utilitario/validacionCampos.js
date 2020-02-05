export const requerido = value => {
    if (!value) {
        return 'Este campo es obligatorio *';
    }
}

export const nombre = value => {
    if (value !== undefined) {
        if (value.length < 3) {
            return 'ingrese mas de tres caracteres';
        }
    }
}

export const apellido = value => {
    if (value !== undefined) {
        if (value.length < 4) {
            return 'ingrese mas de dos caracteres';
        }
    }
}

export const validacionCuarentaCaracteres = value => {
    if (value !== undefined) {
        if (value.length > 40) {
            return 'El limite es de 40 caracteres';
        }
    }

}

export const validacionDoscientosCaracteres = value => {
    if (value !== undefined) {
        if (value.length > 200) {
            return 'El limite es de 200 caracteres';
        }
    }

}


export const documentoIdentificacion = value => {
    if (value !== undefined) {
        if (value.length < 6) {
            return 'ingrese un numero de documento valido';
        }
    }
}

export const correo = value => value && !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(value) ? 'Invalid email address' : undefined;

export const contrasena = value => value && !/^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/i.test(value) ? 'Invalid format password' : undefined;

export const fechaNacimiento = value => {
    var x = new Date(value);
    var actual = new Date();
    var resta = actual.getFullYear() - x.getFullYear();
    if (resta > 99 || resta < 15) {
        return 'La fecha ingresada es incorrecta';
    }
};

export const seleccione = value => {
    if (value === '0' || value === undefined) {
        return 'Seleccione un tipo de documento';
    }
};

