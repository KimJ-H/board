package memo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.dao.MemoDAO;
import memo.dao.Memo_CommentDAO;
import memo.dto.MemoDTO;
import memo.dto.Memo_CommentDTO;



@WebServlet("/memo_servlet/*")
//servlet이란?
//서버에서 웹페이지 등을 동적으로 생성하거나,
//데이터 처리를 수행하기 위해 자바(java)로 작성된 프로그램을 말한다.

//자바 서블릿은 서버측 기능을 확장시킨 자바프로그램으로, 자바EE의 한기능이다.
//서블릿 실행환경은 웹 컨테이너 혹은 서블릿 컨테이너로 불린다.
//웹 컨테이너로는 Apache Tomcat 등이 있다.

public class MemoController extends HttpServlet { //class name : MemoController (부모클래스)
	                                              //HttpServlet (자식클래스)
	private static final long serialVersionUID = 1L;
	
	
	//Get : URL을 통한 Request
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws
	ServletException, IOException {
		//사용자가 요청한 주소
		String uri = request.getRequestURI();
		MemoDAO dao = new MemoDAO();
		Memo_CommentDAO memo_dao = new Memo_CommentDAO();
		
		if(uri.indexOf("comment_list.do") != -1) {
			//사용자가 입력한 내용을 dto에 저장
				docomment_List(request,response, memo_dao);
		}
		
		else if(uri.indexOf("list.do") != -1) {
			//사용자가 입력한 내용을 dto에 저장
				doList(request,response,dao);
		}
		else if (uri.indexOf("view.do") != -1) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			//request.getParameter => String값을 불러온다
			//Integer.parseInt => String값을 int값을로 바꿔준다
			
			// ex)
			// int + String => String
			// int a =3; String s ="4"; String k = a+s; //34
			// int a =3; 3+""; "3"로 변환  System.out.println(3+"");
			
			System.out.println("Memo table key값:"+idx);
			MemoDTO dto = dao.viewMemo(idx);
			request.setAttribute("dto", dto);
			String page = "/memo/memo_view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(uri.indexOf("update.do") != -1) {
			int idx=Integer.parseInt(request.getParameter("idx"));
			String writer=request.getParameter("writer");
			String memo=request.getParameter("memo");
			String school=request.getParameter("school");
			MemoDTO dto=new MemoDTO();
			dto.setIdx(idx);
			dto.setWriter(writer);
			dto.setMemo(memo);
			dto.setSchool(school);
			dao.updateMemo(dto);//레코드 수정
			response.sendRedirect(
					request.getContextPath()+"/memo/memo.jsp");
		
		}else if(uri.indexOf("comment_insert.do") != -1) {
			//사용자가 입력한 내용을 dto에 저장
			int idx=Integer.parseInt(request.getParameter("idx"));
			String writer=request.getParameter("comment_writer");
			String comment=request.getParameter("comment");
			Memo_CommentDTO dto=new Memo_CommentDTO();
			
			//대소문자 구분 확실히!!!!!!!!!!!!
			dto.setWriter(writer);
			dto.setMemo3_comment(comment);
			dto.setMemo3_idx(idx);
			//dao에 레코드 저장 요청
			memo_dao.insertComment(dto);
		} 
		else if(uri.indexOf("insert.do") != -1) {
			//사용자가 입력한 내용을 dto에 저장
			String writer=request.getParameter("writer");
			String memo=request.getParameter("memo");
			String school=request.getParameter("school");
			MemoDTO dto=new MemoDTO();
			dto.setWriter(writer);
			dto.setMemo(memo);
			dto.setSchool(school);
			//dao에 레코드 저장 요청
			dao.insertMemo(dto);
			
		}else if(uri.indexOf("delete.do") != -1) {
			int idx=Integer.parseInt(request.getParameter("idx"));
			dao.deleteMemo(idx);//레코드 삭제
			//페이지 이동
			response.sendRedirect(
					request.getContextPath()+"/memo/memo.jsp");
		
		}else if(uri.indexOf("reply.do") != -1) {
			int idx=Integer.parseInt(request.getParameter("idx")); //idx값을 들고온다
			System.out.println("Memo table key값:"+idx);
			MemoDTO dto = dao.viewMemo(idx);
			String memo = dto.getMemo(); //memo값을 가져온다
			String memostr = "RE:"+memo; //댓글 : RE를 붙혀준다
			dto.setMemo(memostr);
			request.setAttribute("dto", dto);
			String page = "/memo/reply.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		else if(uri.indexOf("insertReply.do") != -1) {
			int idx=Integer.parseInt(request.getParameter("idx")); //idx값을 들고온다
			String writer=request.getParameter("writer");
			String memo=request.getParameter("memo");
			String school=request.getParameter("school");
			System.out.println("Memo table key값:"+idx);
			MemoDTO dto = dao.viewMemo(idx);
			int ref = dto.getRef();
			int re_step = dto.getRe_step();
			int re_level = dto.getRe_level();
			re_step = re_step+1;
			re_level = re_level+1;
			
			dao.updateStep(ref, re_step);
			dto.setWriter(writer);
			dto.setMemo(memo);
			dto.setSchool(school);
			dto.setRe_step(re_step);
			dto.setRe_level(re_level);
			dao.insert_reply(dto);
			String page = "/memo/memo.jsp";
			response.sendRedirect(request.getContextPath()+page);
		}
		
		
	} //doGet end
	
	
	private void doList(HttpServletRequest request, HttpServletResponse response, MemoDAO dao) throws ServletException, IOException {
		int count=dao.totCnt();//총 갯수 계산
		int curPage=1;//null일때는 기본값 1을 줌
		if(request.getParameter("curPage") != null) {
			curPage=
			Integer.parseInt(request.getParameter("curPage"));
		}
		
		//Pager setting
		System.out.println("::"+count+"::"+curPage);
		Pager pager=new Pager(count,curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		List<MemoDTO> list=dao.memoList(start,end);
		request.setAttribute("list", list);
		//페이지 네비게이션에 필요한 정보 전달
		request.setAttribute("page", pager);
		String page="/memo/list.jsp";
		RequestDispatcher rd=request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	
	
	
	
	private void docomment_List(HttpServletRequest request, HttpServletResponse response, Memo_CommentDAO dao) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		System.out.println("IDX "+ idx);
		List<Memo_CommentDTO> list=dao.list_CommentMemo(idx);//메모 목록 리턴
		request.setAttribute("list", list);
		String page = "/memo/comment_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		
		rd.forward(request, response);
		
	} 
	//Post : <form>을 통한 Request
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	} //doPost end
	

}
