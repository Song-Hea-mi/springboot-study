package board.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final BoardMapper boardMapper;
	private final FileMapper fileMapper;
	
	public BoardController(BoardMapper boardMapper, FileMapper fileMapper) {
		this.boardMapper = boardMapper;
		this.fileMapper = fileMapper;
	}
	
	@RequestMapping("/board/list.do")
	public ModelAndView list() throws Exception{
		log.info("/board/list");
		ModelAndView mv = new ModelAndView("/board/list");
		mv.addObject("list", boardMapper.selectList());
		return mv;
	}
	
	@RequestMapping("/board/write.do")
	public String write() throws Exception{
		return "/board/write";
	}
	
	@RequestMapping("/board/insert.do")
	public String insert(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		BoardService boardService = new BoardService(boardMapper, fileMapper);
		boardService.insert(board, multipartHttpServletRequest);
		
		return "redirect:/board/list.do";
	}

	@RequestMapping("/board/detail.do")
	public ModelAndView detail(@RequestParam int seq) throws Exception{
		ModelAndView mv = new ModelAndView("/board/detail");
		BoardService boardService = new BoardService(boardMapper, fileMapper);
		mv.addObject("board", boardService.detail(seq));
		
		return mv;
	}
	
	@RequestMapping("/board/update.do")
	public String update(BoardDto board) throws Exception{
		boardMapper.update(board);
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("/board/delete.do")
	public String delete(int seq) throws Exception{
		boardMapper.delete(seq);
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("/board/download/file.do")
	public void downloadFile(@RequestParam int seq, HttpServletResponse response) throws Exception{
		FileDto file = fileMapper.selectListBySeq(seq);

		if(!ObjectUtils.isEmpty(file))
			FileUtils.download(response, file.getOriginalName(), file.getPath());
	}
}
