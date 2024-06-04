import React, { Component } from 'react';
import { Navigate } from 'react-router-dom';
import axios from 'axios';
import Swal from 'sweetalert2';
import Footer from './Footer';
import "./SignUp.css";

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            email: '',
            password: '',
            phoneNumber: '',
            check: false,
            isRegistered: false,
        };
        this.onChange = this.onChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    handleSubmit(e) {
        e.preventDefault();
        axios.post('http://localhost:8080/users/register', {
            fullName: this.state.name,
            email: this.state.email,
            password: this.state.password,
            phoneNumber: this.state.phoneNumber,
        }).then((res) => {
            if (res.status === 200) {
                Swal.fire({
                    icon: 'success',
                    title: 'Welcome to Recruitment Portal, Account Created Successfully',
                    text: 'Login to Continue',
                });
                this.setState({ isRegistered: true });
            }
        }).catch((err) => {
            if (err.response.status === 409) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops!',
                    text: 'User Already Exist! Try using new Email',
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops!',
                    text: 'Something Went Wrong!',
                });
            }
        });
    }

    render() {
        if (this.state.isRegistered) {
            return <Navigate to="/" />;
        }

        return (
            <>
                <header className="header">
                    <nav className="navbar navbar-expand-lg navbar-light py-3">
                        <div className="container">
                            <a href="#" className="navbar-brand">
                                <img src="https://bootstrapious.com/i/snippets/sn-registeration/logo.svg" alt="logo" width="150" />
                            </a>
                        </div>
                    </nav>
                </header>

                <div className="container">
                    <div className="row py-5 mt-4 align-items-center">
                        <div className="col-md-5 pr-lg-5 mb-5 mb-md-0">
                            <img src="https://bootstrapious.com/i/snippets/sn-registeration/illustration.svg" alt="" className="img-fluid mb-3 d-none d-md-block" />
                            <h1>Create an Account</h1>
                        </div>

                        <div className="col-md-7 col-lg-6 ml-auto">
                            <form onSubmit={this.handleSubmit}>
                                <div className="row">
                                    <div className="input-group col-lg-12 mb-4">
                                        <div className="input-group-prepend">
                                            <span className="input-group-text bg-white px-4 border-md border-right-0">
                                                <i className="fa fa-user text-muted"></i>
                                            </span>
                                        </div>
                                        <input id="name" type="text" name="name" placeholder="Full Name" className="form-control bg-white border-left-0 border-md"
                                            value={this.state.name}
                                            onChange={this.onChange}
                                            required
                                        />
                                    </div>

                                    <div className="input-group col-lg-12 mb-4">
                                        <div className="input-group-prepend">
                                            <span className="input-group-text bg-white px-4 border-md border-right-0">
                                                <i className="fa fa-envelope text-muted"></i>
                                            </span>
                                        </div>
                                        <input id="email" type="email" name="email" placeholder="Email Address" className="form-control bg-white border-left-0 border-md"
                                            value={this.state.email}
                                            onChange={this.onChange}
                                            required
                                        />
                                    </div>

                                    <div className="input-group col-lg-12 mb-4">
                                        <div className="input-group-prepend">
                                            <span className="input-group-text bg-white px-4 border-md border-right-0">
                                                <i className="fa fa-phone-square text-muted"></i>
                                            </span>
                                        </div>
                                        <input id="phoneNumber" type="tel" name="phoneNumber" placeholder="Phone Number" className="form-control bg-white border-md border-left-0 pl-3"
                                            value={this.state.phoneNumber}
                                            onChange={this.onChange} required />
                                    </div>

                                    <div className="input-group col-lg-6 mb-4">
                                        <div className="input-group-prepend">
                                            <span className="input-group-text bg-white px-4 border-md border-right-0">
                                                <i className="fa fa-lock text-muted"></i>
                                            </span>
                                        </div>
                                        <input id="password" type="password" name="password" placeholder="Password" className="form-control bg-white border-left-0 border-md"
                                            value={this.state.password}
                                            onChange={this.onChange} required />
                                    </div>

                                    <div className="input-group col-lg-6 mb-4">
                                        <div className="input-group-prepend">
                                            <span className="input-group-text bg-white px-4 border-md border-right-0">
                                                <i className="fa fa-lock text-muted"></i>
                                            </span>
                                        </div>
                                        <input id="passwordConfirmation" type="password" name="passwordConfirmation" placeholder="Confirm Password" className="form-control bg-white border-left-0 border-md" />
                                    </div>

                                    <div className="d-flex justify-content-center">
                                        <button type="submit" className="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
                                            Register
                                        </button>
                                    </div>

                                    <div className="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                                        <div className="border-bottom w-100 ml-5"></div>
                                        <span className="px-2 small text-muted font-weight-bold text-muted">OR</span>
                                        <div className="border-bottom w-100 mr-5"></div>
                                    </div>

                                    <div className="text-center w-100">
                                        <p className="text-muted font-weight-bold">Already Registered? <a href="/" className="text-primary ml-2">Login</a></p>
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
