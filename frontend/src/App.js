import './App.css';
import ButtonAppBar from './components/Appbar'
import HomePage from './components/HomePage';
import AboutPage from './components/AboutPage';
import LoginPage from './components/LoginPage';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    
    <Router>
      
        <div className="App">
            <ButtonAppBar/>
            <Routes>
              <Route exact path="/" element={<HomePage />}  />
              <Route path="/about" element={<AboutPage />}  />
              <Route path="/login" element={<LoginPage />}  />
            </Routes>
            {/* Add more routes for other pages */}
        </div>
    </Router>

  );
}

export default App;
