import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import useAuth from "../../../hooks/useAuth";
import { createEvent, updateEvent, getEventById } from "../../../api/eventsApi";
import BackToButton from "../../../components/BackToButton/BackToButton";
import "./EventFormPage.css";

export default function EventFormPage() {
  const { user, loading } = useAuth("ADMIN");
  const { eventId } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: "",
    description: "",
    category: "",
    zonedDateTime: "",
    venue: "",
    price: "",
    image: "",
  });
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    if (eventId) {
      const fetchEvent = async () => {
        try {
          const res = await getEventById(eventId);
          const event = res.data || res;
          setFormData({
            name: event.name || "",
            description: event.description || "",
            category: event.category || "",
            zonedDateTime: event.zonedDateTime
              ? event.zonedDateTime.slice(0, 16)
              : "",
            venue: event.venue || "",
            price: event.price || "",
            image: event.imageUrl || "",
          });
        } catch (err) {
          console.error(err);
          setError("Failed to load event data");
        }
      };
      fetchEvent();
    }
  }, [eventId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSaving(true);
    setError("");

    try {
      const eventData = {
        name: formData.name,
        description: formData.description,
        category: formData.category,
        zonedDateTime: new Date(formData.zonedDateTime).toISOString(),
        venue: formData.venue,
        price: formData.price,
      };

      const payload = new FormData();
      payload.append(
        "event",
        new Blob([JSON.stringify(eventData)], { type: "application/json" })
      );

      if (formData.image && formData.image instanceof File) {
        payload.append("image", formData.image);
      }

      if (eventId) {
        await updateEvent(eventId, payload);
      } else {
        await createEvent(payload);
      }

      navigate("/events");
    } catch (err) {
      console.error(err);
      setError("Failed to save event");
    } finally {
      setSaving(false);
    }
  };

  if (loading) return <p>Checking permissions...</p>;

  return (
    <div className="event-form-page">
      <BackToButton />
      <h2 className="form-title">
        {eventId ? "Update Event" : "Add New Event"}
      </h2>

      {error && <p className="error-text">{error}</p>}

      <form className="event-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Event Name</label>
          <input
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            className="form-input"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="description">Description</label>
          <textarea
            id="description"
            name="description"
            value={formData.description}
            onChange={handleChange}
            className="form-textarea"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="category">Category</label>
          <select
            id="category"
            name="category"
            value={formData.category}
            onChange={handleChange}
            className="form-input"
            required
          >
            <option value="">-- Select Category --</option>
            <option value="Music">Music</option>
            <option value="Sports">Sports</option>
            <option value="Tech">Tech</option>
            <option value="Education">Education</option>
            <option value="Art">Art</option>
            <option value="Other">Other</option>
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="zonedDateTime">Date & Time</label>
          <input
            type="datetime-local"
            id="zonedDateTime"
            name="zonedDateTime"
            value={formData.zonedDateTime}
            onChange={handleChange}
            className="form-input"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="venue">Venue</label>
          <input
            id="venue"
            name="venue"
            value={formData.venue}
            onChange={handleChange}
            className="form-input"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="price">Price</label>
          <input
            type="number"
            step="0.01"
            id="price"
            name="price"
            value={formData.price}
            onChange={handleChange}
            className="form-input"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="image">Upload Image</label>
          <input
            type="file"
            id="image"
            name="image"
            accept="image/*"
            onChange={(e) => {
              const file = e.target.files[0];
              if (file) {
                setFormData((prev) => ({ ...prev, image: file }));
              }
            }}
            className="form-input"
          />
        </div>

        <button type="submit" className="btn submit-btn" disabled={saving}>
          {saving ? "Saving..." : eventId ? "Update Event" : "Add Event"}
        </button>
      </form>
    </div>
  );
}
