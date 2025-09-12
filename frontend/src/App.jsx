import { Route, Routes } from 'react-router-dom';
import { useState } from "react";

import './App.css'
import Navbar from './components/Navbar/Navbar'
import Home from './pages/Home/home';
import Footer from './components/Footer/Footer';
import AboutUs from './pages/AboutUs/AboutUs';
import LoginPage from './pages/Auth/Login/LoginPage'
import RegisterPage from './pages/Auth/Register/RegisterPage'
import ViewProfile from './pages/Auth/Profile/ViewProfilePage';
import PendingAdmins from './pages/Admin/PendingAdmins/PendingAdminsPage';
import AdminDashboard from './pages/Admin/AdminDashboard/AdminDashboard';
import EventsListPage from './pages/Events/EventsListPage/EventsListPage';
import EventFormPage from './pages/Events/EventFormPage/EventFormPage';
import EventDetailPage from './pages/Events/EventDetailPage/EventDetailPage'
import MyBookingsPage from './pages/Bookings/MyBookingsPage';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  return (
    <>
    <Navbar isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />

    <Routes>
        <Route path="/" element= {
          <Home/>
        } />

        <Route path="/about" element= {
          <AboutUs/>
        } />

        <Route path="/login" element= {
          <LoginPage setIsLoggedIn={setIsLoggedIn}/>
        } />

        <Route path="/register" element= {
          <RegisterPage/>
        } />

        <Route path="/profile" element= {
          <ViewProfile/>
        }/>

        <Route path="/pending-admins" element= {
          <PendingAdmins/>
        }/>

        <Route path="/admins/dashboard" element= {
          <AdminDashboard/>
        }/>

        <Route path="/events" element= {
          <EventsListPage/>
        }/>

        <Route path="/admins/events/add" element={
          <EventFormPage />
        } />

        <Route path="/admins/events/update/:eventId" element={
          <EventFormPage />
        } />

        <Route path="/events/:eventId" element={
          <EventDetailPage />
        } />

        <Route path="/bookings" element={
          <MyBookingsPage />
        } />

      </Routes>
      <Footer/>
    </>
  )
}

export default App
