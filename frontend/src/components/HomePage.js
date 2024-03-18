import React, {useState} from 'react';
import MessageInput from './MessageInput';
import MessageDisplay from './MessageDisplay';

const HomePage = () => {
    const [messages, setMessages] = useState([]);

    const handleSendMessage = (message) => {
        setMessages([...messages, message]);
        // Here you might send the message to a server or handle any other logic
    };
    return (
        <>
        <div className="container">
            <MessageDisplay messages={messages} />
            <MessageInput onSendMessage={handleSendMessage} />
        </div>
        </>
    );
};

export default HomePage;