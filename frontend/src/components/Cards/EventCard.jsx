import EventActions from "../EventActions/EventActions";
import { FaCalendarAlt, FaMapMarkerAlt, FaDollarSign, FaTag } from "react-icons/fa";
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
        <p className="card-description">{event.description}</p>
        
        <div className="event-details">
          <div className="detail-item">
            <FaTag className="detail-icon" />
            <span>{event.category}</span>
          </div>
          <div className="detail-item">
            <FaCalendarAlt className="detail-icon" />
            <span>{event.zonedDateTime ? new Date(event.zonedDateTime).toLocaleString() : "No date"}</span>
          </div>
          <div className="detail-item">
            <FaMapMarkerAlt className="detail-icon" />
            <span>{event.venue}</span>
          </div>
          <div className="detail-item">
            <FaDollarSign className="detail-icon" />
            <span>{event.price !== undefined ? `${event.price.toFixed(2)}` : "Free"}</span>
          </div>
        </div>
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