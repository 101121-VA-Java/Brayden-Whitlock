// document.getElementById("submitButton").addEventListener("click", login);

let api1 = "http://localhost:8080/auth";

function login() {
  // resetting error div
  document.getElementById("error-div").innerHTML = "";

  //retrieving user credentials
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  let xhr = new XMLHttpRequest();

  xhr.open("POST", "http://localhost:8080/auth");
  // xhr.open("POST", `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let authToken = xhr.getResponseHeader("Authorization");

      /*
              storing authtoken in the session storage to be retrieved in different views
                - an item of key "token" and value authToken (Authorization token passed back from Javalin) is stored in the sessionStorage
             */
      sessionStorage.setItem("token", authToken);

      // navigate to a different view (ie: homepage)
      // window.location.href="../htmlFiles/login.html";
      let x = sessionStorage.token;
      if (x.split(":")[1] === "1") {
        window.location.href = "employeeDash.html";
        console.log("You are in as an employee!!!");
      } else if (x.split(":")[1] === "2") {
        window.location.href = "managerDash.html";
        console.log("You are in as a manager!!!");
      } else {
        document.getElementById("error-div").innerHTML = "Unable to login.";
      }
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML = "Unable to login.";
    }
  };

  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  let requestBody = `username=${username}&password=${password}`;

  xhr.send(requestBody);
}

function register() {
  // resetting error div
  document.getElementById("error-div").innerHTML = "";

  //retrieving user credentials
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let email = document.getElementById("email").value;

  let newEmployee = { firstName, lastName, username, password, email };

  //add other stuff

  let xhr = new XMLHttpRequest();

  xhr.open("POST", "http://localhost:8080/users");
  // xhr.open("POST", `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300)) {
      let authToken = xhr.getResponseHeader("Authorization");

      /*
              storing authtoken in the session storage to be retrieved in different views
                - an item of key "token" and value authToken (Authorization token passed back from Javalin) is stored in the sessionStorage
             */
      sessionStorage.setItem("token", authToken);

      // navigate to a different view (ie: homepage)
      // window.location.href="../htmlFiles/login.html";
      let x = sessionStorage.token;
      window.location.href = "employeeDash.html";
      console.log("You are in as an employee!!!");

    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML = "Unable to register.";
    }
  };
  //used for login
  // xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  let requestBody = JSON.stringify(newEmployee);

  xhr.send(requestBody);
}

function newClaim() {
    // resetting error div
    document.getElementById("error-div").innerHTML = "";
  
    //retrieving user credentials
    let reimAmount = document.getElementById("reimAmount").value;
    let descrip = document.getElementById("descrip").value;
    let type = document.getElementById("type").value;
    if(type === "Lodging"){
        type = 1;
    }
    else if (type === "Travel"){
        type = 2;
    }
    else if (type === "Food"){
        type = 3;
    }
    else {
        type = 4;
    }
  
    let newReim = {reimAmount, descrip, type};
  
    //add other stuff
  
    let xhr = new XMLHttpRequest();
  
    xhr.open("POST", "http://localhost:8080/reimbursements");
    // xhr.open("POST", `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`);
  
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300)) {
        document.getElementById("error-div").innerHTML = "You have succesfully posted a claim please waight for approval!"
      } else if (xhr.readyState === 4) {
        // provide user with feedback of failure to login
        document.getElementById("error-div").innerHTML = "Unable to make a new claim.";
      }
    };
    let requestBody = JSON.stringify(newReim);
    //set this to send in the token make sure it is after the XMLHttpRequest
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send(requestBody);
  }

  


// retrieving token from session storage if it exists
let token = sessionStorage.getItem("token");

// if no token is present, redirect to the login page
if (
  !token &&
  !window.location.href.includes("htmlFiles/login.html") &&
  !window.location.href.includes("htmlFiles/register.html")
) {
// window.location.href = "views/login.html";
  window.location.href = "login.html";
}

// targets logout button
// document.getElementById('logout-button').addEventListener('click', logout);

function logout() {
  sessionStorage.clear();
  window.location.href = "../htmlFiles/login.html";
}
