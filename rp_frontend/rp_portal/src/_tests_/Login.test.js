import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import Login from '../Pages/Login'; 
describe('Login Component', () => {
  beforeEach(() => {
    render(
      <MemoryRouter>
        <Login />
      </MemoryRouter>
    );
  });

  test('renders Email Address input field', () => {
    const emailInput = screen.getByPlaceholderText(/Email Address/i);
    expect(emailInput).toBeInTheDocument();
  });

  test('renders Password input field', () => {
    const passwordInputs = screen.getByPlaceholderText(/Password/i);
    expect(passwordInputs).toBeInTheDocument();
  });

  test('renders Register button', () => {
    const loginButton = screen.getByRole('button', { name: /sign in/i });
    expect(loginButton).toBeInTheDocument();
  });

  test('renders Forgot Password? link', () => {
    const forgotPasswordLink = screen.getByText('Forgot Password?');
    expect(forgotPasswordLink).toBeInTheDocument();
  })
});