var express = require('express');
var app = express();
var multer = require('multer')
var cors = require('cors');
var path = require('path');
var des = 'public/documents';
var rest = ''

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.use(cors())
var storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, des)
  },
  filename: function (req, file, cb) {
    rest = Date.now() + file.originalname
    cb(null, rest)
  }
})

var upload = multer({ storage: storage }).array('file')

app.get('/', function (req, res) {
  return res.send('Hello Server')
})
app.post('/upload', (req, res) => {
  upload(req, res, (err) => {
    if (err instanceof multer.MulterError) {
      return res.status(500).json(err)
    } else if (err) {
      return res.status(500).json(err)
    }
    return res.status(200).send('documents/'+ rest)
  })
});

app.listen(8000, function () {
  console.log('App running on port 8000');
});