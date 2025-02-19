const loginForm = document.getElementById("login-form");


function loginUser(event) {
  event.preventDefault();

  const emailAddress = document.getElementById("email-address").value;
  console.log(emailAddress);

  fetch(`http://localhost:8080/user/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email: emailAddress }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.exists) {
        window.location.href = "/Web/HTML/displayTasks.html";
      } else {
        window.location.href = "/Web/HTML/createProfile.html";
      }
    });
}


loginForm.addEventListener("submit", loginUser);