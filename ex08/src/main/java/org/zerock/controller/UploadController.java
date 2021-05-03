package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;



@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		String uploadFolder = "D:\\upload";
		for (MultipartFile file : uploadFile) {
			log.info("-------------------------------------");
			log.info("Upload file name : " + file.getOriginalFilename());
			log.info("Upload file size : " + file.getSize());
			System.out.println(file.getOriginalFilename());

			File saveFile = new File(uploadFolder,file.getOriginalFilename());
			
			try {
				file.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}

		}
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax(){
		log.info("upload ajax");
		System.out.println("upload ajax");
	}
	
	@PostMapping(value = "/uploadAjaxAction", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile){
		
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		String uploadFolder = "D:\\upload";
	
		log.info("upload ajax post....................");
		System.out.println("upload ajax post....................");
		
		String uploadFolderPath = getFolder();
		
		// make folder
		File uploadPath = new File(uploadFolder,getFolder());
		log.info("upload path" + uploadPath);
		System.out.println(uploadPath);
		
		if(uploadPath.exists()==false){
			uploadPath.mkdirs();
		}
		// end maker folder
		
		for(MultipartFile m : uploadFile){
			AttachFileDTO attachFileDTO = new AttachFileDTO();
			String uploadFileName = m.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			attachFileDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			
			System.out.println(uploadFileName);
			
			File saveFile = new File(uploadPath,uploadFileName);
			
			try {
				m.transferTo(saveFile);
				
				attachFileDTO.setUuid(uuid.toString());
				attachFileDTO.setUploadPath(uploadFolderPath);
				
				if(checkImageType(saveFile)){
					attachFileDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					Thumbnailator.createThumbnail(m.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				list.add(attachFileDTO);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return new ResponseEntity<List<AttachFileDTO>>(list,HttpStatus.OK);
	}
	
	
	private String getFolder(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
	private boolean checkImageType(File file){
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
