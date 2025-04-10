/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.openMalls
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: BoardWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240619152641][pluto#plutozone.com][CREATE: Initial Release]
 *				: [20240808152100][pluto#plutozone.com][UPDATE: 파일 업로드 라이브러리 변경(Apache > Spring Multipart)]
 */
package com.plutozone.backoffice.center.controller;

import java.io.File;
import java.util.Hashtable;
// import java.util.Iterator;
// import java.util.List;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import org.apache.commons.fileupload.DiskFileUpload;
// import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.plutozone.backoffice.center.dto.BoardDto;
import com.plutozone.backoffice.center.service.BoardSrvc;
import com.plutozone.backoffice.common.Common;
import com.plutozone.backoffice.common.dto.PagingDto;
import com.plutozone.backoffice.common.dto.PagingListDto;
import com.plutozone.common.dto.FileDownloadDto;
import com.plutozone.common.dto.FileDto;
import com.plutozone.common.dto.FileUploadDto;
import com.plutozone.common.file.FileUpload;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-06-19
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.backoffice.center.controller.BoardWeb")
public class BoardWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BoardWeb.class);
	
	@Autowired
	private MessageSourceAccessor dynamicProperties;
	
	@Inject
	BoardSrvc boardSrvc;
	
	/**
	 * @param type
	 * @param sequence
	 * @param model
	 * @return ModelAndView
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 파일 다운로드</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/console/center/board/download.web", method = RequestMethod.POST)
	public ModelAndView download(String type, long sequence, Model model) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			BoardDto boardDto = new BoardDto();
			
			FileDownloadDto fileDownloadDto = new FileDownloadDto();
			File file = null;
			
			// [2018-11-05][pluto#plutozone.com][TODO-확장: 타입이 정의되어 있지 않을 경우 처리]
			if (type.equals("BbsNotice")) boardDto.setCd_bbs_type(1);
			else if (type.equals("BbsQuestion")) boardDto.setCd_bbs_type(3);
			
			boardDto.setSeq_bbs((int)sequence);
			
			boardDto	= boardSrvc.select(boardDto);
			boardDto.setFile_orig(boardDto.getFile_orig());
			boardDto.setFile_save(boardDto.getFile_save());
			
			String pathBase		= dynamicProperties.getMessage("backoffice.upload.path", "[UNDEFINED]");
			
			file = new File(pathBase + "" + File.separator + boardDto.getFile_save());
			
			fileDownloadDto.setFile_original(boardDto.getFile_orig());
			fileDownloadDto.setFile_size(file.length());
			
			if (file == null || file.exists() == false ) {
				mav.setViewName("redirect:/error.web?code=404");
				
				return mav;
			}
			else {
				model.addAttribute("file", file);
				model.addAttribute("filename", fileDownloadDto.getFile_original());
				model.addAttribute("filesize", fileDownloadDto.getFile_size());
				
				mav.setViewName("fileDownloadView");
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".download()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-07-30
	 * <p>DESCRIPTION: 고객센터 삭제</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/console/center/board/remove.web")
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			boardDto.setUpdater(Integer.parseInt(getSession(request, "SEQ_MNG")));
			
			if (boardSrvc.deleteFlag(boardDto)) {
				request.setAttribute("script"	, "alert('삭제되었습니다.');");
				request.setAttribute("redirect"	, "/console/center/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());
			}
			else {
				request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
				request.setAttribute("redirect"	, "/");
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".remove()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-07-30
	 * <p>DESCRIPTION: 고객센터 수정 처리</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/console/center/board/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			boardDto.setUpdater(Integer.parseInt(getSession(request, "SEQ_MNG")));
			
			if (boardSrvc.update(boardDto)) {
				request.setAttribute("script"	, "alert('수정되었습니다.');");
				request.setAttribute("redirect"	, "/console/center/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());
			}
			else {
				request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
				request.setAttribute("redirect"	, "/");
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-07-30
	 * <p>DESCRIPTION: 고객센터 수정 폼</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/console/center/board/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			BoardDto _boardDto = boardSrvc.select(boardDto);
			
			mav.addObject("boardDto", _boardDto);
			
			if (boardDto.getCd_bbs_type() == 1) {
				mav.setViewName("backoffice/center/board/notice/modifyForm");
			}
			else if (boardDto.getCd_bbs_type() == 2) {
				mav.setViewName("backoffice/center/board/faq/modifyForm");
			}
			else if (boardDto.getCd_bbs_type() == 3) {
				
				BoardDto boardQuestionDto = boardSrvc.selectQuestion(_boardDto);
				
				mav.addObject("boardQuestionDto", boardQuestionDto);
				mav.setViewName("backoffice/center/board/question/modifyForm");
			}
			else {
				request.setAttribute("redirect"	, "/");
				mav.setViewName("forward:/servlet/result.web");
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-07-30
	 * <p>DESCRIPTION: 고객센터 보기</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/console/center/board/view.web")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			BoardDto _boardDto = boardSrvc.select(boardDto);
			
			// 조회수 증가 실패 시
			if (_boardDto == null ) {
				request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
				request.setAttribute("redirect"	, "/");
				mav.setViewName("forward:/servlet/result.web");
			}
			else {
				mav.addObject("boardDto", _boardDto);
				
				if (boardDto.getCd_bbs_type() == 1) {
					mav.setViewName("backoffice/center/board/notice/view");
				}
				else if (boardDto.getCd_bbs_type() == 2) {
					mav.setViewName("backoffice/center/board/faq/view");
				}
				else if (boardDto.getCd_bbs_type() == 3) {
					
					// DB 부하 감소를 위해 답변이 있을 때만
					if (_boardDto.getSeq_reply() > 0) {
						BoardDto boardReplyDto = boardSrvc.selectReply(boardDto);
						mav.addObject("boardReplyDto", boardReplyDto);
					}
					
					mav.setViewName("backoffice/center/board/question/view");
				}
				else {
					request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
					request.setAttribute("redirect"	, "/");
					mav.setViewName("forward:/servlet/result.web");
				}
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @param fileUploadDto [파일 업로드 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-07-30
	 * <p>DESCRIPTION: 고객센터 쓰기 처리</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@RequestMapping(value = "/console/center/board/writeProc.web")
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto, FileUploadDto fileUploadDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		String message	= "";
		
		try {
			
			boardDto.setRegister(Integer.parseInt(getSession(request, "SEQ_MNG")));
			
			// **************************
			// For Board File
			// **************************
			String pathBase		= dynamicProperties.getMessage("backoffice.upload.path", "[UNDEFINED]") + "/bbs/";
			String maxSize		= dynamicProperties.getMessage("backoffice.upload.file.max10MB"			, "[UNDEFINED]");
			String allowedExt	= dynamicProperties.getMessage("backoffice.upload.file.extension.doc"	, "[UNDEFINED]");
			
			int countFile = 0;
			if (null != fileUploadDto.getFiles()) countFile = fileUploadDto.getFiles().size();
			
			FileDto[] fileDto = new FileDto[countFile];
			LinkedList<Object> uploadResult = FileUpload.upload(fileUploadDto, pathBase, maxSize, allowedExt, countFile);
			
			message	= (String)((Hashtable)uploadResult.getLast()).get("resultID");
			
			if (message.equals("success")) {
				
				Hashtable<String, String> hashtable	= (Hashtable<String, String>)uploadResult.getLast();
				
				String fileNameSrc	= "";
				String fileNameSve	= "";
				String fileSize		= "";
				long totalSize		= 0;
				
				for (int loop = 0; loop < countFile; loop++) {
					fileNameSrc		= (String)hashtable.get("files[" + loop + "]_fileSrcName");
					fileNameSve		= (String)hashtable.get("files[" + loop + "]_fileSveNameRelative");
					fileSize		= (String)hashtable.get("files[" + loop + "]_fileSveSize");
					if (fileSize == null || fileSize == "") fileSize = "0";
					
					fileDto[loop] = new FileDto();
					fileDto[loop].setFileNameOriginal(fileNameSrc);
					fileDto[loop].setFileNameSave(fileNameSve);
					fileDto[loop].setFileSize((Long.parseLong(fileSize)));
					
					totalSize += Long.parseLong(fileSize);
				}
				
				/*
				if (totalSize > 0) {
					boardSrvc.insert(boardDto, boardFileDto);
				}
				else {
					boardSrvc.insert(boardDto);
				}
				*/
				
				boardDto.setFile_orig(fileNameSrc);
				boardDto.setFile_save("bbs\\" + fileNameSve);
				
				if (boardDto.getCd_bbs_type() == 3) {
					// 답변글(상위 일련번호에 문의글 번호를 저장)
					boardDto.setSeq_bbs_parent(boardDto.getSeq_bbs());
					boardDto.setUpdater(Integer.parseInt(getSession(request, "SEQ_MNG")));
				}
				
				if (boardSrvc.insert(boardDto)) {
					if (boardDto.getCd_bbs_type() == 3) {
						request.setAttribute("script"	, "alert('답변이 등록되었습니다.');");
					}
					else {
						request.setAttribute("script"	, "alert('등록되었습니다.');");
					}
					request.setAttribute("redirect"	, "/console/center/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());
				}
				else {
					request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
					request.setAttribute("redirect"	, "/");
				}
			}
			else {
				request.setAttribute("script"	, "alert('" + message + "');");
				request.setAttribute("redirect"	, "/");
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/*
	@SuppressWarnings({"rawtypes"})
	@RequestMapping(value = "/console/center/board/writeProc.web")
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response) {
	// public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			BoardDto boardDto = new BoardDto();
			
			String path = "D:\\Temp";
			
			DiskFileUpload upload = new DiskFileUpload();
			
			upload.setSizeMax(1000000);
			upload.setSizeThreshold(4096);
			upload.setRepositoryPath(path);
			
			List items = upload.parseRequest(request);
			Iterator params = items.iterator();
			
			while (params.hasNext()) {
				
				FileItem item = (FileItem) params.next();
				
				if (item.isFormField()) {
					String name		= item.getFieldName();
					String value	= item.getString("utf-8");
					
					//logger.debug(name + "=" + value);
					
					if (name.equals("cd_bbs_type")) boardDto.setCd_bbs_type(Integer.parseInt(value));
					if (name.equals("title")) boardDto.setTitle(value);
					if (name.equals("cd_ctg")) boardDto.setCd_ctg(Integer.parseInt(value));
					if (name.equals("content")) boardDto.setContent(value) ;
					if (name.equals("flg_top")) boardDto.setFlg_top(value);
				}
				else {
					 
					String fileFieldName	= item.getFieldName();
					String fileName			= item.getName();
					// String contentType	= item.getContentType();
					
					if (fileName != null && !fileName.equals("")) {
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						// long fileSize = item.getSize();
						
						File file = new File(path + "/" + fileName);
						
						item.write(file);
						
						if (fileFieldName.equals("fileOrig")) boardDto.setFileOrig(fileName);
					}
					logger.debug("-----------------------------------");
					logger.debug("요청 파라미터 이름 : " + fileFieldName);
					logger.debug("저장 파일 이름 : " + fileName);
					logger.debug("파일 콘텐츠 유형 : " + contentType);
					logger.debug("파일 크기 : " + fileSize);
				}
			}
			
			
			logger.debug("cd_bbs_type = "	+ boardDto.getCd_bbs_type());
			logger.debug("title = "			+ boardDto.getTitle());
			logger.debug("content = "		+ boardDto.getContent());
			logger.debug("category = "		+ boardDto.getCategory());
			logger.debug("fileOrig = "		+ boardDto.getFileOrig());
			
			boardDto.setRegister(Integer.parseInt(getSession(request, "SEQ_MNG")));
			
			if (boardSrvc.insert(boardDto)) {
				request.setAttribute("script"	, "alert('등록되었습니다.');");
				request.setAttribute("redirect"	, "/console/center/board/list.web?cd_bbs_type=" + boardDto.getCd_bbs_type());
			}
			else {
				request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
				request.setAttribute("redirect"	, "/");
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	*/
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-07-30
	 * <p>DESCRIPTION: 고객센터 쓰기 폼</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/console/center/board/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			if (boardDto.getCd_bbs_type() == 1) {
				mav.setViewName("backoffice/center/board/notice/writeForm");
			}
			else if (boardDto.getCd_bbs_type() == 2) {
				mav.setViewName("backoffice/center/board/faq/writeForm");
			}
			else if (boardDto.getCd_bbs_type() == 3) {
				
				BoardDto _boardDto = boardSrvc.select(boardDto);
				
				mav.addObject("boardDto", _boardDto);
				mav.setViewName("backoffice/center/board/question/writeForm");
			}
			else {
				request.setAttribute("redirect"	, "/");
				mav.setViewName("forward:/servlet/result.web");
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param pagingDto [페이징 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-06-19
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/console/center/board/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			PagingListDto pagingListDto = boardSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			if (pagingDto.getCd_bbs_type() == 1) {
				mav.setViewName("backoffice/center/board/notice/list");
			}
			else if (pagingDto.getCd_bbs_type() == 2) {
				mav.setViewName("backoffice/center/board/faq/list");
			}
			else if (pagingDto.getCd_bbs_type() == 3) {
				mav.setViewName("backoffice/center/board/question/list");
			}
			else {
				request.setAttribute("redirect"	, "/");
				mav.setViewName("forward:/servlet/result.web");
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
