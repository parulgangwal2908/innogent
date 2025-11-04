import { useEffect, useState } from 'react'
import TodoForm from './components/TodoForm'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';

import TodoList from './components/TodoList'

function App() {
  const[tasks,setTask]=useState([])

  useEffect(()=>{
    const savedTasks=JSON.parse(localStorage.getItem("tasks"))||[]
    setTask(savedTasks)

  },[])
  
  useEffect(()=>{
    localStorage.setItem("tasks",JSON.stringify(tasks))
  },[tasks])

  const addTask=(text)=>{
    const newTask={id:Date.now(),text}
    setTask([...tasks,newTask])
  }
  const editTask=(id,newText)=>{
    const updatedTasks=tasks.map((task)=>
      task.id===id ? {...task,text:newText} : task
    )
    setTask(updatedTasks)

  }
  const deleteTask=(id)=>{
    const filteredTasks=tasks.filter((task)=>task.id!==id)
    setTask(filteredTasks)
  }
  
  return (
    <div className="App">
      <h1>To-Do List</h1>
      <TodoForm addTask={addTask}/>
      <TodoList tasks={tasks} editTask={editTask} deleteTask={deleteTask}/>
    </div>
  ) 


}

export default App
