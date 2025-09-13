import { useNavigate } from "react-router-dom";
import "./AdminDashboard..css";

export default function AdminDashboard() {
  const navigate = useNavigate();

  return (
    <div className="dashboard-container">
      <h2 className="dashboard-title">Admin Dashboard</h2>
      <div className="dashboard-main">
        <div className="dashboard-row">
          <button className="card card1" onClick={() => navigate("/events")}>
            Events
          </button>
          <button
            className="card card2"
            onClick={() => navigate("/pending-admins")}
          >
            Pending Admins
          </button>
        </div>
        <div className="dashboard-row">
          <button className="card card3" onClick={() => navigate("/profile")}>
            View Profile
          </button>
        </div>
      </div>
    </div>
  );
}
