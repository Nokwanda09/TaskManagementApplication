function getFullName() {
  console.log(sessionStorage.getItem("userToken"));
  console.log(sessionStorage.getItem("fullName"));
  const full_name = document.getElementById("full-name");

  if (sessionStorage.getItem("fullName")) {
    full_name.textContent = sessionStorage.getItem("fullName") + "!";
  } else {
    full_name.textContent = "there!";
  }
}

function getTasks() {
  let tasks_table = document.getElementById("tasks-table");

  fetch(`http://localhost:8080/tasks`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${sessionStorage.getItem("userToken")}`,
      Credentials: "include",
    },
  })
    .then((response) => response.json())
    .then((tasks) => {
      if (tasks) {
        console.log(tasks);
        tasks.forEach((task) => {
          const task_row = document.createElement("div");
          task_row.classList.add("task");

          const task_title = document.createElement("div");
          task_title.classList.add("task-title");

          const task_category = document.createElement("span");
          task_category.classList.add("task-category");

          const task_notes = document.createElement("div");
          task_notes.classList.add("task-notes");

          tasks_table.appendChild(task_row);
          task_row.appendChild(task_title);
          task_row.appendChild(task_category);
          task_row.appendChild(task_notes);

          task_title.textContent = task.name;
          task_category.textContent = task.category;
          task_notes.textContent = task.notes;
        });
      } else {
        tasks_table.innerText = "No tasks available.";
      }
    });
}

window.addEventListener("load", getFullName);
window.addEventListener("load", getTasks);
