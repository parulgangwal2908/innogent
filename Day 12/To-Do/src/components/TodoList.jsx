import TodoItem from "./TodoItem";

export default function TodoList({ tasks, editTask, deleteTask }) {
  return (
    <div className="task-list">
      {tasks.length === 0 ? (
        <p className="no-task">No tasks yet 😀!!</p>
      ) : (
        tasks.map((task) => (
          <div key={task.id}>
            <TodoItem 
              task={task}
              editTask={editTask}
              deleteTask={deleteTask}
            />
          </div>
        ))
      )}
    </div>
  );
}
