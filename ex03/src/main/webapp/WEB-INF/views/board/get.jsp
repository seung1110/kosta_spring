<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="../includes/header.jsp" %>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Register</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Read</div>
				<div class="panel-body">
					
						<div class="form-group">
							<label>Bno</label> <input class="form-control" name="bno"
							value="<c:out value='${board.bno}'/>" readonly="readonly">
						</div>
						<div class="form-group">
							<label>Title</label> <input class="form-control" name="title" 
								value="<c:out value='${board.title}'/>" readonly="readonly">
						</div>
						<div class="form-group">
							<label>TextArea</label> 
							<textarea class="form-control" row="3" name="content" readonly="readonly"><c:out value='${board.content}'/></textarea>
						</div>
						
						<div class="form-group">
							<label>Writer</label> <input class="form-control" name ="writer"
							value="<c:out value='${board.writer}'/>" readonly="readonly"/>
						</div>
						<button data-oper="modify" class="btn btn-default">Modify</button>
						<button data-oper="list" class="btn btn-info">List</button>
						
						<form id='operForm' action="/board/modify" method="get">
							<input type="hidden" id='bno' name='bno' value="<c:out value='${board.bno}'/>">
						</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			
			var operForm = $("#operForm");
			
			$("button[data-oper='modify']").on("click",function(e){
				operForm.attr("action","/board/modify").submit();
			});
			
			$("button[data-oper='list']").on("click",function(){
				operForm.find("#bno").remove();
				operForm.attr("action","/board/list");
				operForm.submit();
			});
		});
	</script>
	<%@include file="../includes/footer.jsp" %>
</body>
</html>