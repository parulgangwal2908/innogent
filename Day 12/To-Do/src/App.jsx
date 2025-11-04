import { useEffect, useState, useRef } from 'react'
import TodoForm from './components/TodoForm'
import TodoList from './components/TodoList'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [tasks, setTasks] = useState([]);
  const isInitialMount = useRef(true);

  useEffect(() => {
    const savedTasks = JSON.parse(localStorage.getItem("tasks")) || [];
    setTasks(savedTasks);
  }, []);

  useEffect(() => {
    if (isInitialMount.current) {
      isInitialMount.current = false;
      return; 
    }
    localStorage.setItem("tasks", JSON.stringify(tasks));
  }, [tasks]);

  const addTask = (text) => {
    const newTask = { id: Date.now(), text };
    setTasks([...tasks, newTask]);
  };

  const editTask = (id, newText) => {
    const updatedTasks = tasks.map((task) =>
      task.id === id ? { ...task, text: newText } : task
    );
    setTasks(updatedTasks);
  };

  const deleteTask = (id) => {
    const filteredTasks = tasks.filter((task) => task.id !== id);
    setTasks(filteredTasks);
  };

  return (
    <div className="App">
      <h1>To-Do List</h1>
      <TodoForm addTask={addTask} />
      <TodoList tasks={tasks} editTask={editTask} deleteTask={deleteTask} />
    </div>
  );
}

export default App;
