const loginForm = document.getElementById("login-form");
const cardBody = document.getElementById("card-body");

function loginUser(event) {
  event.preventDefault();

  const userName = document.getElementById("username").value;
  const passWord = document.getElementById("password").value;
  console.log(userName + " " + passWord);

  fetch(`http://localhost:8080/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username: userName, password: passWord }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.token) {
        sessionStorage.setItem("userToken", data.token);
        sessionStorage.setItem("fullName", data.fullName);
        window.location.href = "/Web/HTML/displayTasks.html";
      } else {
        changeCardContent();
        addCreateProfileBtn();
      }
    });
}

function changeCardContent() {
  loginForm.style.display = "none";
  const promptParagraph = document.createElement("p");
  promptParagraph.innerText =
    "You currently do not have a profile.\nPlease click the button below to create account.";
  cardBody.appendChild(promptParagraph);
  promptParagraph.style.display = "block";
  promptParagraph.style.textAlign = "center";
}

function addCreateProfileBtn() {
  const createProfileBtn = document.createElement("div");
  createProfileBtn.classList.add("enter-btn");
  createProfileBtn.innerHTML = ` <button type="submit">Create Profile</button>`;

  cardBody.appendChild(createProfileBtn);
  createProfileBtn.style.display = "flex";
  createProfileBtn.style.justifyContent = "center";

  createProfileBtn.addEventListener("click", () => {
    window.location.href = "/Web/HTML/createProfile.html";
  });
}

loginForm.addEventListener("submit", loginUser);
