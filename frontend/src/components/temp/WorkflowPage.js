// WorkflowPage.js
import React from 'react';
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import Task from './Task'; // Assuming you have a Task component
import ButtonAppBar from '../Appbar';
import './WorkflowPage.css';

const WorkflowPage = () => {
  return (
    <>
    <DndProvider backend={HTML5Backend}>
    <div className="workflow-container">
        {/* Left side: Tasks */}
        
        <div className="tasks-area">
          <h2>Available Tasks</h2>
          <div className="tasks-grid">
          {/* Render the draggable tasks here */}
            <Task id="task1" name="Task 1" image="/path/to/task1.png" />
            <Task id="task1" name="Task 1" image="/path/to/task1.png" />
            {/* <Task id="task1" name="Task 1" image="/path/to/task1.png" />
            <Task id="task1" name="Task 2" image="/path/to/task1.png" />
            <Task id="task1" name="Task 3" image="/path/to/task1.png" />
            <Task id="task1" name="Task 3" image="/path/to/task1.png" /> */}
          {/* Add more tasks as needed */}
        </div>
        </div>
        
        {/* Right side: Empty space */}
        <div className="drop-zone">
          <h2>Drop Zone</h2>
          {/* Empty space where you can drop the tasks */}
          {/* Implement drop logic here */}
        </div>
      </div>
    </DndProvider>
    </>
    
  );
};

export default WorkflowPage;
