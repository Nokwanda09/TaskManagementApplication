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

window.addEventListener("load", getFullName);
