import "./Footer.css";
import { Link } from "react-router-dom";

export default function Footer() {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-brand">
          <h3>Eventify</h3>
          <p>Discover events, connect with people, and make memories.</p>
        </div>

        <ul className="footer-links">
          <li><Link to="/">Home</Link></li>
          <li><Link to="/about">About</Link></li>
        </ul>

        <div className="footer-socials">
          <Link to="#"><i className="fab fa-facebook-f"></i></Link>
          <Link to="#"><i className="fab fa-twitter"></i></Link>
          <Link to="#"><i className="fab fa-instagram"></i></Link>
        </div>
      </div>

      <div className="footer-bottom">
        <p>© {new Date().getFullYear()} Eventify. All rights reserved.</p>
      </div>
    </footer>
  );
}

