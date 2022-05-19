package board.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.common.FileUtils;
import board.dto.BoardDto;
import board.dto.FileDto;
import board.mapper.BoardMapper;
import board.mapper.FileMapper;
import board.service.BoardService;

@Controller
public class BoardController {

	private final BoardMapper boardMapper;
	private final FileMapper fileMapper;
	
	public BoardController(BoardMapper boardMapper, FileMapper fileMapper) {
		this.boardMapper = boardMapper;
		this.fileMapper = fileMapper;
	}
	
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ModelAndView boardGet() throws Exception{
		ModelAndView mv = new ModelAndView("/board/restBoardList");
		mv.addObject("list", boardMapper.selectList());
		return mv;
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public String boardWriteGet() throws Exception{
		return "/board/restBoardWrite";
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String boardWritePost(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		BoardService boardService = new BoardService(boardMapper, fileMapper);
		boardService.insert(board, multipartHttpServletRequest);
		
		return "redirect:/board";
	}

	@RequestMapping(value="/board/{seq}", method=RequestMethod.GET)
	public ModelAndView boardSeqGet(@PathVariable("seq") int seq) throws Exception{
		ModelAndView mv = new ModelAndView("/board/restBoardDetail");
		BoardService boardService = new BoardService(boardMapper, fileMapper);
		mv.addObject("board", boardService.detail(seq));
		
		return mv;
	}
	
	@RequestMapping(value="/board/{seq}", method=RequestMethod.POST)
	public String boardSeqPost(BoardDto board) throws Exception{
		boardMapper.update(board);
		return "redirect:/board";
	}
	
	@RequestMapping("/board/delete")
	public String boardDelete(int seq) throws Exception{
		boardMapper.delete(seq);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/board/file", method=RequestMethod.GET)
	public void downloadFile(@RequestParam int seq, HttpServletResponse response) throws Exception{
		FileDto file = fileMapper.selectListBySeq(seq);

		if(!ObjectUtils.isEmpty(file))
			FileUtils.download(response, file.getOriginalName(), file.getPath());
	}
}
