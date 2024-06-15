import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import ForgotPassword from '../Pages/ForgotPassword';

describe('ForgotPassword Component', () => {
  beforeEach(() => {
    render(
      <MemoryRouter>
        <ForgotPassword />
      </MemoryRouter>
    );
  });

  test('renders Full Name input field', () => {
    const fullNameInput = screen.getByPlaceholderText(/Full Name/i);
    expect(fullNameInput).toBeInTheDocument();
  });

  test('renders Phone Number input field', () => {
    const phoneNumberInput = screen.getByPlaceholderText(/Phone Number/i);
    expect(phoneNumberInput).toBeInTheDocument();
  });

  test('renders Send Reset Link button', () => {
    const resetButton = screen.getByRole('button', { name: /Send Reset Link/i });
    expect(resetButton).toBeInTheDocument();
  });

  test('renders Back to Login link', () => {
    const loginLink = screen.getByText('Back to Login');
    expect(loginLink).toBeInTheDocument();
  })
});