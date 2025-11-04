const add_task_form = document.getElementById("new-task-form");

function addTask(event) {
  event.preventDefault();

  let task_name = document.getElementById("task-name").value;
  let task_category = document.getElementById("task-category").value;
  let task_notes = document.getElementById("task-notes").value;

  console.log(sessionStorage.getItem("userToken"));

  console.log(task_name + " " + task_category + " " + task_notes);
  fetch(`http://localhost:8080/add`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${sessionStorage.getItem("userToken")}`,
      // Credentials: include,
    },
    body: JSON.stringify({
      name: task_name,
      notes: task_notes,
      category: task_category,
    }),
  }).then((response) => {
    if (response.ok) {
      alert("Task added!");
    } else {
      alert("Something went wrong, add task again");
    }
  });
}

add_task_form.addEventListener("submit", addTask);
