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
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
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
  if (type === "Lodging") {
    type = 1;
  } else if (type === "Travel") {
    type = 2;
  } else if (type === "Food") {
    type = 3;
  } else {
    type = 4;
  }

  let newReim = { reimAmount, descrip, type };

  //add other stuff

  let xhr = new XMLHttpRequest();

  xhr.open("POST", "http://localhost:8080/reimbursements");
  // xhr.open("POST", `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      document.getElementById("error-div").innerHTML =
        "You have succesfully posted a claim please waight for approval!";
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to make a new claim.";
    }
  };
  let requestBody = JSON.stringify(newReim);
  //set this to send in the token make sure it is after the XMLHttpRequest
  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.send(requestBody);
}

function getUserProfile() {
  document.getElementById("error-div").innerHTML = "";
  let xhr = new XMLHttpRequest();
  xhr.open(
    "GET",
    `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`
  );
  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let user = xhr.response;
      user = JSON.parse(user);
      document.getElementById("firstName").innerHTML = user.firstName;
      document.getElementById("lastName").innerHTML = user.lastName;
      document.getElementById("username").innerHTML = user.username;
      // document.getElementById("password").innerHTML = user.password;
      document.getElementById("email").innerHTML = user.email;
      document.getElementById("role").innerHTML = user.role.role;
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to find User Profile.";
    }
  };
  xhr.send();
}

function getAllPReims() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", `http://localhost:8080/reimbursements/status/1`);

  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let reims = xhr.response;
      reims = JSON.parse(reims);
      printPReims(reims);
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to find Reimbursements.";
    }
  };
  xhr.send();
}

function getAllDReims() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", `http://localhost:8080/reimbursements/status/2`);

  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let reims = xhr.response;
      reims = JSON.parse(reims);
      printReims(reims);
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to find Reimbursements.";
    }
  };
  xhr.send();
}

function getAllAReims() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", `http://localhost:8080/reimbursements/status/3`);

  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let reims = xhr.response;
      reims = JSON.parse(reims);
      printReims(reims);
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to find Reimbursements.";
    }
  };
  xhr.send();
}

function getAllRequestsByStatusAndAuthorP() {
  let xhr = new XMLHttpRequest();
  xhr.open(
    "GET",
    `http://localhost:8080/reimbursements/status/1?author_id=${
      sessionStorage.token.split(":")[0]
    }`
  );

  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let reims = xhr.response;
      reims = JSON.parse(reims);
      printReims(reims);
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to find Reimbursements.";
    }
  };
  xhr.send();
}

function getAllRequestsByStatusAndAuthorD() {
  let xhr = new XMLHttpRequest();
  xhr.open(
    "GET",
    `http://localhost:8080/reimbursements/status/3?author_id=${
      sessionStorage.token.split(":")[0]
    }`
  );

  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let reims = xhr.response;
      reims = JSON.parse(reims);
      printReims(reims);
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to find Reimbursements.";
    }
  };
  xhr.send();
}

function getAllRequestsByStatusAndAuthorA() {
  let xhr = new XMLHttpRequest();
  xhr.open(
    "GET",
    `http://localhost:8080/reimbursements/status/2?author_id=${
      sessionStorage.token.split(":")[0]
    }`
  );

  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let reims = xhr.response;
      reims = JSON.parse(reims);
      printReims(reims);
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to find Reimbursements.";
    }
  };
  xhr.send();
}

function printReims(reims) {
  reims.forEach((row) => {
    const tr = document.createElement("tr");
    const td1 = document.createElement("td");
    const td2 = document.createElement("td");
    const td3 = document.createElement("td");
    const td4 = document.createElement("td");
    const td5 = document.createElement("td");
    const td6 = document.createElement("td");
    const td7 = document.createElement("td");
    const td8 = document.createElement("td");
    const td9 = document.createElement("td");

    td1.textContent = row.reimId;
    tr.appendChild(td1);
    td2.textContent = row.reimAmount;
    tr.appendChild(td2);
    td3.textContent = timeFix(row.submit);
    tr.appendChild(td3);
    td4.textContent = timeFix(row.resolve);
    tr.appendChild(td4);
    td5.textContent = row.descrip;
    tr.appendChild(td5);
    td6.textContent = row.author.username;
    tr.appendChild(td6);
    td7.textContent = row.resolver.username;
    tr.appendChild(td7);
    td8.textContent = row.status.status;
    tr.appendChild(td8);
    td9.textContent = row.type.type;
    tr.appendChild(td9);
    tableBody.appendChild(tr);
  });
}

