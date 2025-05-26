const WebSocket = require('ws');
const mongoose = require('mongoose')
const auto = require('mongoose-sequence')(mongoose)

mongoose.connect(`mongodb://localhost:27017/web-socket`,{
  useNewUrlParser:true,
  useUnifiedTopology:true
}).then(()=>console.log('connected to db'))

const wss = new WebSocket.Server({ port: 8080 });
const employeeSchema = new mongoose.Schema({
  _id:Number,
  name: String,
  salary: Number,
  role: String,
  department: String,
  experience: Number
},{_id:false});

employeeSchema.plugin(auto, {inc_field:"_id"});
const Employee = mongoose.model('Employee',employeeSchema)

wss.on('connection', (ws) => {
  console.log('New client connected');

  ws.on('message', async(message) => {
    const messageStr = message.toString(); 
    console.log(`Received message: ${messageStr}`);

    const [command, ...data] = messageStr.split(' ');
    console.log("com ", command)

    switch (command) {
      case 'INSERT':
        const [name,salary,role, dep,exp] = data;
        const employee = new Employee({name:name,salary,role,department:dep,experience:exp});
        await employee.save();
        ws.send(`Employee inserted successfully.ID: ${employee._id}`);
        break;
      case 'RETRIEVE':
          let response = '';
          const employees = await Employee.find();
          employees.forEach((emp) => {
            response += `ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years\n`;
          });
          ws.send(response.trim());
        break;
      case 'RETRIEVE_BY_DEPT':
          const dept = data;
          const emps = await Employee.find({department:dept});
          let res = "";
          emps.forEach((emp) => {
            res += `ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years\n`;
          });
          ws.send(res);
          break;


      default:
        ws.send('Invalid command.');
    }
  });

  ws.on('close', () => {
    console.log('Client disconnected');
  });
});

console.log('WebSocket server is running on ws://localhost:8080');
