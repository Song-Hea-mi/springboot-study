package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectList();
	
	BoardDto selectBySeq(int seq);
	
	void insert(BoardDto dto);

	void update(BoardDto dto);
	
	void delete(int seq);
	
	void updateHitCount(int seq);
}
