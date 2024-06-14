import React, { Component } from 'react';
import axios from 'axios';
import { Navigate, Link } from 'react-router-dom';
import image from '../Picture/R.jpeg';
import Footer from './Footer';

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: '',
      password: '',
      isloggedin: localStorage.getItem('jwtToken') !== null,
      showError: false
    };

    this.onChange = this.onChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  onChange = (e) => this.setState({ [e.target.name]: e.target.value });

  handleSubmit(event) {
    event.preventDefault();
    localStorage.clear();

    const params = new URLSearchParams({
      email: this.state.email,
      password: this.state.password
    }).toString();

    axios.get(`http://localhost:8080/users/login?${params}`).then((res) => {
      if (res.status === 200) {
        console.log(res.data);
        localStorage.setItem('jwtToken', res.data.accessToken);
        localStorage.setItem('userId', res.data.userId);
        this.setState({ isloggedin: true });
        this.props.onLogin(); // Call the onLogin callback
      }
    }).catch(() => {
      this.setState({ showError: true });
    });
  }

  render() {
    if (this.state.isloggedin) {
      return (
        <Navigate to="/user" />
      );
    }

    return (
      <>
        <div className="container py-1 h-80 mt-3">
          <div className="row d-flex align-items-center justify-content-center h-100">
            <div className="col-md-8 col-lg-7 col-xl-6">
              <img 
                src={image} 
                alt="Home" 
                style={{
                  height: '600px',
                  width: '600px'
                }}
              />
            </div>
            <div className="col-md-7 col-lg-5 col-xl-5 offset-xl-1 border border-1 shadow-sm mb-3 p-5">
              <p className="text-center" id='loginHeading'>Sign in to get Started</p>
              <form onSubmit={this.handleSubmit}>
                <div className="form-outline mb-4 text-start">
                  <input
                    type="email"
                    id="email"
                    name="email"
                    value={this.state.email}
                    onChange={this.onChange}
                    className="form-control form-control-lg"
                    required
                  />
                  <label className="form-label" htmlFor="email">
                    Email address
                  </label>
                </div>

                <div className="form-outline mb-4 text-start">
                  <input
                    type="password"
                    id="password"
                    name="password"
                    onChange={this.onChange}
                    value={this.state.password}
                    className="form-control form-control-lg"
                    required
                  />
                  <label className="form-label" htmlFor="password">
                    Password
                  </label>
                </div>
                <div className='text-center' id="invalidCredentials">
                  {this.state.showError && <span>Please Enter Correct Credentials!</span>}
                </div>
                <div className="text-center">
                  <button type="submit" className="btn btn-primary btn-lg btn-block">Sign in</button>
                  <div className="align-items-center my-4 text-start">
                    <p className="fw-bold mx-3 mb-0 text-muted text-center">Or</p>
                  </div>
                  <Link
                    className="btn btn-primary btn-lg btn-block"
                    style={{ backgroundColor: "#008000" }}
                    to="/register"
                    role="button"
                  >
                    Create new Account
                  </Link>
                  <div className="text-center mt-3">
                    <Link to="/forgot-password">Forgot Password?</Link>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
        <Footer />
      </>
    );
  }
}
