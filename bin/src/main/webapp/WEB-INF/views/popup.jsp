<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="modal fade" id="gameCompleteModal" tabindex="-1" aria-labelledby="gameCompleteLabel" aria-hidden="true">
      <div class="modal-dialog">
           <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="gameCompleteLabel">ポップアップ</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div id="message" class="modal-body">
					 ${message}
                </div>
                <div class="modal-footer">
                    <button id="modal_button" type="button" class="btn btn-secondary" data-bs-dismiss="modal">閉じる</button>
                </div>
           </div>
      </div>
</div>