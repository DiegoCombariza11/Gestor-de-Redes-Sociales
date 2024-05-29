document.write("JavaScript Funcionando")
var socials = ['facebook', 'X', 'instagram'];
var index = 0;

document.getElementById('prev').addEventListener('click', function() {
    index = (index - 1 + socials.length) % socials.length;
    updateSocial();
});

document.getElementById('next').addEventListener('click', function() {
    index = (index + 1) % socials.length;
    updateSocial();
});

function updateSocial() {
    var images = document.getElementsByClassName('social-logo-img');
    for (var i = 0; i < images.length; i++) {
        images[i].src = '/static/images/' + socials[index] + '.svg';
    }
    document.getElementById('socialNetwork').value = socials[index];
}

document.getElementById('continue').addEventListener('click', function() {
    event.preventDefault();
    console.log(document.getElementById('username').value);
    console.log(document.getElementById('password').value)
    console.log(socials[index]);
    console.log("Funciona el berraco botón");
    window.location.replace('/pages/Home.html');
});
