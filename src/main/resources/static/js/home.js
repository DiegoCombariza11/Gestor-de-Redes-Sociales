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


fetch('/friends', {
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
    .then(friends => {
        console.log('Friends:', friends);
        const friendsList = document.getElementById('friends-list');
        friendsList.innerHTML = '';
        friends.forEach(friend => {
            const friendItem = document.createElement('li');
            friendItem.textContent = friend;
            friendsList.appendChild(friendItem);
        });
    })
    .catch(error => console.error('Error:', error));


fetch('/posts', {
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
    const postsTableBody = document.getElementById('post-tbody');
    postsTableBody.innerHTML = '';
    posts.forEach(post => {
        const postRow = document.createElement('tr');
        postRow.innerHTML = `
            <td id="content">${post.content}</td>
            <td>${post.date}</td>
            <td>
                   <button class="btn btn-primary view-button">View</button>
           </td>
        `;
        postsTableBody.appendChild(postRow);
    });



document.addEventListener('click', function (event) {
    if (event.target.matches('.view-button')) {
        const postContent = event.target.parentElement.parentElement.querySelector('#content').textContent;
        const postData = { "content": postContent };

        fetch('/selectPost', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            } else {
                console.error('Error en la respuesta del servidor', response.statusText);
            }
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
    }
});


    /*
    document.addEventListener('DOMContentLoaded', function () {
        const viewButtons = document.querySelectorAll('.view-button');
u
        viewButtons.forEach(btton => {
            button.addEventListener('click', function () {
                const postContent = button.parentElement.parentElement.querySelector('#content').textContent;
                const postData = { "content": postContent };
                console.log(postData)

                fetch('/selectPost', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(postData)
                })
                    .then(response => {
                        if (response.redirected) {
                            window.location.href = response.url;
                        } else {
                            errorMessage.textContent = 'Uh oh, ocurrió un error, llamen al ingeniero';
                            errorMessage.style.display = 'block';
                            console.log('Error en la respuesta del servidor llamen al ingeniero ', response.statusText);
                        }
                        return response.json();
                    })
                    .catch(error => {
                        console.error('There has been a problem with your fetch operation:', error);
                    });
            });
        });
    });
    */
});
