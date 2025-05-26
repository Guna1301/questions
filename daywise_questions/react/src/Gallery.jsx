import React, { useState, useEffect } from 'react';
import './Gallery.css'; 
import img1 from './avengers_edited.jpg';
import img2 from './transcend.png';
import img3 from './mount-fuji-wallpaper-1.jpg';
import img4 from './w.jpg';
import img5 from './wallpaper_7.jpg';
import img6 from './wallpaper1.jpg';

const images = [img1, img2, img3, img4, img5, img6];

function Gallery() {
  const [isOpen, setIsOpen] = useState(false);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [scale, setScale] = useState(1);

  const openLightbox = (index) => {
    setCurrentIndex(index);
    setIsOpen(true);
  };

  const closeLightbox = () => {
    setIsOpen(false);
    setScale(1);
  };

  const showPrev = () => {
    setCurrentIndex((prevIndex) => (prevIndex - 1 + images.length) % images.length);
    setScale(1);
  };

  const showNext = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
    setScale(1);
  };

  const zoomIn = () => {
    setScale((prevScale) => Math.min(prevScale + 0.1, 3));
  };

  const zoomOut = () => {
    setScale((prevScale) => Math.max(prevScale - 0.1, 0.5));
  };

  useEffect(() => {
    const handleKeyPress = (e) => {
      if (e.key === 'ArrowLeft') {
        showPrev(); 
      }
      if (e.key === 'ArrowRight') {
        showNext(); 
      }
      if (e.key === 'ArrowUp') {
        zoomIn();
      }
      if (e.key === 'ArrowDown') {
        zoomOut();
      }
      if (e.key === 'Escape') {
        closeLightbox(); 
      }
    };

    window.addEventListener('keydown', handleKeyPress);

    return () => {
      window.removeEventListener('keydown', handleKeyPress);
    };
  }, []);

  return (
    <div className="gallery-container">
      <div className="image-grid">
        {images.map((img, index) => (
          <img
            key={index}
            src={img}
            alt={`img-${index}`}
            className="gallery-image"
            onClick={() => openLightbox(index)}
          />
        ))}
      </div>

      {isOpen && (
        <div className="lightbox-overlay">
          <span className="close-btn" onClick={closeLightbox}>×</span>
          <button className="nav-btn left" onClick={showPrev}>←</button>
          <img
            src={images[currentIndex]}
            alt="Full view"
            className="lightbox-image"
            style={{ transform: `scale(${scale})` }} 
          />
          <button className="nav-btn right" onClick={showNext}>→</button>
        </div>
      )}
    </div>
  );
}

export default Gallery;
