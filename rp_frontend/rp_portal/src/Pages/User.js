import React, { Component } from 'react';
import { Navigate } from 'react-router-dom';

export default class UserPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isloggedin: localStorage.getItem('jwtToken') !== null,
      userId: localStorage.getItem('userId')
    };

    this.logout = this.logout.bind(this);
  }

  logout() {
    localStorage.clear();
    this.setState({ isloggedin: false });
  }

  render() {
    if (!this.state.isloggedin) {
      return <Navigate to="/login" />;
    }

    return (
      <div className="container py-5 text-center">
        <h1>Hi, {this.state.userId}</h1>
        <button onClick={this.logout} className="btn btn-danger mt-3">Logout</button>
      </div>
    );
  }
}
