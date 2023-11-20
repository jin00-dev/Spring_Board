<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>/board/listCri.jsp</h1>

<h2>결과(result) : ${result }</h2>
<h2>결과(result) : ${param.result }</h2>

<%-- ${boardListAll } --%>

<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">아이티윌 게시판 목록(Cri)</h3>
	</div>

	<div class="box-body">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 45px">번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th style="width: 60px">조회수</th>
				</tr>
				
				<c:forEach var="vo" items="${boardList}">
					<tr>
						<td>${vo.bno }</td>
						<td>
							<a href="/board/read?bno=${vo.bno}">${vo.title }</a>
						</td>
						<td>${vo.writer }</td>
						<td>
						  <fmt:formatDate value="${vo.regdate }" pattern="yy-MM-dd"/> 
						</td>
						<td><span class="badge bg-orange">${vo.viewcnt }</span></td>
					</tr>
				</c:forEach>
				
				
			</tbody>
		</table>
	</div>

	<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
			<li><a href="#">«</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">»</a></li>
		</ul>
	</div>
</div>












<!-- result 값이 createOK일때  alert사용 '글쓰기완료!' -->

<script>
	var result = '${result}';
	console.log(result);

	if (result == 'createOK') {
		alert("글쓰기완료 !!!");
	}
</script>



<%@ include file="../include/footer.jsp"%>