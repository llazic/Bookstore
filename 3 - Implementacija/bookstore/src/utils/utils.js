
let logout = function (router) {
    localStorage.setItem('session', null);
    router.push('/');
}

let getSession = function() {
    let session = localStorage.getItem('session');
    if (session != null) session = JSON.parse(session);
    return session;
}

let checkValidUser = function(user_type) {
    let session = getSession();
    if (session == null) return false;
    let users = JSON.parse(localStorage.getItem('users'));
    let user = users.find(u => u.id == session.currentUserId);
    return user != null && user.user_type == user_type;
}

let isEmptyOrNull = function (variable){
    return variable == null || variable == '';
}


export { logout, getSession, checkValidUser, isEmptyOrNull };

