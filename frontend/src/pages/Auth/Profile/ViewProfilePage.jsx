import { useEffect, useState } from "react";
import { viewProfile, deleteAccount } from "../../../api/authApi"; // استدعاء API للحذف
import "./ViewProfilePage.css";
import { FaUser, FaUserShield, FaCrown } from "react-icons/fa";
import BackToButton from "../../../components/BackToButton/BackToButton";
import { useNavigate } from "react-router-dom";

export default function ViewProfile() {
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [deleting, setDeleting] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const data = await viewProfile();
        setProfile(data);
      } catch (err) {
        setError(err?.response?.data?.message || "Error fetching profile");
      } finally {
        setLoading(false);
      }
    };
    fetchProfile();
  }, []);

  const handleDelete = async () => {
    if (!window.confirm("Are you sure you want to delete this account?"))
      return;

    try {
      setDeleting(true);
      await deleteAccount();
      alert("Account deleted successfully.");
      navigate("/");
      window.location.reload();
    } catch (err) {
      alert(err?.response?.data?.message || "Error deleting account");
    } finally {
      setDeleting(false);
    }
  };

  const getRoleIcon = (role) => {
    switch (role?.toLowerCase()) {
      case "admin":
        return <FaUserShield />;
      case "superadmin":
        return <FaCrown />;
      default:
        return <FaUser />;
    }
  };

  if (loading) return <p className="profile__loading">Loading profile...</p>;
  if (error) return <p className="profile__error">{error}</p>;

  return (
    <div className="profile">
      <h2 className="profile__title">My Profile</h2>

      {profile ? (
        <div className="profile__card">
          <div className="profile__role">
            <div className="profile__role-icon">
              {getRoleIcon(profile.role)}
            </div>
            <p className="profile__role-text">{profile.role}</p>
          </div>

          <div className="profile__details">
            <p>
              <span className="profile__label">First Name:</span>{" "}
              {profile.firstName}
            </p>
            <p>
              <span className="profile__label">Last Name:</span>{" "}
              {profile.lastName}
            </p>
            <p>
              <span className="profile__label">Username:</span>{" "}
              {profile.username}
            </p>
            <p>
              <span className="profile__label">Email:</span> {profile.email}
            </p>
            {profile.phone && (
              <p>
                <span className="profile__label">Phone:</span> {profile.phone}
              </p>
            )}
          </div>

          <BackToButton />
          <button
            className="profile__deleteBtn"
            onClick={handleDelete}
            disabled={deleting}
          >
            {deleting ? "Deleting..." : "Delete This Account"}
          </button>
        </div>
      ) : (
        <p className="profile__empty">No profile data available</p>
      )}
    </div>
  );
}
