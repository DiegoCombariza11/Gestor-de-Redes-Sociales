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
            <td>${post.title}</td>
            <td>${post.date}</td>
            <td>
                <a href="viewPost.html?id=${post.id}" class="btn btn-primary">View</a>
                <a onclick="edit()" class="btn btn-secondary">Edit</a>
                <a href="#" class="btn btn-danger" onclick="deletePost(${post.id})">Delete</a>
            </td>
        `;
        postsTableBody.appendChild(postRow);
    });
})
.catch(error => console.error('Error:', error));





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

async function edit(){
    const post = document.getElementById('post-tbody').value;
    fetch('/selectPost', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'post': post
        })
    }).then(response => {
        if (response.status === 302 || response.status === 200) {
            window.location.href = '/pages/Post.html';
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
}