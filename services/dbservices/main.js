const express = require("express")
const bodyParser = require("body-parser")
const app = express()
const mysql = require("mysql")
const config = require("./config.json")
const baseUrl = `localhost:${config.appPort}`
const cookieParser = require("cookie-parser")

/**
 * 
 * headers:{
 *     authorization:barerToken
 *     
 * }
 * body:{
 *  
 *      query:"SELECT * FROM USER WHERE id = ?"
 *          
 *      
 * }
 */
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json())

app.use((req, res, next) =>{
    res.header('Access-Control-Allow-Origin', '*'); // Permitir acceso a todos los dominios
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Athorization');
    if (req.method === 'OPTIONS') {
      res.header('Access-Control-Allow-Methods', 'PUT, POST, PATCH, DELETE, GET');
      return res.status(200).json({});
    }
    next();
   });


app.post(`/mysql`,(req,res)=>{
    try{
        var con = mysql.createConnection(config.mysql);
        console.log("entrato")
        const [rows,fileds] = con.query(req.query);
        res.send({fields,rows})
        con.end()
    }catch(err){
        console.log(err)
        res.status(500).send(
            {
                message:err.message
            }
        )
    }
})

/**
 * 
 * headers:{
 *     authorization:barerToken
 *     
 * }
 * 
 * body:{
 *      parametricQuery:"SELECT * FROM USER WHERE id = ?"
 *      parameters:[
 *          "p1",
 *          "p2",
 *          "p3"
 *      ]
 *          
 *      
 * }
 */
app.post(`mysql/parametric`,()=>{

})

/*
    body:{
        query:"find"
        params:{

        }
    }
*/
app.post(`/mongo`,()=>{
    
})

app.listen(config.appPort)