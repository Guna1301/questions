import React, { useState, useEffect } from 'react';

function DigitalClock() {
  const [intervalMinutes, setIntervalMinutes] = useState(0);
  const [currentTime, setCurrentTime] = useState(new Date());
  const [isRunning, setIsRunning] = useState(false);
  const [remainingTime, setRemainingTime] = useState(0);

  useEffect(() => {
    let timerInterval = null;

    if (isRunning && remainingTime > 0) {
      timerInterval = setInterval(() => {
        setCurrentTime(new Date());
        setRemainingTime(prevTime => prevTime - 1);
      }, 1000);
    } else if (!isRunning || remainingTime <= 0) {
      clearInterval(timerInterval);
    }

    return () => clearInterval(timerInterval);
  }, [isRunning, remainingTime]);

  const startTimer = () => {
    if (remainingTime === 0) {
      setRemainingTime(intervalMinutes * 60);
    }
    setIsRunning(true);
  };

  const stopTimer = () => {
    setIsRunning(false);
  };

  const handleIntervalChange = (event) => {
    setIntervalMinutes(event.target.value);
  };

  const formatTime = (sec) => {
    const hours = Math.floor(sec / 3600);
    const minutes = Math.floor((sec % 3600) / 60);
    const seconds = sec % 60;
    return `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
  };

  const formatRemainingTimeInMinutes = () => {
    return Math.floor(remainingTime / 60);
  };

  return (
    <div className="digital-clock">
      <div>
        <label>Set Timer Interval (Minutes): </label>
        <input
          type="number"
          value={intervalMinutes}
          onChange={handleIntervalChange}
          min="1"
        />
      </div>
      <div className="clock">
        <h1>{currentTime.toLocaleTimeString()}</h1>
      </div>
      <div>
        <p>Remaining Time: {isRunning ? formatTime(remainingTime) : formatRemainingTimeInMinutes()} {isRunning ? '' : 'minutes'}</p>
      </div>
      <div>
        <button onClick={startTimer} disabled={isRunning}>Start</button>
        <button onClick={stopTimer} disabled={!isRunning}>Stop</button>
      </div>
    </div>
  );
}

export default DigitalClock;
