import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import axios from 'axios';
import AxiosMockAdapter from 'axios-mock-adapter';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import UserPage from '../Pages/User';

const LoginPage = () => <div>Signin</div>;

const mockAxios = new AxiosMockAdapter(axios);

describe('UserPage Component', () => {
  beforeEach(() => {
    localStorage.setItem('jwtToken', 'fakeToken');
    localStorage.setItem('userId', '123');
    mockAxios.onGet('http://localhost:8080/jobs/getjobs').reply(200, [
      {
        id: 1,
        jobTitle: 'Software Developer',
        company: 'ABC Corp',
        jobDescription: 'Develop software solutions',
        skillsRequired: 'React, Node.js',
        location: 'Remote',
        salary: '100000'
      }
    ]);
  });

  afterEach(() => {
    localStorage.clear();
    mockAxios.reset();
  });

  test('renders search input field', () => {
    render(
      <MemoryRouter>
        <UserPage />
      </MemoryRouter>
    );

    const searchInput = screen.getByPlaceholderText('Search by skill');
    expect(searchInput).toBeInTheDocument();
  });

  test('renders search button', () => {
    render(
      <MemoryRouter>
        <UserPage />
      </MemoryRouter>
    );

    const searchButton = screen.getByText('Search');
    expect(searchButton).toBeInTheDocument();
  });

  test('renders apply button for each job', async () => {
    render(
      <MemoryRouter>
        <UserPage />
      </MemoryRouter>
    );

    const applyButton = await screen.findByText('Apply');
    expect(applyButton).toBeInTheDocument();
  });

  test('logs out and redirects to login page', () => {
    render(
      <MemoryRouter initialEntries={['/user']}>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/user" element={<UserPage />} />
        </Routes>
      </MemoryRouter>
    );

    const logoutButton = screen.getByText('Logout');
    fireEvent.click(logoutButton);

    expect(localStorage.getItem('jwtToken')).toBeNull();
    expect(screen.getByText('Signin')).toBeInTheDocument();
  });
});
