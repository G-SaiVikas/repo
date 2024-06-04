import './App.css';
import SignUp from './Pages/SignUp';
import About from './Pages/About';
import { Routes, Route, BrowserRouter } from'react-router-dom';
import { Navbar, Nav, NavDropdown } from 'react-bootstrap'
import Login from './Pages/Login';
import User from './Pages/User';

function App() {
  return (
    
    <div className="App">
      
      <BrowserRouter>
      <Navbar bg="dark" variant="dark" className=" px-2 py-2"  expand="lg">
        
        <Navbar.Toggle aria-controls="basic-navbar-nav" />  
        <Navbar.Collapse id="basic-navbar-nav">
         <Nav className="mr-auto">
           
           
         <Navbar.Brand href="#!">RP</Navbar.Brand>
         </Nav>
       </Navbar.Collapse>
      </Navbar>
      <Routes>
        <Route path="/register" element={<SignUp />} />
        <Route path="/about" element={<About />} />
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/user" element={<User />} />
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
