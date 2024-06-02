import React from 'react';

import './About.css';
import Footer from './Footer';

export default function About() {
  return (
    <>
      <div>
        <div>
        <div class="bg-light">
        <div class="container py-5">
          <div class="row h-100 align-items-center py-5">
            <div class="col-lg-6">
              <h1 class="display-4">Welcome to Recruitment Portal!</h1>
              <p class="lead text-muted mb-0">At [Your Recruitment Portal Name], we are dedicated to bridging the gap between talented job seekers and dynamic employers. Our platform is designed with the mission to simplify the job search and recruitment process, ensuring that the right candidates find the right opportunities.</p>
              </div>
            </div>
            <div class="col-lg-6 d-none d-lg-block"></div>
          </div>
        </div>
      </div>
      
      <div class="bg-white py-5">
        <div class="container py-5">
          <div class="row align-items-center mb-5">
            <div class="col-lg-6 order-2 order-lg-1"><i class="fa fa-bar-chart fa-2x mb-3 text-primary"></i>
              <h2 class="font-weight-light">Our Mission</h2>
              <p class="font-italic text-muted mb-4">Our mission is to empower job seekers by providing them with a user-friendly platform where they can find, apply for, and track job opportunities that align with their career aspirations. We strive to create a seamless experience for employers to post job listings, review applications, and find the best talent to drive their businesses forward.</p>
            </div>
            <div class="col-lg-5 px-5 mx-auto order-1 order-lg-2"><img src="https://lh5.googleusercontent.com/YyTeGA9eUqN8DzgeWaw5gteUI6jThm4CKunleMfuRFoO3s_JSfmd00YUdOTZGNCqOY7YSGAop_mutx9rlJ0XLYQUFtgIK8PvJ8HSH1rRmym1pxkfR6CVc6lWoH-wKSbSIwt5yLLL" alt="" class="img-fluid mb-4 mb-lg-0"/></div>
          </div>
          <div class="row align-items-center">
            <div class="col-lg-5 px-5 mx-auto"><img src="https://pbs.twimg.com/media/DtLfZEjWoAE8gfB.jpg" alt="" class="img-fluid mb-4 mb-lg-0"/></div>
            <div class="col-lg-6"><i class="fa fa-leaf fa-2x mb-3 text-primary"></i>
              <h2 class="font-weight-light">24 x 7 Customer Support</h2>
              <p class="font-italic text-muted mb-4">Our support representatives interact with customers on a variety of channels such as phone, email, and social media, and ensure that all valid customer concerns are being dealt with immediately.
              </p>
            </div>
          </div>
        </div>
      </div>
      
      <div class="bg-light py-5">
        <div class="container py-5">
          <div class="row mb-4">
            <div class="col-lg-5">
              <h2 class="display-4 font-weight-light">Our team</h2>
            </div>
          </div>
      
          <div class="row text-center">
            <div class="col-xl-3 col-sm-6 mb-5">
              <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-3.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm"/>
                <h5 class="mb-0">Sai Teja Dugyala</h5>
                
              </div>
            </div>
      
            <div class="col-xl-3 col-sm-6 mb-5">
              <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-1.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm"/>
                <h5 class="mb-0">Rohit Lanka</h5>
                
              </div>
            </div>

            <div class="col-xl-3 col-sm-6 mb-5">
              <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://img.lovepik.com/element/40143/1122.png_1200.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm"/>
                <h5 class="mb-0">Jyothi Konepudi</h5>
                
              </div>
            </div>
      
            <div class="col-xl-3 col-sm-6 mb-5">
              <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-2.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm"/>
                <h5 class="mb-0">Gumpena Sai Vikas</h5>
                
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Footer/>
    </>
  );
}
