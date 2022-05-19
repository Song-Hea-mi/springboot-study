package board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import board.dto.BoardDto;
import board.mapper.BoardMapper;
import board.mapper.FileMapper;
import board.service.BoardService;

@RestController
public class BoardApiController {

	private final BoardMapper boardMapper;
	private final FileMapper fileMapper;
	
	public BoardApiController(BoardMapper boardMapper, FileMapper fileMapper) {
		this.boardMapper = boardMapper;
		this.fileMapper = fileMapper;
	}
	
	@RequestMapping(value="/api/board", method=RequestMethod.GET)
	public List<BoardDto> boardGet() throws Exception{
		return boardMapper.selectList();
	}
	
	@RequestMapping(value="/api/board/write", method=RequestMethod.POST)
	public void boardWritePost(@RequestBody BoardDto board) throws Exception{
		boardMapper.insert(board);
	}

	@RequestMapping(value="/api/board/{seq}", method=RequestMethod.GET)
	public BoardDto boardSeqGet(@PathVariable("seq") int seq) throws Exception{
		BoardService boardService = new BoardService(boardMapper, fileMapper);
		
		return boardService.detail(seq);
	}
	
	@RequestMapping(value="/api/board/{seq}", method=RequestMethod.POST)
	public String boardSeqPost(@RequestBody BoardDto board) throws Exception{
		boardMapper.update(board);
		return "redirect:/board";
	}
	
	@RequestMapping("/api/board/delete")
	public String boardDelete(@RequestParam int seq) throws Exception{
		boardMapper.delete(seq);
		return "redirect:/board";//통과
	}
}
