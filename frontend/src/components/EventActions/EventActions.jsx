import "./EventActions.css";

export default function EventActions({ role, onUpdate, onDelete, onBook, onView }) {
  return (
    <div className="event-actions">
      {role === "ADMIN" ? (
        <>
          <button className="btn update" onClick={onUpdate}>Update</button>
          <button className="btn delete" onClick={onDelete}>Delete</button>
          <button className="btn view" onClick={onView}>View</button>
        </>
      ) : (
        <>
          <button className="btn book" onClick={onBook}>Book Now</button>
          <button className="btn view" onClick={onView}>View</button>
        </>
      )}

    </div>
  );
}
