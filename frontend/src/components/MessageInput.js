// MessageInput.js
import React, { useState } from 'react';
import axios from 'axios';

const MessageInput = ({ onSendMessage }) => {
    const [message, setMessage] = useState('');
    const [response, setResponse] = useState('');

    const handleMessageChange = (e) => {
        setMessage(e.target.value);
    };

    // const handleSendMessage = () => {
    //     onSendMessage(message);
    //     setMessage('');
    // };

    const handleSendMessage = async () => {
        try {
            const response = await axios.post('http://localhost:8081/api/messages/send', { message });
            setResponse(response.data);
        } catch (error) {
            console.error('Error sending message:', error);
        }
    };

    return (
        <div>
            <input type="text" value={message} onChange={handleMessageChange} />
            <button onClick={handleSendMessage}>Send</button>
            <div>Response: {response}</div>
        </div>
    );
};

export default MessageInput;
