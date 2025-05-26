const WebSocket = require('ws');

const ws = new WebSocket('ws://localhost:8080');

ws.on('open', () => {
  console.log('Connected to WebSocket server');

  ws.send('INSERT Alice 50000 Developer IT 5');
  ws.send('INSERT Bob 60000 Manager IT 5');
  ws.send('RETRIEVE');
  ws.send('RETRIEVE_BY_DEPT IT')
  ws.send('INVALID');
});

ws.on('message', (data) => {
  console.log('Server Response:', data.toString());
});

ws.on('error', (error) => {
  console.error('WebSocket Error:', error);
});

ws.on('close', () => {
  console.log('Disconnected from WebSocket server');
});
