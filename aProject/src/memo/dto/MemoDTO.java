package memo.dto;

//DTO : Data Transfer Object 데이타 전송 객체
public class MemoDTO {
	//메모장(화면)에 출력 할 변수 생성
	private int idx;
	private String writer;
	private String school;
	private String memo;
	private String post_date;
	private int ref;
	private int re_step;
	private int re_level;
	
	
	//getter, setter, toString(), 생성자
	public MemoDTO(int idx, String writer, String school, String memo, String post_date, int ref, int re_step,
			int re_level) {
		super();
		this.idx = idx;
		this.writer = writer;
		this.school = school;
		this.memo = memo;
		this.post_date = post_date;
		this.ref = ref;
		this.re_step = re_step;
		this.re_level = re_level;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	@Override
	public String toString() {
		return "MemoDTO [idx=" + idx + ", writer=" + writer + ", school=" + school + ", memo=" + memo + ", post_date="
				+ post_date + ", ref=" + ref + ", re_step=" + re_step + ", re_level=" + re_level + "]";
	}
	public MemoDTO(int idx, String writer, String school, String memo, String post_date) {
		super();
		this.idx = idx;
		this.writer = writer;
		this.school = school;
		this.memo = memo;
		this.post_date = post_date;
	}
	public MemoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	
	
}
