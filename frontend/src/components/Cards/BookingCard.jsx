import { FaCalendarAlt, FaMapMarkerAlt, FaDollarSign, FaUser, FaTicketAlt, FaTag, FaFileAlt } from "react-icons/fa";
import "./Cards.css";

export default function BookingCard({ booking }) {
  const { eventName, bookingDateTime, quantity } = booking;

  return (
    <div className="card booking">
      <h3 className="card-title">{eventName}</h3>
      <p className="card-text"><FaCalendarAlt className="detail-icon" /> {new Date(bookingDateTime).toLocaleString()}</p>
      <p className="card-text"><FaTicketAlt className="detail-icon" /> Quantity: {quantity}</p>
    </div>
  );
}
