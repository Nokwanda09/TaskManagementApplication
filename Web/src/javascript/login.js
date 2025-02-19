const loginForm = document.getElementById("login-form");
const cardBody = document.getElementById("card-body");

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
        // window.location.href = "/Web/HTML/createProfile.html";
        changeCardContent();
        addCreateProfileBtn();
      }
    });
}

function changeCardContent() {
  loginForm.style.display = "none";
  const promptParagraph = document.createElement("p");
  promptParagraph.innerText =
    "You currently do not have a profile.\nPlease click the button to create Account.";
  // console.log(cardBody);
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
