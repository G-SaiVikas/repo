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
      jobs: [],
      searchSkill: '',
      filteredJobs: []
    };

    this.logout = this.logout.bind(this);
    this.handleSearchChange = this.handleSearchChange.bind(this);
    this.searchJobs = this.searchJobs.bind(this);
  }

  componentDidMount() {
    if (this.state.isloggedin) {
      this.fetchAllJobs();
    }
  }

  fetchAllJobs() {
    axios.get('http://localhost:8080/jobs/getjobs')
      .then(response => {
        this.setState({ jobs: response.data, filteredJobs: response.data });
      })
      .catch(error => {
        console.error("There was an error fetching the jobs!", error);
      });
  }

  logout() {
    localStorage.clear();
    this.setState({ isloggedin: false });
  }

  handleSearchChange(event) {
    this.setState({ searchSkill: event.target.value });
  }

  searchJobs() {
    if (this.state.searchSkill.trim() === '') {
      this.setState({ filteredJobs: this.state.jobs });
    } else {
      axios.get(`http://localhost:8080/jobs/searchJob`, { params: { skill: this.state.searchSkill } })
        .then(response => {
          this.setState({ filteredJobs: response.data });
        })
        .catch(error => {
          console.error("There was an error searching for jobs!", error);
        });
    }
  }

  applyToJob(jobId) {
    const userId = this.state.userId;
    const params = new URLSearchParams({
      id: jobId,
      user_id: userId
    }).toString();

    axios.post(`http://localhost:8080/users/jobs/apply`, null, {
      params: {
        id: jobId,
        user_id: userId
      }
    })
    .then(response => {
        console.log('Job applied successfully:', response.data);
        alert('Job applied successfully');
    })
    .catch(error => {
        if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            if (error.response.status === 409) {
                console.error('User has already applied for this job.');
                alert('You have already applied for this job.');
            } else {
                console.error('Error applying for job:', error.response.data);
                alert('An error occurred while applying for the job.');
            }
        } else if (error.request) {
            // The request was made but no response was received
            console.error('No response received:', error.request);
            alert('No response received from the server.');
        } else {
            // Something happened in setting up the request that triggered an Error
            console.error('Error', error.message);
            alert('An error occurred while applying for the job.');
        }
    });
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

        <div className="mb-4">
          <input
            type="text"
            className="form-control"
            placeholder="Search by skill"
            value={this.state.searchSkill}
            onChange={this.handleSearchChange}
          />
          <button onClick={this.searchJobs} className="btn btn-primary mt-2">Search</button>
        </div>

        <div className="row">
          {this.state.filteredJobs.map(job => (
            <div className="col-md-4"  style={cardStyle} key={job.id}>
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
