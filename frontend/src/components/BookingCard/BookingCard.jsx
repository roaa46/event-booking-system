import "./BookingCard.css";

export default function BookingCard({ booking }) {
  const { personName, eventName, bookingDateTime, quantity } = booking;

  return (
    <div className="booking-card">
      <h3 className="event-name">{eventName}</h3>
      <p><strong>User:</strong> {personName}</p>
      <p><strong>Booking Date:</strong> {new Date(bookingDateTime).toLocaleString()}</p>
      <p><strong>Quantity:</strong> {quantity}</p>
    </div>
  );
}
