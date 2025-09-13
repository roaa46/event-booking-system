import EventActions from "../EventActions/EventActions";
import "./Cards.css";

export default function EventCard({ event, role, onUpdate, onDelete, onBook, onView }) {
  if (!event) return null;

  return (
    <div className="card event">
      <img
        src={
          event.image && event.image.trim() !== ""
            ? `http://localhost:8080${event.image}`
            : "/src/assets/no-img.png"
        }
        alt={event.name || "Event"}
        className="card-image"
      />

      <div className="card-content">
        <h3 className="card-title">{event.name}</h3>
        <p className="card-text">{event.description}</p>
        <p className="card-text">Category: {event.category}</p>
        <p className="card-text">
          {event.zonedDateTime
            ? new Date(event.zonedDateTime).toLocaleString()
            : "No date"}
        </p>
        <p className="card-text">Venue: {event.venue}</p>
        <p className="card-text">
          {event.price !== undefined ? `$${event.price.toFixed(2)}` : "Free"}
        </p>
      </div>

      <EventActions
        role={role}
        onUpdate={onUpdate}
        onDelete={onDelete}
        onBook={onBook}
        onView={onView}
      />
    </div>
  );
}
