$(document).ready(function () {
    load();
});

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
    }).then(response => response.json())
        .then(data => {
            console.log("Post successfully created:", data);
            // Handle successful post creation (e.g., show a message, clear form, etc.)
        })
        .catch(error => {
            console.error("Error creating post:", error);
            // Handle error
        });
});

function load() {
    fetch('/post', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
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

