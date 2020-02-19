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
	$("#btnSave").click(function(){
		document.form1.submit();
	});
});

</script>
</head>
<body>
<h2>댓글쓰기</h2>
<form name="form1" method="post" 
action="${path}/memo_servlet/insertReply.do">
<table border="1" width="700px">
  <tr>
    <td>이름</td>
    <td><input name="writer" id="writer"></td>
  </tr>
  <tr>
    <td>메모</td>
    <td><input name="memo" id="memo" 
      value="${dto.memo}" size="50"></td>
      <!-- value="Re: ${dto.memo}" size="50"></td> -->
  </tr>
  <tr>
    <td>학교</td>
    <td><input name="school" id="school" size="50" 
      value="${dto.school}"></td>
   
  </tr>

  <tr>
    <td colspan="2" align="center">
    <!-- 게시물 번호 -->
      <input type="hidden" name="idx" value="${dto.idx}">
      <input type="button" value="확인" id="btnSave">
    </td>
  </tr>
</table>
</form>
</body>
</html>