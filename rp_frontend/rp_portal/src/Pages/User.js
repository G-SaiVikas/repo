import React, { Component } from 'react';
import { Navigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

export default class UserPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isloggedin: localStorage.getItem('jwtToken') !== null,
      userId: localStorage.getItem('userId'),
      jobs: []
    };

    this.logout = this.logout.bind(this);
  }

  componentDidMount() {
    if (this.state.isloggedin) {
      axios.get('http://localhost:8080/jobs/getjobs')
        .then(response => {
          this.setState({ jobs: response.data });
        })
        .catch(error => {
          console.error("There was an error fetching the jobs!", error);
        });
    }
  }

  logout() {
    localStorage.clear();
    this.setState({ isloggedin: false });
  }

  applyToJob(jobId) {
    // Handle the apply to job action here, e.g., send a request to the backend
    console.log(`Applying to job ID: ${jobId}`);
  }

  render() {
    if (!this.state.isloggedin) {
      return <Navigate to="/login" />;
    }
    const cardStyle = {
      paddingTop: '20px',
      paddingBottom: '20px'
    };


    return (
      <div className="container py-5">
        <h1 className="text-center">Hi, {this.state.userId}</h1>
        <button onClick={this.logout} className="btn btn-danger mt-3 mb-5">Logout</button>

        <div className="row">
          {this.state.jobs.map(job => (
            <div className="col-md-4 " style={cardStyle} key={job.id}>
              <div className="card mb-4 shadow-sm h-100 bg-light">
                <div className="card-body d-flex flex-column">
                  <h5 className="card-title">{job.jobTitle}</h5>
                  <h6 className="card-subtitle mb-2 text-muted">{job.company}</h6>
                  <p className="card-text">{job.jobDescription}</p>
                  <p className="card-text"><strong>Skills Required:</strong> {job.skillsRequired}</p>
                  <p className="card-text"><strong>Location:</strong> {job.location}</p>
                  <p className="card-text"><strong>Salary:</strong> ${job.salary}</p>
                  <button 
                    className="btn btn-primary mt-auto"
                    onClick={() => this.applyToJob(job.id)}
                  >
                    Apply
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}
