package board.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class FileDto {
	private int seq;
	private int boardSeq;
	private String originalName;
	private String path;
	private long size;
	private Date created;
	private String createId;
	private Date updated;
	private String updateId;
	private String useYn;

	private long kbSize;
}
