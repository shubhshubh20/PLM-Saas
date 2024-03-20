import './App.css';
import HomePage from './components/HomePage';
import AboutPage from './components/AboutPage';
import LoginPage from './components/LoginPage';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AILandingPage from './components/AILandingPage';
import WorkflowPage from './components/temp/WorkflowPage';

function App() {
  return (
    
    <Router>
      
        <div className="App">
            {/* <ButtonAppBar/> */}
            <Routes>
              <Route exact path="/" element={<LoginPage />}  />
              <Route path="/about" element={<AboutPage />}  />
              <Route path="/home" element={<HomePage />}  />
              <Route path="/ai-assistant" element={<AILandingPage/>} />
              <Route path="/workflow" element={<WorkflowPage/>} />
            </Routes>
            {/* Add more routes for other pages */}
        </div>
    </Router>

  );
}

export default App;