function printPReims(reims) {
  reims.forEach((row) => {
    const tr = document.createElement("tr");
    const td1 = document.createElement("td");
    const td2 = document.createElement("td");
    const td3 = document.createElement("td");
    const td4 = document.createElement("td");
    const td5 = document.createElement("td");
    const td6 = document.createElement("td");
    const td7 = document.createElement("td");

    td1.textContent = row.reimId;
    tr.appendChild(td1);
    td2.textContent = row.reimAmount;
    tr.appendChild(td2);
    td3.textContent = timeFix(row.submit);
    tr.appendChild(td3);
    td4.textContent = row.descrip;
    tr.appendChild(td4);
    td5.textContent = row.author.username;
    tr.appendChild(td5);
    td6.textContent = row.status.status;
    tr.appendChild(td6);
    td7.textContent = row.type.type;
    tr.appendChild(td7);
    $('<td>Select: <input type="checkbox" /></td>').appendTo(tr);
    tableBody.appendChild(tr);
  });
}

function denyAllSelectedReimsById() {
  let array = GetSelected();
  let count = 0;
  array.forEach(function (x) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/reimbursements/${x}`);
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
        let reims = xhr.response;
        reims = JSON.parse(reims);
        console.log(reims);
        count++;
        denySelectedReims(reims, count, array.length);
      } else if (xhr.readyState === 4) {
        // provide user with feedback of failure to login
        document.getElementById("error-div").innerHTML =
          "Unable to find User Profile.";
      }
    };
    xhr.send();
  });
}

function approveAllSelectedReimsById() {
  let array = GetSelected();
  let count = 0;
  console.log(array);
  array.forEach(async function (x) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/reimbursements/${x}`);
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
        let reims = xhr.response;
        // console.log("test" + reims);
        reims = JSON.parse(reims);
        // console.log(reims);
        count++;
        acceptSelectedReims(reims, count, array.length);
        // acceptSelectedReims(reims);
      } else if (xhr.readyState === 4) {
        // provide user with feedback of failure to login
        document.getElementById("error-div").innerHTML =
          "Unable to find User Profile.";
      }
    };
    xhr.send();
  });
}

function acceptSelectedReims(reims, count, numberOfReimb) {
// function acceptSelectedReims(reims) {
  document.getElementById("error-div").innerHTML = "";
  let xhr1 = new XMLHttpRequest();
  reims.status.statusId = 2;
  console.log(reims);
  xhr1.onreadystatechange = function () {
    if (xhr1.readyState === 4 && xhr1.status >= 200 && xhr1.status < 300) {
      document.getElementById("error-div").innerHTML = "Update successfull.";
      if(count === numberOfReimb){
        location.reload();
      }
    } else {
      console.log(xhr1.readyState + " " + xhr1.status);
      document.getElementById("error-div").innerHTML = "Update failed.";
    }
  };
  xhr1.open("PUT", `http://localhost:8080/reimbursements/${reims.reimId}`);
  xhr1.setRequestHeader("Authorization", sessionStorage.token);
  xhr1.send(JSON.stringify(reims));
}

function denySelectedReims(reims, count, numberOfReimb) {
  document.getElementById("error-div").innerHTML = "";
  let xhr1 = new XMLHttpRequest();
  reims.status.statusId = 3;
  console.log(reims);
  xhr1.onreadystatechange = function () {
    if (xhr1.readyState === 4 && xhr1.status >= 200 && xhr1.status < 300) {
      document.getElementById("error-div").innerHTML = "Update successfull.";
      if(count === numberOfReimb){
        location.reload();
      }
    } else {
      console.log(xhr1.readyState + " " + xhr1.status);
      document.getElementById("error-div").innerHTML = "Update failed.";
    }
  };
  xhr1.open("PUT", `http://localhost:8080/reimbursements/${reims.reimId}`);
  xhr1.setRequestHeader("Authorization", sessionStorage.token);
  xhr1.send(JSON.stringify(reims));
}

