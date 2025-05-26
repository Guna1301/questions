import React, { useState, useEffect } from 'react';

const Cal = () => {
  const [num1, setNum1] = useState('');
  const [num2, setNum2] = useState('');
  const [op, setOp] = useState('');
  const [res, setRes] = useState(0);
  const [err, setErr] = useState('');

  useEffect(() => {
    const a = parseFloat(num1);
    const b = parseFloat(num2);

    if (isNaN(a) || isNaN(b)) {
      setErr('Please enter valid numbers');
      setRes('');
      return;
    }

    let result;
    let error = '';

    switch (op) {
      case '+':
        result = a + b;
        break;
      case '-':
        result = a - b;
        break;
      case '*':
        result = a * b;
        break;
      case '/':
        if (b === 0) {
          error = 'Divide by zero is not allowed';
        } else {
          result = a / b;
        }
        break;
      default:
        result = '';
    }

    setErr(error);
    setRes(error ? '' : result);
  }, [num1, num2, op]);

  return (
    <div>
      <h2>Cal</h2>
      <input
        type="number"
        value={num1}
        onChange={(e) => setNum1(e.target.value)}
        placeholder="Enter first number"
      />
      <input
        type="number"
        value={num2}
        onChange={(e) => setNum2(e.target.value)}
        placeholder="Enter second number"
      />

      <div>
        <button onClick={() => setOp('+')}>Add</button>
        <button onClick={() => setOp('-')}>Subtract</button>
        <button onClick={() => setOp('*')}>Multiply</button>
        <button onClick={() => setOp('/')}>Divide</button>
      </div>

      <h3>
        {err ? <span style={{color:"red"}}>{err}</span> : `Result: ${res}`}
      </h3>
    </div>
  );
};

export default Cal;
