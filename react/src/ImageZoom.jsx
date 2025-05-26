import React, { useState } from 'react';
import wallpaper from './wallpaper_7.jpg';

function ImageZoom() {
    const [scale, setScale] = useState(1);

    const zoomin = () => {
        setScale(prev => Math.min(prev + 0.1, 3));
    };
    const zoomout = () => {
        setScale(prev => Math.max(prev - 0.1, 0.5));
    };
    const reset = () => {
        setScale(1);
    };

    return (
        <div style={{ padding: '20px' }}>
            <div style={{ marginBottom: '20px' }}>
                <button onClick={zoomin} style={{ marginRight: '10px' }}>Zoom In</button>
                <button onClick={zoomout} style={{ marginRight: '10px' }}>Zoom Out</button>
                <button onClick={reset}>Reset</button>
            </div>

            <div
                style={{
                    transform: `scale(${scale})`,
                    transition: 'transform 0.2s',
                    display: 'inline-block',
                }}
            >
                <img
                    src={wallpaper}
                    alt="wallpaper"
                    style={{
                        width: '400px',
                        height: 'auto',
                        display: 'block'
                    }}
                />
            </div>
        </div>
    );
}

export default ImageZoom;
