$(async function () {
    await getAllUsers();
    getNewUserForm();
    getDefaultModal();
    addNewUser();
})

async function getAllUsers() {
    let tBody = document.getElementById("allUsersRows")
    tBody.innerHTML = ""

    fetch('http://localhost:8080/allUsers')
        .then(responce => responce.json())
        .then(users => {
            users.forEach(function (user)  {
                let row = tBody.insertRow()
                row.setAttribute("id", user.id);

                let cell0 = row.insertCell(0)
                cell0.innerHTML = user.id

                let cell1 = row.insertCell(1)
                cell1.innerHTML = user.name

                let cell2 = row.insertCell(2)
                cell2.innerHTML = user.lastName

                let cell3 = row.insertCell(3)
                cell3.innerHTML = user.email

                let cell4 = row.insertCell(4)
                cell4.innerHTML = userRolesForPrint(user.roles)

                let cell5 = row.insertCell(5)
                cell5.innerHTML =
                    '<button type="button" ' +
                    'onclick="getModalEdit(' + user.id + ')" ' +
                    'class="btn btn-primary btn-sm">Edit</button>'

                let cell6 = row.insertCell(6);
                cell6.innerHTML =
                    '<button type="button" ' +
                    'onclick="getModalDelete(' + user.id + ')" ' +
                    'class="btn btn-danger btn-sm">Delete</button>'
            })
        })
}

function getNewUserForm() {

}

function getDefaultModal() {

}

function addNewUser() {

}

function userRolesForPrint(roles) {
    let rolesForPrint = '';
    $.each(roles, function (key, value) {
        rolesForPrint += (value.role.substr(5) + ' ');
    })
    return rolesForPrint;
}
