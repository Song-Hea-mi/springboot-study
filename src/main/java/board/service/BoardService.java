package board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.common.FileUtils;
import board.dto.BoardDto;
import board.dto.FileDto;
import board.mapper.BoardMapper;
import board.mapper.FileMapper;

@Service
public class BoardService {

	private final BoardMapper boardMapper;
	private final FileMapper fileMapper;
	
	public BoardService(BoardMapper boardMapper, FileMapper fileMapper) {
		this.boardMapper = boardMapper;
		this.fileMapper = fileMapper;
	}
	
	public BoardDto detail(int seq) {
		BoardDto board = boardMapper.selectBySeq(seq);
		List<FileDto> listFile = fileMapper.selectListByBoardSeq(seq);
		board.setListFile(listFile);
		
		boardMapper.updateHitCount(seq);
		
		return board;
	}
	
	public void insert(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insert(board);
		List<FileDto> list = FileUtils.parseFileInfo(board.getSeq(), multipartHttpServletRequest);
		if(!CollectionUtils.isEmpty(list))
			fileMapper.insert(list);
	}
}
