$(document).ready(function () {
    load();
});
function getSimpleCookie(name) {
    let cookieValue = document.cookie
        .split('; ')
        .find(row => row.startsWith(name + '='))
        .split('=')[1];
    return cookieValue || null;
}

let userCookieValue = getSimpleCookie('user');
let socialMediaCookieValue = getSimpleCookie('socialMedia');
let passwordCookieValue = getSimpleCookie('password');
document.getElementById('post1').addEventListener('click', function (event) {
    event.preventDefault();
    const content = document.getElementById('post').value;
    const date = document.getElementById('date').value;

    if (!content || !date) {
        document.getElementById('error-message').innerHTML = "Todos los campos son obligatorios.";
        return;
    } else {
        document.getElementById('error-message').innerHTML = "";
    }

    const selectedDate = new Date(date);
    const currentDate = new Date();
    selectedDate.setHours(0, 0, 0, 0);
    currentDate.setHours(0, 0, 0, 0);

    if (selectedDate < currentDate) {
        document.getElementById('error-message').innerHTML = "La fecha no puede ser menor a la fecha actual.";
        return;
    }

    let body = {
        content: content,
        date: date
    };

    fetch('/post', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }).then(response => {
        if (response.status === 302 || response.status === 200) {
            window.location.href = '/pages/Home.html';
        } else {
            return response.json();
        }
    }).then(data => {
        if (data) {
            console.log("Post creation response:", data);
        }
    }).catch(error => {
        console.error("Error creating post:", error);
    });
});


function load() {
    fetch('/getPost', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "user": userCookieValue,
            "password": passwordCookieValue,
            "socialMedia": socialMediaCookieValue,
            "post":"Hola"
        })

    })
        .then(response => response.json())
        .then(post => {
            document.getElementById('post').value = post.content;
            document.getElementById('date').value = post.date;
        })
        .catch(error => {
            console.error("Error loading post:", error);
            // Handle error
        });
}
document.getElementById('back').addEventListener('click', function (event) {
    event.preventDefault();
    window.location.href = '/pages/Home.html';
});
