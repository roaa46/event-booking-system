import EventActions from "../EventActions/EventActions";
import "./EventCard.css";

export default function EventCard({
  event,
  role,
  onUpdate,
  onDelete,
  onBook,
  onView,
}) {
  if (!event) return null;
  return (
    <div className="event-card">
      <img
        src={
          event.image && event.image.trim() !== ""
            ? `http://localhost:8080${event.image}`
            : "/src/assets/no-img.png"
        }
        alt={event.name || "Event"}
        className="event-image"
      />

      <div className="event-content">
        <h3 className="event-name">{event.name}</h3>
        <p className="event-description">{event.description}</p>
        <p className="event-category">Category: {event.category}</p>
        <p className="event-date">
          {event.zonedDateTime
            ? new Date(event.zonedDateTime).toLocaleString()
            : "No date"}
        </p>
        <p className="event-venue">Venue: {event.venue}</p>
        <p className="event-price">
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
