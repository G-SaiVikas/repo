import React from 'react';
import { render, screen } from '@testing-library/react';
import SignUp from '../Pages/SignUp'; 

describe('SignUp Component', () => {
  beforeEach(() => {
    render(<SignUp />);
  });

  test('renders Full Name input field', () => {
    const nameInput = screen.getByPlaceholderText(/full name/i);
    expect(nameInput).toBeInTheDocument();
  });

  test('renders Email Address input field', () => {
    const emailInput = screen.getByPlaceholderText(/email address/i);
    expect(emailInput).toBeInTheDocument();
  });

  test('renders Phone Number input field', () => {
    const phoneInput = screen.getByPlaceholderText(/phone number/i);
    expect(phoneInput).toBeInTheDocument();
  });

  test('renders Password input field', () => {
    const passwordInputs = screen.getAllByPlaceholderText(/password/i);
    expect(passwordInputs[0]).toBeInTheDocument();
    expect(passwordInputs[1]).toBeInTheDocument();
  });

  test('renders Confirm Password input field', () => {
    const confirmPasswordInput = screen.getByPlaceholderText(/confirm password/i);
    expect(confirmPasswordInput).toBeInTheDocument();
  });

  test('renders Register button', () => {
    const submitButton = screen.getByRole('button', { name: /register/i });
    expect(submitButton).toBeInTheDocument();
  });
});

