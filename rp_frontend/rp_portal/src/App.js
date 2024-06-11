import './App.css';
import SignUp from './Pages/SignUp';
import About from './Pages/About';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';
import ForgotPassword from './Pages/ForgotPassword';
import ResetPassword from './Pages/ResetPassword';
import Login from './Pages/Login';
import User from './Pages/User';
import ViewAppliedJobs from './Pages/ViewAppliedJobs';

const App = () => {
  const isLoggedIn = localStorage.getItem('jwtToken') !== null;

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = '/';
  };

  return (
    <div className="App">
      <BrowserRouter>
        <Navbar bg="dark" variant="dark" className="px-2 py-2" expand="lg">
          <Container>
            <Navbar.Brand href="/user">RP</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                <Nav.Link href="/">Home</Nav.Link>
                {!isLoggedIn && <Nav.Link href="/about">About</Nav.Link>}
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
                </Nav>
              )}
            </Navbar.Collapse>
          </Container>
        </Navbar>
        <Routes>
          <Route path="/register" element={<SignUp />} />
          <Route path="/about" element={<About />} />
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
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
