<%@ page session="false" pageEncoding="UTF-8"%>

<!-- http://localhost:8088/controller/ -->
<%@ include file="include/header.jsp" %>

<h1>
	Hello world!  아이티윌 (home.jsp) ${pageContext.request.contextPath }
</h1>

<P>  The time on the server is ${serverTime}. </P>

<hr>

<input type="button" value="버튼" class="btn btn-block btn-danger bg-purple">

<button type="button" class="btn btn-block btn-success">Success</button>

<div class="btn-group">
<button type="button" class="btn btn-danger">Action</button>
<button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
<span class="caret"></span>
<span class="sr-only">Toggle Dropdown</span>
</button>
<ul class="dropdown-menu" role="menu">
<li><a href="#">Action</a></li>
<li><a href="#">Another action</a></li>
<li><a href="#">Something else here</a></li>
<li class="divider"></li>
<li><a href="#">Separated link</a></li>
</ul>
</div>

<hr><hr><hr>

제목 : <input type="text" name="title" id="title">

<button type="button" class="btn btn-block btn-success" 
        id="btnCreate">글쓰기(REST)</button>

<hr><hr><hr>

<div id="readContent"></div>

<!-- 17901 글 번호 조회 -->
<button type="button" class="btn btn-block btn-success" 
        id="btnRead">글조회 - 본문보기(REST)</button>

<hr><hr><hr>
<!-- 글 수정 -->
<button type="button" class="btn btn-block btn-success" 
        id="btnUpdate">글수정하기(REST)</button>

<hr><hr><hr>
<!-- 글 삭제 -->
<button type="button" class="btn btn-block btn-success" 
        id="btnDelete">글삭제하기(REST)</button>

<hr><hr><hr>
<!-- 글 목록 조회 -->
<button type="button" class="btn btn-block btn-success" 
        id="btnList">글 목록 조회(REST)</button>

<hr><hr><hr>
<div id="result"></div>


<script type="text/javascript">
	$(document).ready(function(){
		
		
		// 글쓰기 버튼 클릭
		$("#btnCreate").click(function(){
			alert('글쓰기 클릭!');
			
		var boardObj = {
				title : $("#title").val(),
				writer : "ajax 이름",
				content : "ajax 내용"
		};
			
			$.ajax({
				type : "post",
				url : "${contextPath}/boards",
				data : JSON.stringify(boardObj),
				contentType : "application/json",
				success : function(data){
					if(data == "CREATE_OK"){
					alert("성공(REST컨트롤러 갔다 옴)");
					}
				}//success
			});///ajax
		});//click
		
		//글 내용 보기 
		$("#btnRead").click(function(){
			// var bno = ${dto.bno}; 가능 
			var bno = 17903;
			$.ajax({
				type : "GET",
				url : "${contextPath}/boards/"+bno,
				success : function(data){
					alert('read 성공 @@@');
 					//alert(data);
					console.log(data);					
					
					$("#readContent").append("bno : "+data.bno+ "<br>"
											 + "title : " + data.title + "<br>"
											 + "regdate : " + new Date(data.regdate)+ "<br>...");
				}//success
			});//ajax
		});//click
		
		//글 수정하기 
		$("#btnUpdate").click(function(){
			//JSON 데이터 (수정 데이터)
			var updateObj = {
				bno : 17903,
				title : "ajax 수정1",
				writer : "ajax 이름 수정1",
				content : "ajax 내용 수정1"
			};
			$.ajax({
				url : "/boards/17903",
				type : "put",
				data : JSON.stringify(updateObj),
				contentType : "application/json",
				success : function(){
					alert("성공");
				}
			});
		});//click
		
		$("#btnDelete").click(function(){
			$.ajax({
				url : "/boards/17900",
				type : "delete",
				success : function(data){
					if(data == "deleteOK"){
						alert("삭제 성공");
					}else{
						alert("삭제 실패");
					}
				}
			});
			
			
		});//click
		
		$("#btnList").click(function(){
			
			getList();
			
		});//list
		
		function getList(){
			$.ajax({
				url : "/boards/list",
				type : "get",
				success : function(data){
					alert("목록조회 성공");
					$(data).each(function(idx,item){
						console.log(idx);
						console.log(item);
						$("#result").append(item.bno+"/"+item.writer+"/"+ item.content +"<hr>" );
					});
				}
			});
		}
	});//ready
</script>












<%@ include file="include/footer.jsp" %>