<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="../include/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(function(){
   list('1');
   $("#btnSave").click(function(){
      insert();
   });
   //검색 버튼 클릭
   $("#btnSearch").click(function(){
      list('1');
   });
});


//화면상에서 확인키눌렀을때
function insert(){
	var writer=$("#writer").val();
	var memo=$("#memo").val();
	var school=$('#school').val();
	var param="writer="+writer+"&memo="+memo+"&school="+school;
	$.ajax({ //ajax : 같은 화면상에서 바로 처리한다.
		type: "post",
		url: "${path}/memo_servlet/insert.do",
		data: param,
		success: function(){
			list('1');
			$("#writer").val("");
			$("#memo").val("");
			$("#school").val("");
		}
	});
}
function list(curPage){
	var param="curPage="+curPage;
	$.ajax({
		type: "post",
		url: "${path}/memo_servlet/list.do",
		data: param,
		success: function(result){
			$("#result").html(result);
		}
	});
}
 
</script>
</head>
<body>

<h2>한줄메모장</h2>
<h4>여러분의 의견을 적어주세요~!</h4>

이름 : <input id="writer" size="10"><br>
메모 : <input id="memo" size="40"><br>
학교 : <input id="school" size="50">
<input type="button" id="btnSave" value="확인">
<br>
<select id="searchkey">
  <option value="writer">이름</option>
  <option value="memo">메모</option>
  <option value="writer_memo">이름+메모</option>
</select>
<input id="search" value="${search}">
<input type="button" id="btnSearch" value="조회">

<div id="result"></div>

</body>
</html>