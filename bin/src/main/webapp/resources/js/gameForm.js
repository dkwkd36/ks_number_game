$(document).ready(function () {
    $("#button").click(function () {
        playGame();
    });
	
	$("#modal_button").click(function() {
		$("#result_answer").focus();
	});
	
	
	$("#gameForm").on("keypress", function (event) {
	    if (event.key === "Enter") {
	        event.preventDefault();
	        playGame();
	    }
	});

    let message = $("#message").text().trim();
    if (message) {
        showMessage(message);
    }
});

function playGame() {
    let result_answer = $("#result_answer").val().trim();
    let hidden_number = $("#hidden_number").val();
    let game_count = $("#game_count").val();
    let point = $("#point").val();

    if (result_answer === "") {
        showMessage("入力数字を3桁で入力してください。");
        return;
    }

    if (result_answer.length !== 3) {
        showMessage("入力数字を3桁で入力してください。");
        return;
    }

    let numberRegex = /^[0-9]+$/;
    if (!numberRegex.test(result_answer)) {
        showMessage("半角数字以外は入力できません。");
        return;
    }

    if (result_answer[0] === result_answer[1] || result_answer[1] === result_answer[2] || result_answer[0] === result_answer[2]) {
        showMessage("数字が重複しないように入力してください。");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/game",
        data: {
            result_answer: result_answer,
            hidden_number: hidden_number,
            game_count: game_count,
            point: point
        },
        success: function (response) {
            let message = response.message;
            game_count = parseInt(game_count) + 1;

            $("#result_answer_" + game_count).text(result_answer);
            $("#result_content_" + game_count).text(response.result_content);
            $("#current_point").text("現在のポイント: " + response.point);
            $("#game_count").val(game_count);

            if (response.game_act_fig === 1) {
                $("#button").prop("disabled", true);
            }

            if (message) {
                showMessage(message);
            }

            $("#result_answer").val("");
        },
        error: function (error) {
            console.error("ゲーム実行失敗:", error);
        }
    });
}

function showMessage(message) {
    $("#message").text(message);
    var modal = new bootstrap.Modal(document.getElementById("gameCompleteModal"));
    modal.show();
}


window.onbeforeunload = function () {
    navigator.sendBeacon("/logout");
};