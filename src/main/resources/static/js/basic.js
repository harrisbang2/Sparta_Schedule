$(document).ready(function () {
    const auth = getToken();
    if(auth === '') {
        $('#login-true').show();
        $('#login-false').hide();
        //window.location.href = host + "/api/user/login-page";
    } else {
        $('#login-true').show();
        $('#login-false').hide();
    }
})

function logout() {
    // 토큰 삭제
    window.Cookies.remove('Authorization', { path: '/' });
    window.location.href = host + "/api/user/login-page";
}

function getToken() {
    let auth = window.Cookies.get('Authorization');

    if(auth === undefined) {
        return '';
    }

    return auth;
}