// MessageDisplay.js
import React from 'react';


const MessageDisplay = ({ messages }) => {
    return (
        <div>
            {messages.map((message, index) => (
                <div key={index}>{message}</div>
            ))}
        </div>
    );
};

export default MessageDisplay;
