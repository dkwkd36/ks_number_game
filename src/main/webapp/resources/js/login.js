$(document).ready(function () {
    $("#loginForm").submit(function (event) {
        validateLogin(event);
    });
});

function validateLogin(event) {
    event.preventDefault();
    let member_id = $("#member_id").val().trim();
    let member_password = $("#member_password").val().trim();
    let error_message = $("#error_message");

    error_message.text(""); // 🎯 에러 메시지 초기화

    if (member_id === "") {
        error_message.text("会員IDは6桁で入力してください。");
        return;
    }

    if (member_id.length !== 6) {
        error_message.text("会員IDは6桁で入力してください。");
        return;
    }

    let memberIdRegex = /^[0-9]+$/;
    if (!memberIdRegex.test(member_id)) {
        error_message.text("会員IDは半角数字以外は入力できません");
        return;
    }

    if (member_password === "") {
        error_message.text("パスワードは8桁で入力してください。");
        return;
    }

    if (member_password.length !== 8) {
        error_message.text("パスワードは8桁で入力してください。");
        return;
    }

    let passwordRegex = /^[a-zA-Z0-9]+$/;
    if (!passwordRegex.test(member_password)) {
        error_message.text("パスワードは半角英数字以外は入力できません");
        return;
    }

    $("#loginForm").off("submit").submit(); // 🎯 정상적인 경우 폼 제출
}