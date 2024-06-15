import React, { useState } from 'react';
import './App.css';
import SignUp from './Pages/SignUp';
import About from './Pages/About';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import { Navbar, Nav, Container } from 'react-bootstrap';
import ForgotPassword from './Pages/ForgotPassword';
import ResetPassword from './Pages/ResetPassword';
import Login from './Pages/Login';
import User from './Pages/User';
import ViewAppliedJobs from './Pages/ViewAppliedJobs';
import logo from './Picture/image.png';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('jwtToken') !== null);

  const handleLogout = () => {
    localStorage.clear();
    setIsLoggedIn(false); // Update login state
    window.location.href = '/';
  };

  const handleLogin = () => {
    setIsLoggedIn(true); // Update login state on successful login
  };

  return (
    <div className="App">
      <BrowserRouter>
        <Navbar bg="dark" variant="dark" className="px-2 py-2" expand="lg">
          <Container>
            <Navbar.Brand href="/user">
              <img
                src={logo}
                alt="RP Logo"
                height="30"
                className="d-inline-block align-top text-bold"
              />{' '}
              Straw_hat_Careers
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                
              </Nav>
              {isLoggedIn ? (
                <Nav>
                  <Nav.Link href="/profile">Profile</Nav.Link>
                  <Nav.Link href="/applied-jobs">Applied Jobs</Nav.Link>
                  <Nav.Link href="/saved-jobs">Saved Jobs</Nav.Link>
                  <Nav.Link onClick={handleLogout}>Logout</Nav.Link>
                </Nav>
              ) : (
                <Nav>
                  <Nav.Link href="/login">Login</Nav.Link>
                  <Nav.Link href="/register">Register</Nav.Link>
                  <Nav.Link href="/about">About</Nav.Link>
                </Nav>
              )}
            </Navbar.Collapse>
          </Container>
        </Navbar>
        <Routes>
          <Route path="/register" element={<SignUp />} />
          <Route path="/about" element={<About />} />
          <Route path="/" element={<Login onLogin={handleLogin} />} /> {/* Pass the onLogin prop */}
          <Route path="/login" element={<Login onLogin={handleLogin} />} /> {/* Pass the onLogin prop */}
          <Route path="/user" element={<User />} />
          <Route path="/applied-jobs" element={<ViewAppliedJobs />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />
          <Route path="/resetpassword" element={<ResetPassword />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;
