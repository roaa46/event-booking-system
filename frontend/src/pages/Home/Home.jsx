import EventCards from "../../components/HomeEventList/EventCards";
import "./Home.css";
function Home() {
  return (
    <>
    <div className="container">
        <section className="home">
        <div className="home-content">
          <h1>Event Space</h1>
          <p>Your gateway to events that inspire and connect.</p>
        </div>

        <div className="home-image">
          <img src="/src/assets/home.png" alt="Home Image" />
        </div>
      </section>
      <section>
        <div className="explore-content">
          <h2>Explore Events</h2>
          <EventCards />
        </div>
      </section>
    </div>
      
    </>
  );
}
export default Home;
