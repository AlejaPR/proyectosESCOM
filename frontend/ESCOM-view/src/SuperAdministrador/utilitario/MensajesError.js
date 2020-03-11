export const mensajesDeError = respuesta => {
    switch (respuesta) {
        case 'Token requerido':
            return 'Token requerido';

        case 'Sin permiso':
            return 'Sin permiso';

        case 'token vencido':
            return 'Token requerido';

        case 'token no registrado':
            return 'Token requerido';

        case 'token incorrecto':
            return 'Token requerido';

        case 'Ocurrio un error interno del servidor':
            return 'Token requerido';
        default:
            return '';
    }
};

export const formatoFecha = (fecha) => {
    let fechaRecibida = new Date(fecha);
    let dia = fechaRecibida.getUTCDate();
    let mes = fechaRecibida.getUTCMonth();
    let ano = fechaRecibida.getFullYear();
    return new Date(ano, mes, dia);
}
