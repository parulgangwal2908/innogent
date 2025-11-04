import { useState } from "react";
import { MdFormatListBulletedAdd } from "react-icons/md";

 export default function TodoForm({addTask}){
    const [text,setText]=useState("")
    const handleSubmit=(e)=>{
        e.preventDefault();
        if(text.trim()){
            addTask(text);
            setText("")
        }
    }
    
    return(
        <form onSubmit={handleSubmit}>
            <div className="input-text">
            <MdFormatListBulletedAdd className="add-logo"/>
            <input 
            type="text" 
            value={text}    
            onChange={(e)=>setText(e.target.value)}
            placeholder="Enter a new task"
            className="input-body"
            />
            </div>

            <div>
            <button  className="btn-text" type="submit">Add Task</button>
            </div>
        </form>
    )

 }