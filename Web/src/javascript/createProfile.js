const createProfileForm = document.getElementById("create-profile-form");

function createProfile(event) {
  event.preventDefault();

  const full_name = document.getElementById("full-name").value;
  const user_name = document.getElementById("user-name").value;
  const pass_word = document.getElementById("password").value;

  fetch(`http://localhost:8080/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      username: full_name,
      fullName: user_name,
      password: pass_word,
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
      // const full_name = data.fullName;
      // const
      // sessionStorage.setItem("fullName", data.fullName);
      // console.log(data.user.name);
      window.location.href = "/Web/HTML/login.html";
    });
}

createProfileForm.addEventListener("submit", createProfile);
