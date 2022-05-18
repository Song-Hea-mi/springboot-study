package board.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
	private int seq;
	private String title;
	private String contents;
	private int hitCnt;
	private Date created;
	private String createId;
	private Date updated;
	private String updateId;
	private String useYn;
	
	List<FileDto> listFile;
}
