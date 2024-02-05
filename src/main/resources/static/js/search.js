
////// 날자로 검색
function SearchByDate(){
    $('#cards-box').empty();
    let search = $('#searchbydate').val();
    if (isValidContents(search) == false) {
        alert("검색결과가 없습니다!");
        return;
    }
    let the_url = "/api/schedule/search/date/"+search;
    $.ajax({
        type: "GET",
        url: the_url,
        contentType: "application/json",
        data: JSON.stringify(search),
        success: function (response) {
            alert('검색이 성공!');
            for (let i = 0; i < response.length; i++) {
                let message = response[i];
                let id = message['id'];
                let contents = message['contents'];
                let date = message['date'];
                addHTML(id, contents, date);
            }
        }
    });
}

// redisplay search
function redisplay(){
    $('#cards-box').empty();
    let search = $('#searchbydate').val();
    if (isValidContents(search) == false) {
        return 0;
    }
    let the_url = "/api/schedule/search/date/"+search;
    $.ajax({
        type: "GET",
        url: the_url,
        contentType: "application/json",
        data: JSON.stringify(search),
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let message = response[i];
                let id = message['id'];
                let contents = message['contents'];
                let date = message['date'];
                addHTML(id, contents, date);
            }
        }
    });
}
//searcb post
function SearchPost(){
    $('#cards-box').empty();
    let search = $('#search').val();
    if (isValidContents(search) == false) {
        alert("검색결과가 없습니다!")
        return;
    }
    let the_url = "/api/schedule/search/"+search;
    $.ajax({
        type: "GET",
        url: the_url,
        contentType: "application/json",
        data: JSON.stringify(search),
        success: function (response) {
            alert('검색이 성공!');
            let id = response['id'];
            let password = response['password'];
            let contents = response['contents'];
            let date = response['date'];
            addHTML(id, contents, date);
        }
    });
}