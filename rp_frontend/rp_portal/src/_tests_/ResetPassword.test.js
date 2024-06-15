import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import ResetPassword from '../Pages/ResetPassword';

describe('ResetPassword Component', () => {
  beforeEach(() => {
    render(
      <MemoryRouter>
        <ResetPassword />
      </MemoryRouter>
    );
  });

  test('renders New Password input field', () => {
    const newPasswordInput = screen.getByPlaceholderText(/New Password/i);
    expect(newPasswordInput).toBeInTheDocument();
  });

  test('renders Confirm Password input field', () => {
    const confirmPasswordInput = screen.getByPlaceholderText(/Confirm Password/i);
    expect(confirmPasswordInput).toBeInTheDocument();
  });

  test('renders Reset Password button', () => {
    const resetButton = screen.getByRole('button', { name: /Reset Password/i });
    expect(resetButton).toBeInTheDocument();
  });
});