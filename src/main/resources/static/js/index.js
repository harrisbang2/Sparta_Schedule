
    // 사용자가 내용을 올바르게 입력하였는지 확인합니다.
    function isValidContents(contents) {
    if (contents == '') {
    return false;
}
    if (contents.trim().length > 140) {
    alert('공백 포함 140자 이하로 입력해주세요');
    return false;
}
    return true;
}

    // 수정 버튼을 눌렀을 때, 기존 작성 내용을 textarea 에 전달합니다.
    // 숨길 버튼을 숨기고, 나타낼 버튼을 나타냅니다.

    function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();
    $(`#${id}-contents`).hide();
    $(`#${id}-edit`).hide();
}

    // 메모를 불러와서 보여줍니다.
    function getMessages() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#cards-box').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
    type: 'GET',
    url: '/api/schedule',
    success: function (response) {
    for (let i = 0; i < response.length; i++) {
    let message = response[i];
    let id = message['id'];
    let contents = message['contents'];
    let date = message['date'];
    addHTML(id, contents, date);
}
}
})
}

    // 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
    function addHTML(id, contents, date) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `<div class="card">
                <!-- date/username 영역 -->
                <div class="metadata">
                    <div class="date">
                        ${date}
                    </div>
                    <div id="${id}-username">
                 
                    </div>
                </div>
                <!-- contents 조회/수정 영역-->
                <div class="contents">
                    <div id="${id}-contents" class="text">
                        ${contents}
                    </div>
                    <div id="${id}-editarea" class="edit">
                        <textarea id="${id}-textarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                    </div>
                </div>
                <!-- 버튼 영역-->
                <div class="footer">
                    <img id="${id}-edit" class="icon-start-edit" src="/images/edit.png" alt="" onclick="editPost('${id}')">
                    <img id="${id}-delete" class="icon-delete" src="/images/delete.png" alt="" onclick="deleteOne('${id}')">
                    <img id="${id}-submit" class="icon-end-edit" src="/images/done.png" alt="" onclick="submitEdit('${id}')">
                </div>
            </div>`;
    // 2. #cards-box 에 HTML을 붙인다.
    $('#cards-box').append(tempHtml);
}

    // 메모를 생성합니다.
    function writePost() {
    // 1. 작성한 메모를 불러옵니다.
    let contents = $('#contents').val();

    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
    return;
}
    let date = $('#date').val();
    // 4. 전달할 data JSON으로 만듭니다.
    let data = {'contents': contents,'date':date};

    // 5. POST /api/schedule 에 data를 전달합니다.
    $.ajax({
    type: "POST",
    url: "/api/schedule",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function (response) {
    alert('메시지가 성공적으로 작성되었습니다.');
    window.location.reload();
}
});
}

    // 메모를 수정합니다.
    function editPost(id) {
        let urls = "/api/schedule/search/"+id;
        // 비번 확인을 위해 비번 받기
        $.ajax({
            type: "GET",
            url: urls,
            data: {}, // GET 요청 시엔 비워둔다.
            success: function(response) { // 서버에서 받은 결과
                    showEdits(id);
                    let contents = $(`#${id}-contents`).text().trim();
                    $(`#${id}-textarea`).val(contents);
            }
        })
    }

    //
    function submitEdit(id) {
    // 1. 작성 대상 메모의 contents 를 확인합니다.
    let contents = $(`#${id}-textarea`).val().trim();
    let date = new Date();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
    return;
        }
    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'contents': contents,'date' : date};
    // 4. PUT /api/schedule/{id} 에 data를 전달합니다.
    $.ajax({
    type: "PUT",
    url: `/api/schedule/${id}`,
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function (response) {
    alert('메시지 변경에 성공하였습니다.');
    window.location.reload();
}
});
}

    // 메모를 삭제합니다.
    function deleteOne(id) {
    // 1. DELETE /api/schedule/{id} 에 요청해서 메모를 삭제합니다.
    $.ajax({
        type: "DELETE",
        url: `/api/schedule/${id}`,
        success: function (response) {
            alert('메시지 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
}