const allUsersURL = 'http://localhost:8080/allUsers'
const allRolesURL = 'http://localhost:8080/allRoles'
const userByIdURL = 'http://localhost:8080/getUserById'
const head = {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Referer': null
}

$(async function () {
    await getAllUsers();
    await showRolesSelector();
})

function userRolesForPrint(roles) {
    let rolesForPrint = '';
    $.each(roles, function (key, value) {
        rolesForPrint += (value.role.substr(5) + ' ');
    })
    return rolesForPrint;
}

async function getAllUsers() {
    let tBody = document.getElementById("allUsersRows")
    tBody.innerHTML = ""
    await fetch(allUsersURL)
        .then(responce => responce.json())
        .then(users => {
            users.forEach(async user => {
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
                    'onclick="showModalEdit(' + user.id + ')" ' +
                    'class="btn btn-primary btn-sm">Edit</button>'
                let cell6 = row.insertCell(6);
                cell6.innerHTML =
                    '<button type="button" ' +
                    'onclick="showModalDelete(' + user.id + ')" ' +
                    'class="btn btn-danger btn-sm">Delete</button>'
            })
        })
}

async function showRolesSelector() {
    selector = document.getElementById("rolesSelector")
    selector.id = "roles-newUser"
    fetch(allRolesURL)
        .then(responce => responce.json())
        .then(roles => {
            roles.forEach(role => {
                let option = document.createElement('option');
                option.value = role.id
                option.text = role.role
                selector.appendChild(option)
            })
        })
}

async function addNewUser() {
    let roles = []
    document.getElementById("roles-newUser").childNodes.forEach(node => {
        if (node.selected == true) {
            roles.push(role = {id: node.value})
        }
    })
    let user = {
        name: document.getElementById("name-newUser").value,
        lastName: document.getElementById("lastName-newUser").value,
        email: document.getElementById("email-newUser").value,
        active: document.getElementById("active-newUser").checked,
        username: document.getElementById("username-newUser").value,
        password: document.getElementById("password-newUser").value,
        roles: roles
    }
    await fetch(`/add`, {method: 'PUT', headers: head, body: JSON.stringify(user)})
        .then(responce => responce.json())
        .then(user => {
            let tBody = document.getElementById("allUsersRows")
            let row = tBody.insertRow()
            row.setAttribute("id", user.id);
            console.log(user.id)
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
                'onclick="showModalEdit(' + user.id + ')" ' +
                'class="btn btn-primary btn-sm">Edit</button>'
            let cell6 = row.insertCell(6);
            cell6.innerHTML =
                '<button type="button" ' +
                'onclick="showModalDelete(' + user.id + ')" ' +
                'class="btn btn-danger btn-sm">Delete</button>'
        })
}

async function deleteUser(id) {
    await fetch(`/${id}`, {method: 'DELETE', headers: head})
    await getAllUsers()
    let modalDialog = $('#modalDialog')
    modalDialog.modal("hide")
}

async function editUser(id) {
    let modalDialog = $('#modalDialog')
    let roles = []
    document.getElementById("roles-editUser").childNodes.forEach(node => {
        if (node.selected == true) {
            roles.push(role = {id: node.value})
        }
    })
    let user = {
        id: id,
        name: document.getElementById("name-editUser").value,
        lastName: document.getElementById("lastName-editUser").value,
        email: document.getElementById("email-editUser").value,
        active: document.getElementById("active-editUser").checked,
        username: document.getElementById("username-editUser").value,
        password: document.getElementById("password-editUser").value,
        roles: roles
    }
    await fetch(`/${id}`, {method: 'PATCH', headers: head, body: JSON.stringify(user)})
    modalDialog.modal("hide")
    await getAllUsers()
}

async function showModalEdit(id) {
    await fetchModal(id, 'editUser', 'Edit user')
}

async function showModalDelete(id) {
    await fetchModal(id, "deleteUser",'Delete user')
}

async function fetchModal(id, command, title) {
    let modalTitle = document.getElementById('modalTitle')
    modalTitle.textContent = title
    await fetch(userByIdURL + `/${id}`)
        .then(responce => responce.json())
        .then(async user => {
            let modalBody = document.getElementById('modalBody')
            while (modalBody.hasChildNodes()) {
                modalBody.removeChild(modalBody.lastChild);
            }
            let isViewOnly = command == "deleteUser" ? true : false
            await showProperty(user, command, "id", true)
            await showProperty(user, command, "name", isViewOnly)
            await showProperty(user, command, "lastName", isViewOnly)
            await showProperty(user, command, "email", isViewOnly)
            await showProperty(user, command, "active", isViewOnly, "checkbox")
            if (command == "editUser") {
                await showProperty(user, command, "username")
                await showProperty(user, command, "password")
            }
            await showProperty(user, command, "roles", isViewOnly)
        })
    let btnType = command == "deleteUser" ? 'btn-danger' : 'btn-primary'
    let modalFooter = document.getElementById('modalFooter')
    modalFooter.innerHTML =
        '<button type="button" ' +
        'onclick="hideDialog()" ' +
        'class="btn btp-primary btn-sm">Close</button> ' +
        '<button type="button" ' +
        'onclick="' + command + '(' + id + ')" ' +
        'class="btn ' + btnType + ' btn-sm">' + title.replace(" user","") + '</button>'
    $('#modalDialog').modal()
}

async function showProperty(user, command, propertyName, isViewOnly, elementType = "text") {
    let modalBody = document.getElementById('modalBody')
    let label = document.createElement("label")
    label.setAttribute("for", propertyName)
    label.textContent = propertyName.charAt(0).toUpperCase() + propertyName.slice(1)
    label.setAttribute("class", "font-weight-bold")
    modalBody.appendChild(label)
    if (propertyName == "roles") {
        await createSelector(user, elementType, propertyName, modalBody, isViewOnly, command)
    } else {
        await createInput (user, elementType, propertyName, modalBody, isViewOnly, command)
    }
    modalBody.appendChild(document.createElement("br"))
}

async function createInput (user, elementType, propertyName, parent, isViewOnly, command) {
    let formGroup = document.createElement("div")
    formGroup.setAttribute("class", "font-weight-bold")
    let input = document.createElement("input")
    input.type = propertyName == "password" ? "password" : elementType
    input.name = propertyName
    input.id = propertyName + '-' + command
    input.setAttribute("class", "form-control")
    input.disabled = isViewOnly
    input.value = propertyName == "password" ? "" : user[propertyName]
    formGroup.appendChild(input)
    parent.appendChild(formGroup)
}

async function createSelector(user, elementType, propertyName, parent, isViewOnly, command) {
    let formGroup = document.createElement("div")
    formGroup.setAttribute("class", "form-group")
    formGroup.id = propertyName
    selector = document.createElement("select")
    selector.disabled = isViewOnly
    selector.setAttribute("multiple", "multiple")
    selector.setAttribute("name", "roleIDS")
    selector.id = propertyName + '-' + command
    await fetch(allRolesURL)
        .then(responce => responce.json())
        .then(roles => {
            roles.forEach(role => {
                let option = document.createElement('option');
                option.id = role.id + '-option-' + command
                option.value = role.id
                option.text = role.role
                selector.appendChild(option)
            })
        })
    formGroup.appendChild(selector)
    parent.appendChild(formGroup)
}

async function hideDialog() {
    let modalDialog = $('#modalDialog')
    modalDialog.modal("hide")
}
