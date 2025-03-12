const createProfileForm = document.getElementById("create-profile-form");

function createProfile(event) {
  event.preventDefault();

  const emailAddress = document.getElementById("email-address").value;
  const userName = document.getElementById("user-name").value;

  fetch(`http://localhost:8080/user/create`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: emailAddress,
      name: userName,
    }),
  })
    .then((response) => {
      if (!response.ok) {
        console.log(response.status);
      }
      console.log(response.status);
      // console.log(response.body);
      return response.json();
    })
    .then((data) => {
      const userName = data.user.name;
      
      console.log(data.user.name);
      window.location.href = "/Web/HTML/displayTasks.html";

    });
}

createProfileForm.addEventListener("submit", createProfile);
