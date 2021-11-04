const getAuthorizedUserURL = 'http://localhost:8080/getAuthorizedUser'

showUserInfo()

function showUserInfo(user)
{
    fetch(getAuthorizedUserURL)
        .then(responce => responce.json())
        .then(user => {
            let tBody = document.getElementById("userInfoRows");
            tBody.innerHTML = "";
            let row = tBody.insertRow(0);
            let cell0 = row.insertCell(0);
            cell0.innerHTML = user.id;
            let cell1 = row.insertCell(1);
            cell1.innerHTML = user.name;
            let cell3 = row.insertCell(2);
            cell3.innerHTML = user.lastName;
            let cell4 = row.insertCell(3);
            cell4.innerHTML = user.email;
            let cell5 = row.insertCell(4);
            cell5.innerHTML = userRolesForPrint(user.roles)
            let navbarEmail = document.getElementById("navbar-email");
            navbarEmail.innerHTML = user.email
            let navbarRoles = document.getElementById("navbar-roles");
            navbarRoles.innerHTML = userRolesForPrint(user.roles)
        })
}

function userRolesForPrint(roles) {
        let rolesForPrint = '';
        $.each(roles, function (key, value) {
                rolesForPrint += (value.role.substr(5) + ' ');
        })
        return rolesForPrint;
}