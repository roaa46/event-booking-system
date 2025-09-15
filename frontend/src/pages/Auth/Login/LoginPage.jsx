import { useState } from "react";
import { login as loginAPI } from "../../../api/authApi";
import "./LoginPage.css";
import Cookies from "universal-cookie";
import { useNavigate, Link } from "react-router-dom";
import { useAuth } from "../../../context/AuthContext";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();
  const { login } = useAuth();
  const cookies = new Cookies();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      cookies.remove("token", { path: "/" });

      const res = await loginAPI({ email, password });

      cookies.set("token", res.token, { path: "/", maxAge: 60 * 60 * 24 });

      login({ email: res.email, role: res.role, username: res.username }); // instead of setIsLoggedIn

      if (res.role === "ADMIN") {
        navigate("/admins/dashboard");
      } else if (res.role === "PENDING_ADMIN") {
        navigate("/profile");
      }
        else if(res.role === "USER") {
          navigate("/events");
        }
      
    } catch (err) {
      console.error(err);
      setMessage("Login failed. Please check your credentials.");
    }
  };

  return (
    <div>
      <h2 className="login">Login</h2>
      <form className="form" onSubmit={handleSubmit}>
        <span className="input-span">
          <label htmlFor="email" className="label">
            Email
          </label>
          <input
            type="email"
            name="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </span>

        <span className="input-span">
          <label htmlFor="password" className="label">
            Password
          </label>
          <input
            type="password"
            name="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </span>

        {message && <p>{message}</p>}

        <input className="submit" type="submit" value="Log in" />

        <span className="span">
          Don't have an account? <Link to="/register">Sign up</Link>
        </span>
      </form>
    </div>
  );
}
