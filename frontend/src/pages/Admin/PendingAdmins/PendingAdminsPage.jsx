import { useEffect, useState } from "react";
import { getPendingAdmins, handleAdminAction } from "../../../api/adminsApi";
import "./PendingAdminsPage.css";
import BackToDashboardButton from "../../../components/BackToDashboardButton/BackToDashboardButton";
import useAuth from "../../../hooks/useAuth";

export default function PendingAdminsPage() {
  const { user, loading } = useAuth("ADMIN");
  const [admins, setAdmins] = useState([]);
  const [error, setError] = useState("");
  const [pageLoading, setPageLoading] = useState(true);

  useEffect(() => {
    const fetchAdmins = async () => {
      try {
        const data = await getPendingAdmins();
        setAdmins(data);
      } catch (err) {
        setError("Failed to load pending admins");
      } finally {
        setPageLoading(false);
      }
    };
    fetchAdmins();
  }, []);

  const handleAction = async (id, action) => {
    try {
      await handleAdminAction(id, action);
      setAdmins((prev) => prev.filter((admin) => admin.id !== id));
    } catch (err) {
      alert("Error performing action");
    }
  };

  if (loading || pageLoading) return <p className="loading-text">Loading...</p>;
  if (error) return <p className="error-text">{error}</p>;

  return (
    <div className="pending-admins">
      <h2 className="page-title">Pending Admins</h2>
      <BackToDashboardButton />
      {admins.length === 0 ? (
        <p className="empty-text">No pending admins found</p>
      ) : (
        <ul className="admins-list">
          {admins.map((admin) => (
            <li key={admin.id} className="admin-card">
              <div className="admin-info">
                <p><span className="label">First Name:</span> {admin.firstName}</p>
                <p><span className="label">Last Name:</span> {admin.lastName}</p>
                <p><span className="label">Username:</span> {admin.username}</p>
                <p><span className="label">Email:</span> {admin.email}</p>
              </div>
              <div className="admin-actions">
                <button
                  className="btn btn-approve"
                  onClick={() => handleAction(admin.id, "approve")}
                >
                  Approve
                </button>
                <button
                  className="btn btn-reject"
                  onClick={() => handleAction(admin.id, "reject")}
                >
                  Reject
                </button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
