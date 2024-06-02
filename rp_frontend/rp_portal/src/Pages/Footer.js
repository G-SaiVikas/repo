import './Footer.css';
import React from 'react';

export default function Footer() {
    return (
        <div>

            <div className="d-flex flex-column h-100">
                <footer className="w-100 py-4 mt-auto">
                    <div className="container py-4">
                        <div className="row gy-4 gx-5">
                            <div className="col-lg-4 col-md-6">
                                <h3 className="h1 text-white">Recruitment Portal</h3    >
                                <p className="small text-muted">We are dedicated to bridging the gap between talented job seekers and dynamic employers.</p>
                                <p className="small text-muted mb-0">&copy; Copyrights. All rights reserved. <a className="text-primary" href="/">JobPortal.com</a></p>
                            </div>

                            <div className="col-lg-2 col-md-6">
                                <h5 className="text-white mb-3">Quick links</h5>
                                <ul className="list-unstyled text-muted">
                                    <li><a href="/">Home</a></li>
                                    <li><a href="/about">About Us</a></li>

                                </ul>
                            </div>

                        </div>
                    </div>
                </footer>
            </div>


        </div>
    )
}
