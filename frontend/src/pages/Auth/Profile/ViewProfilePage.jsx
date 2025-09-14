import { useEffect, useState } from "react";
import { viewProfile } from "../../../api/authApi";
import "./ViewProfilePage.css";
import BackToButton from "../../../components/BackToButton/BackToButton";

export default function ViewProfile() {
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

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


  if (loading) return <p className="profile__loading">Loading profile...</p>;
  if (error) return <p className="profile__error">{error}</p>;

  return (
    <div className="profile">
      <h2 className="profile__title">My Profile</h2>
      <BackToButton/>
      {profile ? (
        <div className="profile__card">
          <p><span className="profile__label">First Name:</span> {profile.firstName}</p>
          <p><span className="profile__label">Last Name:</span> {profile.lastName}</p>
          <p><span className="profile__label">Username:</span> {profile.username}</p>
          <p><span className="profile__label">Email:</span> {profile.email}</p>
          {profile.phone && (
            <p><span className="profile__label">Phone:</span> {profile.phone}</p>
          )}
          <p><span className="profile__label">Role:</span> {profile.role}</p>

        </div>
      ) : (
        <p className="profile__empty">No profile data available</p>
      )}
    </div>
  );
}
