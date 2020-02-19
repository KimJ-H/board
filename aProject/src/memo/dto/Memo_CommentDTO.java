package memo.dto;

public class Memo_CommentDTO {
	
	private int idx;
	private int memo3_idx;
	private String writer;
	private String memo3_comment;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getMemo3_idx() {
		return memo3_idx;
	}
	public void setMemo3_idx(int memo3_idx) {
		this.memo3_idx = memo3_idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMemo3_comment() {
		return memo3_comment;
	}
	public void setMemo3_comment(String memo3_comment) {
		this.memo3_comment = memo3_comment;
	}
	@Override
	public String toString() {
		return "Memo_CommentDTO [idx=" + idx + ", memo3_idx=" + memo3_idx + ", writer=" + writer + ", memo3_comment="
				+ memo3_comment + "]";
	}
	public Memo_CommentDTO(int idx, int memo3_idx, String writer, String memo3_comment) {
		super();
		this.idx = idx;
		this.memo3_idx = memo3_idx;
		this.writer = writer;
		this.memo3_comment = memo3_comment;
	}
	public Memo_CommentDTO() {
		
	}
	
	
	
}
