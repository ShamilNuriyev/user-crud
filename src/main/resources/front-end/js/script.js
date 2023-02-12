const usersList = document.getElementById("usersList");
const apiUrl = "http://localhost:8585/api/v1/users";

// Function to add user
const addUser = async () => {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;

    if (!firstName || !lastName || !email) {
        alert("Please fill out all the required fields");
        return;
    }
    validateForm(firstName, lastName, email);

    try {
        const res = await fetch(apiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ firstName, lastName, email })
        });
        const data = await res.json();
        closeUserModal();
        showUsers();
    } catch (error) {
        console.error("Error adding user", error);
    }
};

// Function to show users
const showUsers = async () => {
    try {
        const res = await fetch(apiUrl);
        const data = await res.json();

        let usersHtml = "";
        data.forEach(user => {
            usersHtml += `
        <tr>
          <td>${user.firstName}</td>
          <td>${user.lastName}</td>
          <td>${user.email}</td>
          <td>
            <button type="button" class="btn btn-primary mr-2" onClick="showAddUserModalForUpdate('${user.firstName}','${user.lastName}','${user.email}','${user.id}')">Edit</button>
            <button type="button" class="btn btn-danger" onClick="deleteUser('${user.id}')">Delete</button>
          </td>
        </tr>
      `;
        });
        usersList.innerHTML = usersHtml;
    } catch (error) {
        console.error("Error fetching users", error);
    }
};

// Function to delete user
const deleteUser = async (id) => {
    try {
        const res = await fetch(`${apiUrl}/${id}`, {
            method: "DELETE"
        });
        const data = await res.json();
        showUsers();
    } catch (error) {
        console.error("Error deleting user:", error);
    }
    showUsers();
};


// Function to update user
const updateUser = async (id) => {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;

    if (!firstName || !lastName || !email) {
        alert("Please fill out all the required fields");
        return;
    }

    validateForm(firstName, lastName, email);

    try {
        const res = await fetch(`${apiUrl}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ firstName, lastName, email })
        });
        const data = await res.json();
        showUsers();
        closeUserModal();
    } catch (error) {
        console.error("Error updating user", error);
    }
};

showUsers();


const showAddUserModalForAddingUser = () => {
    document.getElementById("modal-title").innerHTML = "Add User";
    document.getElementById("userModal").style.display = "block";
    document.getElementById("firstName").value = "";
    document.getElementById("lastName").value = "";
    document.getElementById("email").value = "";
};

let userID;
const showAddUserModalForUpdate = (firstName,lastName,email,id) => {
    document.getElementById("modal-title").innerHTML = "Update User";
    document.getElementById("userModal").style.display = "block";
    document.getElementById("firstName").value = firstName;
    document.getElementById("lastName").value = lastName;
    document.getElementById("email").value = email;
    userID = id;
};
const closeUserModal = () => {
    document.getElementById("userModal").style.display = "none";
};

const saveOrUpdate = () => {
    if (userID == null){
        addUser();
    }
    else {
        updateUser(userID);
        userID = null;
    }
};

//validation

function validateForm(firstName, lastName, email) {
    let nameRegex = /^[a-zA-Z ]{3,30}$/;
    let emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (!nameRegex.test(firstName)) {
        alert('Please enter a valid first name');
        firstName.focus();
        return false;
    }
    if (!nameRegex.test(lastName)) {
        alert('Please enter a valid last name');
        lastName.focus();
        return false;
    }
    if (!emailRegex.test(email)) {
        alert('Please enter a valid email address');
        email.focus();
        return false;
    }
    return true;
}
