package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.FileDto;

@Mapper
public interface FileMapper {
	void insert(List<FileDto> list);
	
	List<FileDto> selectListByBoardSeq(int boardSeq);
	
	FileDto selectListBySeq(int seq);
}
