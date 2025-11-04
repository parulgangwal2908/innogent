import { useState } from "react";
import React from "react";
import { BiSolidEditAlt, BiTrash, BiSave } from "react-icons/bi";

export default function TodoItem({ task, editTask, deleteTask }) {
  const [isEditing, setIsEditing] = useState(false);
  const [newText, setNewText] = useState(task.text);
  const [isDeleted, setIsDeleted] = useState(false); // 👈 new state

  const handleEdit = () => {
    if (isEditing && newText.trim()) {
      editTask(task.id, newText);
    }
    setIsEditing(!isEditing);
  };

  const handleDelete = () => {
    setIsDeleted(true); // 👈 just mark as deleted (don’t remove yet)
  };

  return (
<div className={`task-row ${isDeleted ? "deleted" : ""} ${isEditing ? "editing" : ""}`}>
      {isEditing ? (
        <input
          type="text"
          value={newText}
          onChange={(e) => setNewText(e.target.value)}
        />
      ) : (
        <span className="task-text">{task.text}</span>
      )}

      <div className="task-actions">
        <button className="btn-edit" onClick={handleEdit}>
          {isEditing ? <BiSave className="save" /> : <BiSolidEditAlt className="edit" />}
        </button>
        <button className="btn-delete" onClick={handleDelete}>
          <BiTrash className="delete" />
        </button>
      </div>
    </div>
  );
}
