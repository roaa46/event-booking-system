import { useEffect, useState } from "react";
import { getUserBookings } from "../../api/bookingsApi"
import BookingCard from "../../components/BookingCard/BookingCard"
import useAuth from "../../hooks/useAuth";
import BackToButton from "../../components/BackToButton/BackToButton";
import "./MyBookingsPage.css";

export default function MyBookingsPage() {
  const { user, loading } = useAuth();
  const [bookings, setBookings] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    if (!user) return;

    const fetchBookings = async () => {
      try {
        const res = await getUserBookings(user.id, 0, 20);
        setBookings(res.content || []);
      } catch (err) {
        console.error(err);
        setError("Failed to load bookings");
      }
    };

    fetchBookings();
  }, [user]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p className="error-text">{error}</p>;

  return (
    <div className="my-bookings-page">
      <h2>My Bookings</h2>
      <BackToButton/>
      {bookings.length === 0 ? (
        <p>No bookings yet.</p>
      ) : (
        <div className="bookings-grid">
          {bookings.map((booking) => (
            <BookingCard key={booking.id} booking={booking} />
          ))}
        </div>
      )}
    </div>
  );
}
