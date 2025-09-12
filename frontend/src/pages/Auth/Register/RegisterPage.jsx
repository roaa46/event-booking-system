import { useState } from "react";
import { register } from "../../../api/authApi";
import "../Login/LoginPage.css";

export default function RegisterPage() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [phone, setPhone] = useState("");
  const [role, setRole] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const userData = {
        firstName,
        lastName,
        username,
        email,
        password,
      };
      if (phone) userData.phone = phone;
      userData.role = role.toUpperCase();

      await register(userData);
      window.location.href = "login";
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <h2 className="signup">Sign Up</h2>
      <form className="form" onSubmit={handleSubmit}>
        {/* first name */}
        <span className="input-span">
          <label htmlFor="firstName" className="label">
            First Name
          </label>
          <input
            type="text"
            name="firstName"
            id="firstName"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />
        </span>

        {/* last name */}
        <span className="input-span">
          <label htmlFor="lastName" className="label">
            Last Name
          </label>
          <input
            type="text"
            name="lastName"
            id="lastName"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />
        </span>

        {/* username */}
        <span className="input-span">
          <label htmlFor="username" className="label">
            Username
          </label>
          <input
            type="text"
            name="username"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </span>

        {/* email */}
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

        {/* password */}
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

        {/* phone */}
        <span className="input-span">
          <label htmlFor="phone" className="label">
            Phone
          </label>
          <input
            type="tel"
            name="phone"
            id="phone"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            required
          />
        </span>

        {/* role */}
        <div className="role-selection">
          <label className="role-label">
            <input
              type="radio"
              name="role"
              value="user"
              checked={role === "user"}
              onChange={(e) => setRole(e.target.value)}
              hidden
            />
            <img
              src="/src/assets/user.png"
              alt="User"
              className={role === "user" ? "selected" : ""}
            />
            <span>User</span>
          </label>

          <label className="role-label">
            <input
              type="radio"
              name="role"
              value="admin"
              checked={role === "admin"}
              onChange={(e) => setRole(e.target.value)}
              hidden
            />
            <img
              src="/src/assets/admin.png"
              alt="Admin"
              className={role === "admin" ? "selected" : ""}
            />
            <span>Admin</span>
          </label>
        </div>

        <input className="submit" type="submit" value="Sign Up" />
      </form>
    </div>
  );
}
