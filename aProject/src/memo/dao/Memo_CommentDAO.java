package memo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import memo.dto.MemoDTO;
import memo.dto.Memo_CommentDTO;
import sqlmap.MybatisManager;

public class Memo_CommentDAO {
	
	public void insertComment(Memo_CommentDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		//session은 namespace(memo3.xml)에서 찾는다
		
		//공백, <, >을 사용했을때 오류현상을 막는다
		  String writer=dto.getWriter(); 
		  writer=writer.replace(" ", "&nbsp;");
		  String memo3_comment=dto.getMemo3_comment(); 
		  memo3_comment=memo3_comment.replace(" ", "&nbsp");
		  
		  //태그 문자 처리 //String memo=dto.getMemo(); 
		  memo3_comment=memo3_comment.replace("<", "&lt;");//Less
		  //Than ~보다 작다 
		  memo3_comment=memo3_comment.replace(">", "&gt;");//Greater Than ~보다 크다
		  dto.setMemo3_comment(memo3_comment);
		 
		  
		session.insert("memo3_comment.insert", dto);
		session.commit();
		session.close();
	}
	
	public List<Memo_CommentDTO> list_CommentMemo(int idx) { //listMemo 메소드
		SqlSession session = MybatisManager.getInstance().openSession();
		List<Memo_CommentDTO> list = null;
		try {
			list = session.selectList("memo3_comment.list",idx);
		} catch (Exception e) {
			e.printStackTrace(); //예외 정보 출력
		} finally {
		}
		return list;
	} 

}
