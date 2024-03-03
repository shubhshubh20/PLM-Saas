// src/LoginPage.js
import React, { useState } from 'react';
import './LoginPage.css'; // Import CSS for login page styling

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState({});

    const handleSubmit = (e) => {
        e.preventDefault();
        // Perform form validation
        const errors = {};
        if (!username.trim()) {
            errors.username = 'Username is required';
        }
        if (!password.trim()) {
            errors.password = 'Password is required';
        }
        setErrors(errors);

        // If no errors, proceed with login
        if (Object.keys(errors).length === 0) {
            // Logic for handling login
            console.log('Logging in...');
        }
    };

    return (
        <div className="login-container">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    {errors.username && <span className="error">{errors.username}</span>}
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    {errors.password && <span className="error">{errors.password}</span>}
                </div>
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default LoginPage;