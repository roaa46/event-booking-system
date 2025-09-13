import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getEventById } from "../../../api/eventsApi";
import BackToButton from "../../../components/BackToButton/BackToButton";
import "./EventDetailPage.css";

export default function EventDetailPage() {
  const { eventId } = useParams();
  const [event, setEvent] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchEvent = async () => {
      try {
        const res = await getEventById(eventId);
        const data = res.data || res;
        setEvent(data);
      } catch (err) {
        console.error(err);
        setError("Failed to load event details");
      } finally {
        setLoading(false);
      }
    };
    fetchEvent();
  }, [eventId]);

  if (loading) return <p>Loading event details...</p>;
  if (error) return <p className="error-text">{error}</p>;
  if (!event) return <p>No event found</p>;

  return (
    <div className="event-detail-page">
      <BackToButton />
      <h2 className="event-title">{event.name}</h2>

      <img
        src={
          event.image && event.image.trim() !== ""
            ? `http://localhost:8080${event.image}`
            : "/src/assets/no-img.png"
        }
        alt={event.name || "Event"}
        className="event-image"
      />

      <p>
        <strong>Description:</strong> {event.description}
      </p>
      <p>
        <strong>Category:</strong> {event.category}
      </p>
      <p>
        <strong>Date & Time:</strong>{" "}
        {new Date(event.zonedDateTime).toLocaleString()}
      </p>
      <p>
        <strong>Venue:</strong> {event.venue}
      </p>
      <p>
        <strong>Price:</strong> ${event.price}
      </p>
    </div>
  );
}
