import { useEffect, useState } from "react";
import { getEvents, deleteEvent } from "../../../api/eventsApi";
import { bookEvent, getUserBookings } from "../../../api/bookingsApi";
import EventCard from "../../../components/EventCard/EventCard";
import BackToButton from "../../../components/BackToButton/BackToButton";
import { useNavigate } from "react-router-dom";
import "./EventsListPage.css";
import useAuth from "../../../hooks/useAuth";
import Notification from "../../../components/Notification/Notification";

export default function EventsListPage() {
  const { user, loading } = useAuth();
  const [events, setEvents] = useState([]);
  const navigate = useNavigate();
  const [notification, setNotification] = useState(null);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const res = await getEvents();
        setEvents(res.data.content);
      } catch (err) {
        console.error("Error fetching events", err);
      }
    };
    fetchEvents();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteEvent(id);
      setEvents(events.filter((e) => e.id !== id));
    } catch (err) {
      console.error("Delete failed", err);
    }
  };

  const handleBook = async (eventId) => {
    if (!user || !user.id) return;

    try {
      await bookEvent(user.id, eventId);

      const bookings = await getUserBookings(user.id);
      const totalTickets = bookings.content.reduce(
        (sum, b) => sum + b.quantity,
        0
      );

      setNotification({
        type: "success",
        title: "Ticket booked",
        message: `You booked 1 ticket.\nTotal tickets booked: ${totalTickets}`,
        actions: [
          { label: "View Bookings", onClick: () => navigate("/bookings") },
        ],
      });
    } catch (err) {
      setNotification({
        type: "error",
        title: "Booking failed",
        message: "Please try again.",
      });
    }
  };

  if (loading) return <p>Loading...</p>;

  if (user.role !== "ADMIN" && user.role !== "USER") {
    return <p>You are not authorized to view this page</p>;
  }

  return (
    <div className="events-page">
      {user.role === "USER" && (
        <div className="view-bookings-container">
          <button className="btn" onClick={() => navigate("/bookings")}>
            View Bookings
          </button>
          <button className="btn" onClick={() => navigate("/profile")}>
            View Profile
          </button>
        </div>
      )}
      {user.role === "ADMIN" && <BackToButton />}

      {user.role === "ADMIN" && (
        <div className="add-event-container">
          <button
            className="btn"
            onClick={() => navigate("/admins/events/add")}
          >
            Add Event
          </button>
        </div>
      )}

      <div className="events-grid">
        {events.map((event) => (
          <EventCard
            key={event.id}
            event={event}
            role={user.role}
            onUpdate={() => navigate(`/admins/events/update/${event.id}`)}
            onDelete={() => handleDelete(event.id)}
            onBook={() => handleBook(event.id)}
            onView={() => navigate(`/events/${event.id}`)}
          />
        ))}
      </div>

      {notification && (
        <Notification
          type={notification.type}
          title={notification.title}
          message={notification.message}
          actions={notification.actions}
          onClose={() => setNotification(null)}
        />
      )}
    </div>
  );
}
