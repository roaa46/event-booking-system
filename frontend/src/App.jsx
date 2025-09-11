import { Route, Routes } from 'react-router-dom';

import './App.css'
import Navbar from './components/Navbar/Navbar'
import Home from './pages/Home/home';
import Footer from './components/Footer/Footer';
import AboutUs from './pages/AboutUs/AboutUs';
import LoginPage from './pages/Auth/Login/LoginPage'
import RegisterPage from './pages/Auth/Register/RegisterPage'


function App() {

  return (
    <>
    <Navbar/>

    <Routes>
        <Route path="/" element= {
          <Home/>
        } />

        <Route path="/about" element= {
          <AboutUs/>
        } />

        <Route path="/login" element= {
          <LoginPage/>
        } />

        <Route path="/register" element= {
          <RegisterPage/>
        } />
        
      </Routes>
      <Footer/>
    </>
  )
}

export default App
