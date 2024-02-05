let host = 'http://' + window.location.host;

// $(document).ready(function () {
//     const auth = getToken();
//     if(auth === '') {
//         window.location.href = host + "/api/user/login-page";
//     } else {
//         $('#login-true').show();
//         $('#login-false').hide();
//     }
// })

function SearchPage() {
    // 토큰 삭제
    //Cookies.remove('Authorization', { path: '/' });
    window.location.href = host + "/api/user/search-page";
}
function homePage() {
    // 토큰 삭제
    //Cookies.remove('Authorization', { path: '/' });
    window.location.href = host + "/";
}