function GetSelected() {
  //Reference the Table.
  var grid = document.getElementById("choiceTable");

  //Reference the CheckBoxes in Table.
  var checkBoxes = grid.getElementsByTagName("INPUT");
  let array = [];

  //Loop through the CheckBoxes.
  for (var i = 0; i < checkBoxes.length; i++) {
    if (checkBoxes[i].checked) {
      var row = checkBoxes[i].parentNode.parentNode;
      array.push(row.cells[0].innerHTML);
    }
  }
  return array;
}

function timeFix(time) {
  if (time != null) {
    var d = new Date(time);
    var formattedDate =
      d.getMonth() + 1 + "/" + d.getDate() + "/" + d.getFullYear();
    var hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
    var minutes = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
    var formattedTime = hours + ":" + minutes;
    formattedDate = formattedDate + " " + formattedTime;
  }
  return formattedDate;
}

function getAllUsers() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", `http://localhost:8080/users`);
  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let users = xhr.response;
      users = JSON.parse(users);
      printUsers(users);
    } else if (xhr.readyState === 4) {
      // provide user with feedback of failure to login
      document.getElementById("error-div").innerHTML =
        "Unable to find User Profile.";
    }
  };
  xhr.send();
}

function printUsers(users) {
  while (tableBody.firstChild) {
    tableBody.removeChild(tableBody.firstChild);
  }
  users.forEach((row) => {
    const tr = document.createElement("tr");
    // tr.textContent = row.id;
    const td1 = document.createElement("td");
    const td2 = document.createElement("td");
    const td3 = document.createElement("td");
    const td4 = document.createElement("td");
    const td5 = document.createElement("td");
    td1.textContent = row.id;
    tr.appendChild(td1);
    td2.textContent = row.firstName + " " + row.lastName;
    tr.appendChild(td2);
    td3.textContent = row.username;
    tr.appendChild(td3);
    td4.textContent = row.email;
    tr.appendChild(td4);
    td5.textContent = row.role.role;
    tr.appendChild(td5);
    tableBody.appendChild(tr);
  });
}

function updateUserProfile() {
  document.getElementById("error-div").innerHTML = "";
  let xhr = new XMLHttpRequest();
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let email = document.getElementById("email").value;
  let role = document.getElementById("role").value;
  if (role === "Finance Manager(FM)") {
    role = 2;
  } else {
    role = 1;
  }
  let updatedEmployee = {
    firstName,
    lastName,
    username,
    password,
    email,
    role,
  };
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      let x = sessionStorage.token;
      if (role === 2) {
        sessionStorage.setItem("token", x.split(":")[0] + ":" + "2");
      } else if (role === 1) {
        sessionStorage.setItem("token", x.split(":")[0] + ":" + "1");
      }
      document.getElementById("error-div").innerHTML = "Update successfull.";
    } else {
      document.getElementById("error-div").innerHTML = "Update failed.";
    }
  };
  xhr.open(
    "PUT",
    `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`
  );
  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.send(JSON.stringify(updatedEmployee));
}

// needs work
function updateRequests() {
  document.getElementById("error-div").innerHTML = "";
  let xhr = new XMLHttpRequest();
  let amount = document.getElementById("amount").value;
  let submitted = document.getElementById("submitted").value;
  let description = document.getElementById("description").value;
  let author = document.getElementById("author").value;
  let status = document.getElementById("status").value;
  if (status === "Approved") {
    status = 2;
  } else {
    status = 3;
  }
  let type = document.getElementById("type").value;
  if (type === "lodging") {
    type = 1;
  } else if (type === "travel") {
    type = 2;
  } else if (type === "food") {
    type = 3;
  } else {
    type = 4;
  }
  let updatedReim = {
    amount,
    submitted,
    description,
    author,
    status,
    type,
  };
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
      document.getElementById("error-div").innerHTML = "Update successfull.";
    } else {
      document.getElementById("error-div").innerHTML = "Update failed.";
    }
  };
  xhr.open("PUT", `http://localhost:8080/reimbursements/${reimId}}`);
  xhr.setRequestHeader("Authorization", sessionStorage.token);
  xhr.send(JSON.stringify(updatedReim));
}

function redirect() {
  let authToken = sessionStorage.getItem("token");
  if (authToken.split(":")[1] === "1") {
    window.location.href = "employeeDash.html";
  } else {
    window.location.href = "managerDash.html";
  }
}

function clearAll() {
  while (tableBody.firstChild) {
    tableBody.removeChild(tableBody.firstChild);
  }
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

function logout() {
  sessionStorage.clear();
  window.location.href = "../htmlFiles/login.html";
}