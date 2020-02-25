import jsPDF from "jspdf";
import "jspdf-autotable";
import React from 'react';


class Reporte extends React.Component {

  constructor() {
    super();
    this.state = {
      people: [
        { name: "Keanu Reeves", profession: "Actor" ,age:"32"},
        { name: "Lionel Messi", profession: "Football Player",age:"56" },
        { name: "Cristiano Ronaldo", profession: "Football Player",age:"22" },
        { name: "Jack Nicklaus", profession: "Golf Player",age:"43" },
      ]
    }
  }

  exportPDF = () => {
    const unit = "pt";
    const size = "A4"; // Use A1, A2, A3 or A4
    const orientation = "portrait"; // portrait or landscape

    const marginLeft = 40;
    const doc = new jsPDF(orientation, unit, size);

    doc.setFontSize(15);

    const title = "My Awesome Report";
    const headers = [["NAME", "PROFESSION","AGE"]];

    const data = this.state.people.map(elt=> [elt.name, elt.profession,elt.age]);

    let content = {
      startY: 50,
      head: headers,
      body: data
    };

    doc.text(title, marginLeft, 40);
    doc.autoTable(content);
    doc.save("report.pdf")
  }

  render() {
    return (
      <div>
        <button onClick={() => this.exportPDF()}>Generate Report</button>
      </div>
    );
  }
}

export default Reporte;