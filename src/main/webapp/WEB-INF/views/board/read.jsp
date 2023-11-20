<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<h1>/board/read.jsp</h1>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시판 본문</h3>
	</div>


	<!-- 수정/삭제할때 필요한  bno정보를 전달 -->
	<form method="post" role="form">
	   <input type="hidden" name="bno" value="${resultVO.bno }">
	</form>
	
	
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">글번호</label>
				<input type="text" name="writer" class="form-control" 
				 id="exampleInputEmail1"	 value="${resultVO.bno }" readonly>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">글쓴이</label>
				<input type="text" name="writer" class="form-control" 
				 id="exampleInputEmail1"	 value="${resultVO.writer }" readonly>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">제 목</label>
				<input type="text" name="title" class="form-control" 
				id="exampleInputPassword1" value="${resultVO.title }" readonly>
			</div>

			<div class="form-group">
				<label>내 용</label>
				<textarea class="form-control" rows="3" 
				name="content" readonly>${resultVO.content }</textarea>
			</div>


		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-success u">수정하기</button>
			<button type="submit" class="btn btn-danger d">삭제하기</button>
			<button type="submit" class="btn btn-info l">목록으로</button>
		</div>
	
</div>

<!-- Jquery 사용 -->
<!-- Jquery라이브러리 추가는 include/header.jsp 에 포함되어있음 -->
<script type="text/javascript">
	$(document).ready(function(){
		
		var formObj = $("form[role='form']");
		
		// 수정버튼 클릭시
		$(".btn-success").click(function(){
			// 수정페이지로 이동(기존의 정보확인) [GET]
			// 폼태그 action(/board/read) 속성을 /board/modify 으로 변경
			formObj.attr("action","/board/modify");
			formObj.attr("method","get");
			formObj.submit();			
		});
		
		// 삭제버튼 클릭시
		$(".btn-danger").click(function(){
			// 삭제페이지(처리)로 이동 [POST]
			// 폼태그 action(/board/read->/board/remove) 변경후 이동
			formObj.attr("action","/board/remove");
			formObj.submit();
		});
		
		$(".btn-info").click(function(){
			// 리스트 페이지로 이동
			//location.href='/board/listAll';
			location.href='/board/listPage';
		});	
		
		
	});
</script>



<%@ include file="../include/footer.jsp"%>