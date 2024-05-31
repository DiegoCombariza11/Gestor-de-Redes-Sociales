document.write("JavaScript Funcionando")
var socials = ['Facebook', 'X', 'instagram'];
var index = 0;

document.getElementById('prev').addEventListener('click', function() {
    event.preventDefault();
    index = (index - 1 + socials.length) % socials.length;
    console.log('Prev button clicked, new index is: ' + index);
    updateSocial();
});

document.getElementById('next').addEventListener('click', function() {
    event.preventDefault();
    index = (index + 1) % socials.length;
    console.log('Next button clicked, new index is: ' + index);
    updateSocial();
});

function updateSocial() {
    var logoImage = document.querySelector('#social-logo-container .social-logo-img');
    logoImage.style.opacity = 0;
    setTimeout(function() {
        logoImage.src = '/images/' + socials[index] + '.svg';
        logoImage.alt = socials[index];
        document.getElementById('socialNetwork').value = socials[index];
        console.log('Image source updated to: ' + logoImage.src);
        logoImage.style.opacity = 1;
    }, 500);
}

document.getElementById('continue').addEventListener('click', function() {
    event.preventDefault();
    console.log(document.getElementById('username').value);
    console.log(document.getElementById('password').value)
    console.log(socials[index]);
    console.log("Funciona el berraco botón");
    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            'username': document.getElementById('username').value,
            'password': document.getElementById('password').value,
            //'socialNetwork': socialNetwork
        })
    })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            }
        })
        .catch(error => console.error('Error:', error));
});
   // window.location.replace('/pages/Home.html');






