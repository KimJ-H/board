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
$(document).ready(function(){
	   var idx=$("#idx").val();
	   var param="idx="+idx;
	   $.ajax({
	      type: "post",
	      url: "${path}/memo_servlet/comment_list.do",
	      data: param,
	      success: function(result){
	         $("#result").html(result);
	      }
	   });

	});

function replyMemo(){ //댓글
	document.form1.action="${path}/memo_servlet/reply.do";
	document.form1.submit();
	
}


function deleteMemo(){
	if(confirm("삭제하시겠습니까?")){ //confirm : 확인창
		document.form1.action="${path}/memo_servlet/delete.do";
		document.form1.submit();
	}
}

function updateMemo(){
	var writer=$("#writer").val(); //id=writer 값 가져오기
	var memo=$("#memo").val();
	var school=$("#school").val();
	if(writer==""){
		alert("이름을 입력하세요");
		$("#writer").focus();
		return;
	}
	if(memo==""){
		alert("메모를 입력하세요");
		$("#memo").focus();
		return;
	}
	if(school==""){
		alert("학교를 입력하세요");
		$("#school").focus();
		return;
	}
	document.form1.action="${path}/memo_servlet/update.do";
	document.form1.submit();
}

function insertComment(){
	var idx=$("#idx").val();
	var comment_writer=$("#comment_writer").val();
	var comment=$("#comment").val();
	if(comment_writer==""){
		alert("작성자를 입력하세요")
		$("#comment_writer").focus();
		return;
	}
	if(comment==""){
		alert("코멘트를 입력하세요")
		$("#comment").focus();
		return;
	}
	
	var param="idx="+idx+"&comment_writer="+comment_writer+"&comment="+comment;
	$.ajax({ //ajax : 같은 화면상에서 바로 처리한다.
		type: "post",
		url: "${path}/memo_servlet/comment_insert.do",
		data: param,
		success: function(){
			comment_list();
			$("#writer").val("");
			$("#memo").val("");
			$("#school").val("");
		}
	});
	
	function comment_list(){
		var param="idx="+idx;
		$.ajax({
			type: "post",
			url: "${path}/memo_servlet/comment_list.do",
			data: param,
			success: function(result){
				$("#result").html(result);
			}
		});
	}
}

</script>
</head>
<body>

<h2>메모 수정</h2>
<form name="form1" id="form1" method="post">
  <table border="1" width="550px">
    <tr>
      <td>이름</td>
      <td><input name="writer" id="writer" 
      value="${dto.writer}"></td>
    </tr>
    <tr>
      <td>메모</td>
      <td><input name="memo" id="memo" size="50" 
      value="${dto.memo}"></td>
    </tr>
    <tr>
      <td>학교</td>
      <td><input name="school" id="school" size="50" 
      value="${dto.school}"></td>
    <tr>
      <td colspan="2" align="center"> <!-- colspan : 열 병합 -->
        <input type="hidden" name="idx" id="idx" value=${dto.idx}>
        <input type="hidden" name="aaa" id="aaa" value="kkk">
        <input type="button" value="수정" onclick="updateMemo()">
        <input type="button" value="삭제" onclick="deleteMemo()">
        <input type="button" value="댓글" onclick="replyMemo()">
      </td>
    </tr>
  </table>


<table border="1" width="550px">
<tr><td><h2>Comment</h2></td></tr>
 <tr>
   <td>이름 : <input name="comment_writer" id="comment_writer" size="10"></td><br>
 </tr>
 <tr>
  <td>comment : <input name="comment" id="comment" size="40"></td>
 </tr>
 <tr>
   <td align="center" > 
   <input type="button" id="btnCommentSave" name="btnCommentSave" value="확인" onclick="insertComment()" > 
   </td>
 </tr>
</table>
</form>

<div id="result"></div>
<br>
</body>
</html>