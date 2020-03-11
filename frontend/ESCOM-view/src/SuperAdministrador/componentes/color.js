// import jsPDF from "jspdf";
// import "jspdf-autotable";
// import React from 'react';


// class Reporte extends React.Component {

//   constructor() {
//     super();
//     this.state = {
//       people: [
//         { name: "Keanu Reeves", profession: "Actor", age: "32" },
//         { name: "Lionel Messi", profession: "Football Player", age: "56" },
//         { name: "Cristiano Ronaldo", profession: "Football Player", age: "22" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Keanu Reeves", profession: "Actor", age: "32" },
//         { name: "Lionel Messi", profession: "Football Player", age: "56" },
//         { name: "Cristiano Ronaldo", profession: "Football Player", age: "22" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Keanu Reeves", profession: "Actor", age: "32" },
//         { name: "Lionel Messi", profession: "Football Player", age: "56" },
//         { name: "Cristiano Ronaldo", profession: "Football Player", age: "22" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Keanu Reeves", profession: "Actor", age: "32" },
//         { name: "Lionel Messi", profession: "Football Player", age: "56" },
//         { name: "Cristiano Ronaldo", profession: "Football Player", age: "22" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },
//         { name: "Jack Nicklaus", profession: "Golf Player", age: "43" },

//       ]
//     }
//   }

//   headRows = () => {
//     return [
//       { id: 'ID', name: 'Name', email: 'Email', city: 'City', expenses: 'Sum' },
//     ]
//   }

//   // function footRows() {
//   //   return [
//   //     { id: 'ID', name: 'Name', email: 'Email', city: 'City', expenses: 'Sum' },
//   //   ]
//   // }

//   // function columns() {
//   //   return [
//   //     { header: 'ID', dataKey: 'id' },
//   //     { header: 'Name', dataKey: 'name' },
//   //     { header: 'Email', dataKey: 'email' },
//   //     { header: 'City', dataKey: 'city' },
//   //     { header: 'Exp', dataKey: 'expenses' },
//   //   ]
//   // }

//   // function data(rowCount) {
//   //   rowCount = rowCount || 10
//   //   let body = []
//   //   for (var j = 1; j <= rowCount; j++) {
//   //     body.push({
//   //       id: j,
//   //       name: faker.name.findName(),
//   //       email: faker.internet.email(),
//   //       city: faker.address.city(),
//   //       expenses: faker.finance.amount(),
//   //     })
//   //   }
//   //   return body
//   // }

//   bodyRows = (rowCount) => {
//     rowCount = rowCount || 10
//     let body = []
//     for (var j = 1; j <= rowCount; j++) {
//       body.push({
//         id: j,
//         name: window.faker.name.findName(),
//         email: window.faker.internet.email(),
//         city: window.faker.address.city(),
//         expenses: window.faker.finance.amount(),
//       })
//     }
//     return body
//   }

//   exportPDF = () => {
//     var doc = new jsPDF()
//     var totalPagesExp = '{total_pages_count_string}'
//     const headers = [["NAME", "PROFESSION","AGE"]];
//     const data = this.state.people.map(elt=> [elt.name, elt.profession,elt.age]);
//     doc.autoTable({
//       head: headers,
//       body: data,
//       didDrawPage: function(data) {
//         // Header
//         doc.setFontSize(20)
//         doc.setTextColor(40)
//         doc.setFontStyle('normal')
//         // if (base64Img) {
//         //   doc.addImage(base64Img, 'JPEG', data.settings.margin.left, 15, 10, 10)
//         // }
//         doc.text('Report', data.settings.margin.left + 15, 22)
  
//         // Footer
//         var str = 'Page ' + doc.internal.getNumberOfPages()
//         // Total page number plugin only available in jspdf v1.0+
//         if (typeof doc.putTotalPages === 'function') {
//           str = str + ' of ' + totalPagesExp
//         }
//         doc.setFontSize(10)
  
//         // jsPDF 1.4+ uses getWidth, <1.4 uses .width
//         var pageSize = doc.internal.pageSize
//         var pageHeight = pageSize.height ? pageSize.height : pageSize.getHeight()
//         doc.text(str, data.settings.margin.left, pageHeight - 10)
//       },
//       margin: { top: 30 },
//     })
//     doc.save('myrepo.pdf');
//   }

//   render() {
//       return(
//       <div>
//     <button onClick={() => this.exportPDF()}>Generate Report</button>
//       </div >
//     );
//   }
// }
// export default Reporte
import 'date-fns';
import React from 'react';
import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import {
  MuiPickersUtilsProvider,
  KeyboardTimePicker,
  KeyboardDatePicker,
} from '@material-ui/pickers';

export default function MaterialUIPickers() {
  // The first commit of Material-UI
  const [selectedDate, setSelectedDate] = React.useState(new Date('2020-03-18T21:11:54'));

  const handleDateChange = date => {
    setSelectedDate(date);
  };

  return (
    
    // <MuiPickersUtilsProvider utils={DateFnsUtils}>
    //   <Grid container justify="space-around">
    //     {/* <KeyboardDatePicker
    //       disableToolbar
    //       variant="inline"
    //       format="MM/dd/yyyy"
    //       margin="normal"
    //       id="date-picker-inline"
    //       label="Date picker inline"
    //       value={selectedDate}
    //       onChange={handleDateChange}
    //       KeyboardButtonProps={{
    //         'aria-label': 'change date',
    //       }}
    //     /> */}
    //     <KeyboardDatePicker
    //       margin="normal"
    //       id="date-picker-dialog"
    //       label="Date picker dialog"
    //       format="MM/dd/yyyy"
    //       minDate={selectedDate}
    //       value={selectedDate}
    //       onChange={handleDateChange}
    //       KeyboardButtonProps={{
    //         'aria-label': 'change date',
    //       }}
    //     />
    //     {/* <KeyboardTimePicker
    //       margin="normal"
    //       id="time-picker"
    //       label="Time picker"
    //       value={selectedDate}
    //       onChange={handleDateChange}
    //       KeyboardButtonProps={{
    //         'aria-label': 'change time',
    //       }}
    //     /> */}
    //   </Grid>
    // </MuiPickersUtilsProvider>
    <>
    </>
  );
}
