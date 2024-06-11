import React, { Component } from 'react';
import axios from 'axios';
import { Card, Button, Container, Row, Col } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class ViewAppliedJobs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isloggedin: localStorage.getItem('jwtToken') !== null,
      userId: localStorage.getItem('userId'),
      jobs: []
    };
  }

  componentDidMount() {
    if (this.state.isloggedin) {
      this.fetchAllJobs();
    }
  }

  fetchAllJobs() {
    const userId = this.state.userId;
    axios.get('http://localhost:8080/users/getappliedjobs', {
      params: { user_id: userId} // Assuming 'userId' is the parameter name expected by the backend
    })
    .then(response => {
      this.setState({ jobs: response.data });
    })
    .catch(error => {
      console.error("There was an error fetching the jobs!", error);
    });
  }
  StatusOfJob()
  {
    alert('Your job is under review');
  }

  render() {
    const { jobs } = this.state;

    return (
        <Container className="py-5">
        <h1 className="text-center mb-4">Applied Jobs</h1>
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
                    <Button variant="primary" className="mt-auto"
                    onClick={() => this.StatusOfJob()}>View Status</Button>
                  </Card.Body>
                </Card>
              </Col>
            ))
          ) : (
            <p className="text-center">No jobs applied for yet.</p>
          )}
        </Row>
      </Container>
    );
  }
}

export default ViewAppliedJobs;
