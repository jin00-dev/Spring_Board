<%@ page pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<h1>/views/board/regist.jsp</h1>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시판 글쓰기</h3>
	</div>


	<form method="post" role="form" >
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">글쓴이</label>
				 <input type="text" name="writer" 
				        class="form-control" id="exampleInputEmail1" 
				        placeholder="작성자 이름을 쓰시오">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">제 목</label>
				 <input type="text" name="title" class="form-control" id="exampleInputPassword1" placeholder="제목을 작성하시오 ">
			</div>

			<div class="form-group">
				<label>내 용</label>
				<textarea class="form-control" rows="3" name="content"></textarea>
			</div>


		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-success bg-orange">글쓰기</button>
		</div>
	</form>
</div>

<%@ include file="../include/footer.jsp"%>

