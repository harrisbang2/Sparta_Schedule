function logout() {
    // 토큰 삭제 요청.
    const cookies = document.cookie.split(";");
    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i];
        const eqPos = cookie.indexOf("=");
        const name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
    $.ajax({
        type: "GET",
        url: `/logout`,
        success: function (response) {
            alert('로그아웃');
            window.location.href = host + "/api/user/login-page";
        }
    })
}