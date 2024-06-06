function getSimpleCookie(name) {
    let cookieValue = document.cookie
        .split('; ')
        .find(row => row.startsWith(name + '='))
        .split('=')[1];
    return cookieValue || null;
}


let userCookieValue = getSimpleCookie('user');
let socialMediaCookieValue = getSimpleCookie('socialMedia');
let password = getSimpleCookie('password');


console.log('Cookie de usuario: ' + userCookieValue);
console.log('Cookie de red social: ' + socialMediaCookieValue);

document.getElementById('username').textContent = userCookieValue;





fetch('/api/posts', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        'username': userCookieValue,
        'password': password,
        'socialMediaName': socialMediaCookieValue
    })
})
.then(response => response.json())
.then(posts => {
    console.log('Posts:', posts);
    const postsContainer = document.getElementById('posts-container');
    postsContainer.innerHTML = '';
    posts.forEach(post => {
        const postElement = document.createElement('div');
        postElement.className = 'post';
        postElement.innerHTML = `
            <h2>${post.title}</h2>
            <p>${post.content}</p>
            <p>${post.author}</p>
            <p>${post.date}</p>
        `;
        postsContainer.appendChild(postElement);
    });

})
.catch(error => console.error('Error:', error));