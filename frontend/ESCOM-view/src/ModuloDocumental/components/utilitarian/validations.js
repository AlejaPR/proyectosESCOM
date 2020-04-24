export const required = value => {
    if (!value) {
        return 'Este campo es obligatorio *';
    }
}

export const thousand = value => {
    if (value !== undefined) {
        if (value.length > 1000) {
            return 'El limite es de 1000 caracteres';
        }
    }
}

export const fiveHundred = value => {
    if (value !== undefined) {
        if (value.length > 500) {
            return 'El limite es de 500 caracteres';
        }
    }
}

export const threeHundred = value => {
    if (value !== undefined) {
        if (value.length > 300) {
            return 'El limite es de 300 caracteres';
        }
    }
}

export const twenty = value => {
    if (value !== undefined) {
        if (value.length > 20) {
            return 'El limite es de 20 caracteres';
        }
    }
}

export const minimum = value => {
    if (value !== undefined) {
        if (value.length < 3) {
            return 'ingrese mas de tres caracteres';
        }
    }
}

export const select = value => {
    if (value === '0' || value === undefined || value === "") {
        return 'Seleccione una opcion';
    }
};


