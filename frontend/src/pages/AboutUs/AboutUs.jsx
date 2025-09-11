import "./AboutUs.css";

function AboutUs() {
  return (
    <section className="about">
      <div className="about-container">
        {/* Left side - text */}
        <div className="about-text">
          <h2>About Us</h2>
          <p>
            At <strong>Eventify</strong>, we believe that events bring people together.  
            Whether it’s a music concert, a conference, or a local workshop,  
            our mission is to make discovering and booking events seamless and exciting.
          </p>
        </div>

        {/* Right side - image */}
        <div className="about-image">
          <img src="/src/assets/about.jpg" alt="About Eventify" />
        </div>
      </div>
    </section>
  );
}

export default AboutUs;
