<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>/board/listPage.jsp</h1>

<h2>결과(result) : ${result }</h2>
<h2>결과(result) : ${param.result }</h2>

${pageVO }
<%-- ${boardListAll } --%>

<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">아이티윌 게시판 목록(Page)</h3>
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
		    
		    <c:if test="${pageVO.prev }">
				<li><a href="/board/listPage?page=${pageVO.startPage - 1 }">«</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
				<li ${pageVO.cri.page == i? 'class="active"':'' }>
					<a href="/board/listPage?page=${i }">${i }</a>
				</li>
			</c:forEach>
			
			<c:if test="${pageVO.next }">
				<li><a href="/board/listPage?page=${pageVO.endPage + 1 }">»</a></li>
			</c:if>
			
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