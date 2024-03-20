// Task.js
import React from 'react';
import { useDrag } from 'react-dnd';
import "./Task.css";

const Task = ({ id, name, image }) => {
  const [{ isDragging }, drag] = useDrag({
    type: 'TASK', // Define the type property
    item: { id, name, image},
    collect: monitor => ({
      isDragging: monitor.isDragging(),
    }),
  });

  return (
    <div
      className="task"
      ref={drag}
      style={{ opacity: isDragging ? 0.5 : 1 }}
    >
      <img className="task-image" src={image} />
      <div className="task-name">{name}</div>
    </div>
  );
};

export default Task;
