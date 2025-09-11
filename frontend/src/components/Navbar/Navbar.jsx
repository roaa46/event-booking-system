import './Navbar.css';
import { useState, useEffect } from 'react';
import Cookies from 'universal-cookie';
import {viewProfile, logout } from '../../api/authApi';

const Navbar = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
    const checkLogin = async () => {
      try {
        const res = await viewProfile(); // GET /auth/me
        if (res && res.email) {
          setIsLoggedIn(true);
        } else {
          setIsLoggedIn(false);
        }
      } catch (err) {
        setIsLoggedIn(false);
      }
    };
    checkLogin();
  }, []);

  const handleLogout = async () => {
    try {
      await logout(); // POST /auth/logout
      setIsLoggedIn(false);
      window.location.href = '/';
    } catch (err) {
      console.error('Logout failed', err);
    }
  };
    return (
        <nav className="navbar">
        <div className="navbar-logo">
        <a href="/">
          <svg xmlns="http://www.w3.org/2000/svg" width="180" height="40" viewBox="0 0 180 40" role="img" aria-label="Eventify logo">
                        {/* Pin shape */}
                        <path d="M20 4a10 10 0 0 0-10 10c0 6 10 18 10 18s10-12 10-18a10 10 0 0 0-10-10z"
                              fill="url(#grad)" />

                        {/* Ticket inside pin */}
                        <g>
                            <rect x="12" y="10" width="16" height="10" rx="2" ry="2" fill="#fff"/>
                            {/* ticket notches */}
                            <circle cx="12" cy="15" r="1" fill="url(#grad)"/>
                            <circle cx="28" cy="15" r="1" fill="url(#grad)"/>
                            {/* small star */}
                            <polygon points="20,12 21,14 23,14 21.5,15 22.2,17 20,16 17.8,17 18.5,15 17,14 19,14"
                                     fill="url(#grad)"/>
                        </g>

                        {/* Gradient definition */}
                        <defs>
                            <linearGradient id="grad" x1="0" y1="0" x2="1" y2="1">
                                <stop offset="0%" stopColor="#F43F5E"/> {/* pink/red */}
                                <stop offset="100%" stopColor="#6366F1"/> {/* indigo */}
                            </linearGradient>
                        </defs>

                        {/* Brand name */}
                        <text x="44" y="26" fontFamily="Poppins, Segoe UI, Roboto, sans-serif"
                              fontSize="18" fontWeight="600" fill="#0B2545">Eventify</text>
                    </svg>

        </a>
        </div>
        <ul className="navbar-links">
        <li><a href="/">Home</a></li>
        <li><a href="/about">About Us</a></li>
        </ul>
        <div className="navbar-actions">
                {isLoggedIn ? (
                    <button onClick={handleLogout} className="navbar-btn">Logout</button>
                ) : (
                    <>
                        <a href="/login" className="navbar-btn">Login</a>
                        <a href="/register" className="navbar-btn">Register</a>
                    </>
                )}
            </div>
      </nav>
    );
};

export default Navbar;