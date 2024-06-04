import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default class ForgotPassword extends Component {
  constructor(props) {
    super(props);

    this.state = {
      fullName: '',
      phoneNumber: '',
      message: '',
      showError: false
    };

    this.onChange = this.onChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  onChange = (e) => this.setState({ [e.target.name]: e.target.value });

  handleSubmit(event) {
    event.preventDefault();

    axios.get('http://localhost:8080/users/forgotpassword', {
      params: {
        fullName: this.state.fullName,
        phoneNumber: this.state.phoneNumber
      }
    }).then((res) => {
      if (res.status === 200) {
        localStorage.setItem('Id', res.data.id);
        console.log(localStorage.getItem('Id'));
        this.setState({ message: 'Password reset link sent to your email.', showError: false });
      }
    }).catch((error) => {
      if (error.response && error.response.status === 404) {
        this.setState({ message: 'User not found. Please check your details and try again.', showError: true });
      } else {
        this.setState({ message: 'Error sending password reset link. Please try again.', showError: true });
      }
    });
  }

  render() {
    return (
      <div className="container py-1 h-80 mt-3">
        <div className="row d-flex align-items-center justify-content-center h-100">
          <div className="col-md-7 col-lg-5 col-xl-5 offset-xl-1 border border-1 shadow-sm mb-3 p-5">
            <p className="text-center">Forgot Password</p>
            <form onSubmit={this.handleSubmit}>
              <div className="form-outline mb-4 text-start">
                <input
                  type="text"
                  id="fullName"
                  name="fullName"
                  value={this.state.fullName}
                  onChange={this.onChange}
                  className="form-control form-control-lg"
                  required
                />
                <label className="form-label" htmlFor="fullName">
                  Full Name
                </label>
              </div>
              <div className="form-outline mb-4 text-start">
                <input
                  type="text"
                  id="phoneNumber"
                  name="phoneNumber"
                  value={this.state.phoneNumber}
                  onChange={this.onChange}
                  className="form-control form-control-lg"
                  required
                />
                <label className="form-label" htmlFor="phoneNumber">
                  Phone Number
                </label>
              </div>
              <div className='text-center' id="resetMessage">
                {this.state.message && <span>{this.state.message}</span>}
              </div>
              <div className="text-center">
                <button type="submit" className="btn btn-primary btn-lg btn-block">Send Reset Link</button>
              </div>
              <div className="text-center mt-3">
                <Link to="/login">Back to Login</Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }
}
