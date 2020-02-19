<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="../include/jquery-3.4.1.min.js"></script>
</head>
<body>
<table border="1" width="500px">
  <tr>
    <th>번호</th>
    <th>이름</th>
    <th>학교</th>
    <th width="50%">메모</th>
    <th>날짜</th>
    
  </tr>
  <!-- var="개별값" items="리스트" -->
  <c:forEach var="row" items="${list}">
    <tr>
      <td>${row.idx}</td>
      <td>${row.writer}</td>
      <td>${row.school}</td>
      <td>
      <a href="${path}/memo_servlet/view.do?idx=${row.idx}">
      <!-- idx=${row.idx} Key값을 전달한다. -->
      
      ${row.memo}</a></td>
      <td>${row.post_date}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>