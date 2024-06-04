import React, { Component } from 'react';
import axios from 'axios';
import { Navigate } from 'react-router-dom';

export default class ResetPassword extends Component {
  constructor(props) {
    super(props);

    this.state = {
      newPassword: '',
      confirmPassword: '',
      message: '',
      showError: false,
      isDone: false
    };

    this.onChange = this.onChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  onChange = (e) => this.setState({ [e.target.name]: e.target.value });

  handleSubmit(event) {
    event.preventDefault();
    const { newPassword, confirmPassword } = this.state;

    if (newPassword !== confirmPassword) {
      this.setState({ message: 'Passwords do not match', showError: true });
      return;
    }

    const id = localStorage.getItem('Id');
    if (!id) {
      this.setState({ message: 'No user ID found in local storage', showError: true });
      return;
    }

    axios.put('http://localhost:8080/users/resetpassword', null, {
      params: {
        id: id,
        newPassword: newPassword
      }
    }).then((res) => {
      if (res.status === 200) {
        this.setState({ message: 'Password reset successful', showError: false });
        localStorage.clear();
        this.setState({ isDone: true });
      }
    }).catch((error) => {
      this.setState({ message: 'Error resetting password. Please try again.', showError: true });
    });
  }

  render() {
    if (this.state.isDone) {
        return (
            <Navigate to="/login" />
          );
        }

    return (
      <div className="container py-1 h-80 mt-3">
        <div className="row d-flex align-items-center justify-content-center h-100">
          <div className="col-md-7 col-lg-5 col-xl-5 offset-xl-1 border border-1 shadow-sm mb-3 p-5">
            <p className="text-center">Reset Password</p>
            <form onSubmit={this.handleSubmit}>
              <div className="form-outline mb-4 text-start">
                <input
                  type="password"
                  id="newPassword"
                  name="newPassword"
                  value={this.state.newPassword}
                  onChange={this.onChange}
                  className="form-control form-control-lg"
                  required
                />
                <label className="form-label" htmlFor="newPassword">
                  New Password
                </label>
              </div>
              <div className="form-outline mb-4 text-start">
                <input
                  type="password"
                  id="confirmPassword"
                  name="confirmPassword"
                  value={this.state.confirmPassword}
                  onChange={this.onChange}
                  className="form-control form-control-lg"
                  required
                />
                <label className="form-label" htmlFor="confirmPassword">
                  Confirm Password
                </label>
              </div>
              <div className='text-center' id="resetMessage">
                {this.state.message && <span style={{ color: this.state.showError ? 'red' : 'green' }}>{this.state.message}</span>}
              </div>
              <div className="text-center">
                <button type="submit" className="btn btn-primary btn-lg btn-block">Reset Password</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }
}
