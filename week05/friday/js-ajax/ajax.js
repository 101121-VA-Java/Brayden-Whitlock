// document.getElementById('getData').onclick = getData;
document.getElementById("getData").addEventListener("click", getData);

let apiUrl = "https://pokeapi.co/api/v2/pokemon";

function getData() {
  //retrieve user input for Pokemon id
  let userInput = document.getElementById("dataInput").value;

  // 1: create XMLHttpRequest object
  let xhr = new XMLHttpRequest();

  //2: set a callback function to the readystate change event of that object
  xhr.onreadystatechange = receiveData;

  //3: parameterize / open the request object (set request information)
  xhr.open("GET", `${apiUrl}/${userInput}`);
  console.log('hello world', apiUrl, userInput);
  //4: send the request
  xhr.send();

  function receiveData() {
    // what to be done once the data is ready
    if (xhr.readyState === 4) {
      let dataSpan = document.getElementById("data");
      dataSpan.innerHTML = "";
      if (xhr.status >= 200 && xhr.status < 300) {
        let response = xhr.response;

        // Converting JSON data to JS object
        response = JSON.parse(response);

        // data processing behavior
        populateData(response);
      } else {
        dataSpan.innerHTML = `<img src="https://lh3.googleusercontent.com/proxy/PwTFjS3gKK23XkUpAs0ZPWDjjABchQ9pjEOdmbPJujuZbJd1t6tZYLwCG1R4vRFMoQgTXdehLcWyWY0hVWNDzC9TwnR1VgMNLNkE4QopBJhst4ZFvKVNoCLZqSom97GfoAAH3q3tBihVMw">`;
      }
    }
  }
}

// function populateData(response) {
//   console.log(response);
//   let moveName = "";
//   for (let i = 0; i < response.moves.length; i++) {
//     moveName = response.moves[i].move.name;
//     let example = document.createElement('div');
//     example.innerHTML = moveName;
//     document.getElementById("data").appendChild(example);
//   }

function populateData(response) {
    console.log(response);
    let sprites = Object.values(response.sprites);
    for (let i = 0; i < sprites.length; i++) {
      let pic = sprites[i];
      if (!!pic && typeof pic === 'string'){
          console.log('pic url :', pic);
          let picture = document.createElement('div');
          picture.innerHTML = `<img src="${pic}">`;
          document.getElementById("data").appendChild(picture);
      }
    }
}
//hoisting
add = function (a,b){
    return a+b;
}
// let x;
// let y;
// x = 5;
// y = 4;
// console.log('test');

// console.log(add(x,y));

console.log(add(1,2));
console.log(0.1+0.2==0.3);

/*
  1. get api 
  2. access sprite and check each key for a value 
  3. if value exists make img tag 
  4. put the url for the sprite in img tag
  5. add to the data id and put the image into the section tag
*/

  // document.getElementById('data').innerHTML = element;
//   document.getElementById("data").innerHTML =
//     response.name + " " + response.abilities[0].ability.name;
  // Using DOM manipulation, display information about pokemon to the webpage

