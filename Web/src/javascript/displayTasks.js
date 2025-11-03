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
        tasks.forEach((task) => {
          tasks_table.innerHTML = `
          <div class="task">
        <div class="task-title">${task.name}</div>
        <span class="task-category">${task.category}</span>
        <div class="task-notes">${task.notes}</div>
      </div>`;
        });
      } else {
        tasks_table.innerText = "No tasks available.";
      }
    });
}

window.addEventListener("load", getFullName);
window.addEventListener("load", getTasks);
