package board.common;

import java.io.File;
import java.net.URLEncoder;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.FileDto;

@Component
public class FileUtils {
	
	public static List<FileDto> parseFileInfo(int boardSeq, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		if(ObjectUtils.isEmpty(multipartHttpServletRequest))
			return null;
		
		List<FileDto> fileList = new ArrayList<>();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd"); 
    	ZonedDateTime current = ZonedDateTime.now();
    	String path = "images/" + current.format(format);
    	
    	File file = new File(path);
		if(!file.exists())
			file.mkdirs();
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		String newFileName, originalFileExtension, contentType;
		
		while(iterator.hasNext()){
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			
			for (MultipartFile multipartFile : list){
				if(!multipartFile.isEmpty()){
					contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)){
						break;
					}else if(contentType.contains("image/jpeg")) {
						originalFileExtension = ".jpg";
					}else if(contentType.contains("image/png")) {
						originalFileExtension = ".png";
					}else if(contentType.contains("image/gif")) {
						originalFileExtension = ".gif";
					}else {
						break;
					}
					
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					FileDto boardFile = new FileDto();
					boardFile.setBoardSeq(boardSeq);
					boardFile.setSize(multipartFile.getSize());
					boardFile.setOriginalName(multipartFile.getOriginalFilename());
					boardFile.setPath(path + "/" + newFileName);
					fileList.add(boardFile);
					
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
	
	public static void download(HttpServletResponse response, String name, String path) throws Exception {
		byte[] files = org.apache.commons.io.FileUtils.readFileToByteArray(new File(path));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(files.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(name,"UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		response.getOutputStream().write(files);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}