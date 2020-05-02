import { Component } from 'react';
import React from 'react';
import ReactToExcel from 'react-html-table-to-excel';

class Prueba extends Component {
    render() {
        return (
            <div>
                <table id="tabla" border="1" style={{display:"none"}}>
                    <tbody>
                        <tr style={{backgroundColor:"red"}} >
                            <td  rowspan="2">area formacion</td>
                            <td  rowspan="2">nulceo thematico</td>
                            <td  colspan="4">obetivo general del nucleo</td>
                            <td  colspan="3">profesional</td>
                            <td  colspan="3">ocupacional</td>
                        </tr>
                        <tr >
                            <td >fusagasuga</td>
                            <td >facatativa</td>
                            <td >chia</td>
                            <td >ubate</td>
                            <td >perfil 1</td>
                            <td >perfil 2</td>
                            <td >perfil 3</td>
                            <td >perfil 1</td>
                            <td >perfil 2</td>
                            <td >perfil 3</td>
                        </tr>
                        <tr >
                            <td  rowspan="6">area</td>
                            <td  rowspan="3">nucle</td>
                            <td  rowspan="3">obj</td>
                            <td  rowspan="3">obj</td>
                            <td  rowspan="3">obj</td>
                            <td  rowspan="3">ojb</td>
                            <td >fc</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                        </tr>
                        <tr >
                            <td >fc</td>
                            <td >&nbsp;</td>
                            <td >fc</td>
                            <td >&nbsp;</td>
                            <td >fc</td>
                            <td >&nbsp;</td>
                        </tr>
                        <tr>
                            <td >fc</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >fc</td>
                        </tr>
                        <tr>
                            <td  rowspan="3">nucleo</td>
                            <td  rowspan="3">obj</td>
                            <td  rowspan="3">obj</td>
                            <td  rowspan="3">obj</td>
                            <td  rowspan="3">obh</td>
                            <td >fg</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >gf</td>
                            <td >fg</td>
                            <td >&nbsp;</td>
                        </tr>
                        <tr>
                            <td >&nbsp;</td>
                            <td >gf</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >fg</td>
                            <td >&nbsp;</td>
                        </tr>
                        <tr>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >gf</td>
                            <td >&nbsp;</td>
                            <td >fg</td>
                            <td >gf</td>
                        </tr>
                        <tr>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                            <td >&nbsp;</td>
                        </tr>
                    </tbody>
                </table>
                <ReactToExcel
                    className="btn"
                    table="tabla"
                    filename="excel"
                    sheet="sheet 1"
                    buttonText="EXPORTAR"
                />
            </div>
        )
    }
}
export default Prueba;