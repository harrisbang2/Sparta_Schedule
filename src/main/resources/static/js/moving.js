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

function searchPage() {
    // 토큰 삭제
    //Cookies.remove('Authorization', { path: '/' });
    window.location.href = host + "/api/user/search-page";
}
function homePage() {
    // 토큰 삭제
    //Cookies.remove('Authorization', { path: '/' });
    window.location.href = host + "/";
}
function commentPage() {
    alert("오류로 작동이 안됩니다...");
    // 오류
    // window.location.href = host + "/api/user/comment-page";
    window.location.href = host + "/";
}