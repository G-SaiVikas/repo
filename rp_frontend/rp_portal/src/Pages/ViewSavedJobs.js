import React, { Component } from 'react';
import axios from 'axios';
import { Card, Button, Container, Row, Col } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class ViewSavedJobs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isloggedin: localStorage.getItem('jwtToken') !== null,
      userId: localStorage.getItem('userId'),
      jobs: []
    };

    this.applyToJob = this.applyToJob.bind(this);
  }

  componentDidMount() {
    if (this.state.isloggedin) {
      this.fetchAllJobs();
    }
  }

  fetchAllJobs() {
    const userId = this.state.userId;
    axios.get('http://localhost:8080/users/getsavedjobs', {
      params: { userId } // Correct parameter name
    })
    .then(response => {
      this.setState({ jobs: response.data });
    })
    .catch(error => {
      console.error("There was an error fetching the jobs!", error);
    });
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
        if (error.response.status === 409) {
          console.error('User has already applied for this job.');
          alert('You have already applied for this job.');
        } else {
          console.error('Error applying for job:', error.response.data);
          alert('An error occurred while applying for the job.');
        }
      } else if (error.request) {
        console.error('No response received:', error.request);
        alert('No response received from the server.');
      } else {
        console.error('Error', error.message);
        alert('An error occurred while applying for the job.');
      }
    });
  }

  render() {
    const { jobs } = this.state;

    return (
      <Container className="py-5">
        <h1 className="text-center mb-4">Saved Jobs</h1>
        <Row>
          {jobs && jobs.length > 0 ? (
            jobs.map(job => (
              <Col md={4} key={job.id} className="mb-4">
                <Card className="h-100">
                  <Card.Body>
                    <Card.Title>{job.jobTitle}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">{job.company}</Card.Subtitle>
                    <Card.Text>
                      {job.jobDescription}
                    </Card.Text>
                    <Card.Text>
                      <strong>Skills Required:</strong> {job.skillsRequired}
                    </Card.Text>
                    <Card.Text>
                      <strong>Location:</strong> {job.location}
                    </Card.Text>
                    <Card.Text>
                      <strong>Salary:</strong> ${job.salary}
                    </Card.Text>
                    <Button 
                      variant="primary" 
                      className="mt-auto"
                      onClick={() => this.applyToJob(job.id)} // Use the method
                    >
                      Apply
                    </Button>
                  </Card.Body>
                </Card>
              </Col>
            ))
          ) : (
            <p className="text-center">No jobs saved yet.</p>
          )}
        </Row>
      </Container>
    );
  }
}

export default ViewSavedJobs;
