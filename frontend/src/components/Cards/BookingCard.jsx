import "./Cards.css";

export default function BookingCard({ booking }) {
  const { personName, eventName, bookingDateTime, quantity } = booking;

  return (
    <div className="card booking">
      <h3 className="card-title">{eventName}</h3>
      <p className="card-text"><strong>User:</strong> {personName}</p>
      <p className="card-text"><strong>Booking Date:</strong> {new Date(bookingDateTime).toLocaleString()}</p>
      <p className="card-text"><strong>Quantity:</strong> {quantity}</p>
    </div>
  );
}
