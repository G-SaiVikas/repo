import React, { Component } from 'react';
import axios from 'axios';
import { Container, Row, Col, Image, Card, Form, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class UserProfile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      profile: null,
      editMode: false,
      formData: {
        fullName: '',
        phoneNumber: '',
        email: '',
        profilePic: null,
        resume: null,
        dateOfBirth: ''
      },
      profilePicUrl: '',
      resumeUrl: ''
    };
  }

  componentDidMount() {
    this.fetchUserProfile();
  }

  fetchUserProfile = () => {
    const userId = localStorage.getItem('userId');
    axios.get(`http://localhost:8080/users/viewProfile`, { params: { id: userId } })
      .then(response => {
        const profileData = response.data;
        this.setState({
          profile: profileData,
          formData: {
            fullName: profileData.fullName,
            phoneNumber: profileData.phoneNumber,
            email: profileData.email,
            dateOfBirth: profileData.dateOfBirth
          },
          profilePicUrl: `http://localhost:8080/users/viewProfilePic?id=${profileData.id}`,
          resumeUrl: `http://localhost:8080/users/viewResume?id=${profileData.id}`
        });
      })
      .catch(error => {
        console.error('There was an error fetching the user profile!', error);
      });
  }

  handleInputChange = (e) => {
    const { name, value } = e.target;
    this.setState(prevState => ({
      formData: {
        ...prevState.formData,
        [name]: value
      }
    }));
  };

  handleFileChange = (e) => {
    const { name, files } = e.target;
    this.setState(prevState => ({
      formData: {
        ...prevState.formData,
        [name]: files[0]
      }
    }));
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const userId = localStorage.getItem('userId');
    const formDataToSend = new FormData();
    formDataToSend.append('id', userId);
    formDataToSend.append('fullName', this.state.formData.fullName);
    formDataToSend.append('phoneNumber', this.state.formData.phoneNumber);
    formDataToSend.append('email', this.state.formData.email);
    if (this.state.formData.profilePic) {
      formDataToSend.append('profilePic', this.state.formData.profilePic);
    }
    if (this.state.formData.resume) {
      formDataToSend.append('resume', this.state.formData.resume);
    }
    formDataToSend.append('dateOfBirth', this.state.formData.dateOfBirth);

    axios.put(`http://localhost:8080/users/updateProfile`, formDataToSend)
      .then(response => {
        this.setState({
          profile: response.data,
          editMode: false,
          profilePicUrl: `http://localhost:8080/users/viewProfilePic?id=${response.data.id}`,
          resumeUrl: `http://localhost:8080/users/viewResume?id=${response.data.id}`
        });
      })
      .catch(error => {
        console.error('There was an error updating the user profile!', error);
      });
  };

  renderProfileCard = () => {
    const { profile, resumeUrl } = this.state;
    return (
      <Card className="shadow-sm">
        <Card.Body>
          <div className="d-flex flex-column align-items-center">
            <Image
              src={this.state.profilePicUrl || "https://via.placeholder.com/150"}
              roundedCircle
              fluid
              style={{ height: '150px', width: '150px', objectFit: 'cover' }}
              className="mb-3"
            />
            <Card.Title className="text-center">{profile.fullName}</Card.Title>
            <Card.Subtitle className="mb-2 text-muted text-center">{profile.userName}</Card.Subtitle>
            <Card.Text className="text-center">
              <strong>Email:</strong> {profile.email}<br />
              <strong>Phone Number:</strong> {profile.phoneNumber}<br />
              {profile.dateOfBirth && <><strong>Date of Birth:</strong> {profile.dateOfBirth}<br /></>}
            </Card.Text>
            {resumeUrl && (
              <Card.Text className="text-center">
                <strong>Resume:</strong> <a href={resumeUrl} target="_blank" rel="noopener noreferrer">Download</a>
              </Card.Text>
            )}
            <Button variant="primary" onClick={() => this.setState({ editMode: true })}>Edit Profile</Button>
          </div>
        </Card.Body>
      </Card>
    );
  };

  renderEditForm = () => {
    const { formData } = this.state;
    return (
      <Form onSubmit={this.handleSubmit} className="shadow-sm p-4 rounded bg-light">
        <Form.Group controlId="formFullName">
          <Form.Label>Full Name</Form.Label>
          <Form.Control
            type="text"
            name="fullName"
            value={formData.fullName}
            onChange={this.handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formPhoneNumber">
          <Form.Label>Phone Number</Form.Label>
          <Form.Control
            type="text"
            name="phoneNumber"
            value={formData.phoneNumber}
            onChange={this.handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formEmail">
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            name="email"
            value={formData.email}
            onChange={this.handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formProfilePic">
          <Form.Label>Profile Picture</Form.Label>
          <Form.Control
            type="file"
            name="profilePic"
            onChange={this.handleFileChange}
          />
        </Form.Group>

        <Form.Group controlId="formResume">
          <Form.Label>Resume</Form.Label>
          <Form.Control
            type="file"
            name="resume"
            onChange={this.handleFileChange}
          />
        </Form.Group>

        <Form.Group controlId="formDateOfBirth">
          <Form.Label>Date of Birth</Form.Label>
          <Form.Control
            type="date"
            name="dateOfBirth"
            value={formData.dateOfBirth}
            onChange={this.handleInputChange}
          />
        </Form.Group>

        <Button variant="primary" type="submit" className="mr-2">Save</Button>
        <Button variant="secondary" onClick={() => this.setState({ editMode: false })}>Cancel</Button>
      </Form>
    );
  };

  render() {
    const { profile, editMode } = this.state;

    if (!profile) {
      return <div>Loading...</div>;
    }

    return (
      <Container className="py-5">
        <Row className="justify-content-center">
          <Col md={8}>
            {editMode ? this.renderEditForm() : this.renderProfileCard()}
          </Col>
        </Row>
      </Container>
    );
  }
}

export default UserProfile;
