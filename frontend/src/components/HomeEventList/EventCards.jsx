import EventCard from "../HomeEventCard/EventCard";
import "./EventCards.css";

function EventCards() {
  return (
    <section className="cards-container">
      <EventCard
        title="Music Concert"
        description="Join us for an unforgettable night of live performances and amazing vibes."
        image="/src/assets/music.jpg"
      />

      <EventCard
        title="Tech Conference"
        description="Discover the latest trends in technology, AI, and innovation with industry leaders."
        image="/src/assets/tech.jpg"
      />

      <EventCard
        title="Art Exhibition"
        description="Experience breathtaking artworks and meet the artists behind the masterpieces."
        image="/src/assets/art.jpg"
      />
    </section>
  );
}

export default EventCards;
