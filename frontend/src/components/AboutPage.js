import React from 'react';
import ButtonAppBar from './Appbar';

const AboutPage = () => {
    return (
        <>
        <ButtonAppBar/>
        <div className="container">
            <h1>About Us</h1>
            <p>This is the About page for our application.</p>
            <p>Insert relevant information about your application or organization here.</p>
        </div>
        </>
    );
};

export default AboutPage;