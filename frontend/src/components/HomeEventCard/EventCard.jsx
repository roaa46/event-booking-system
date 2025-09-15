import "./EventCard.css";

export default function EventCard({ title, description, image }) {
  return (
    <div className="card">
      <img src={image} alt={title} className="card__image" />

      <div className="card__content">
        <p className="card__title">{title}</p>
        <p className="card__description">{description}</p>
      </div>
    </div>
  );
}

