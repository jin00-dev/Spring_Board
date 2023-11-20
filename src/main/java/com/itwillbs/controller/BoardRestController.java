package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardRestController {

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	@Inject
	private BoardService bService;
	
	
	// 글쓰기  POST : /boards + JSON
	// http://localhost:8088/boards
	@RequestMapping(value = "",method = RequestMethod.POST )
	public ResponseEntity<String> addBoard(@RequestBody BoardVO vo) throws Exception{
		logger.debug(" addBoard() 호출 @@@@ ");
		
		bService.regist(vo);
		// 비동기 방식으로 호출 -> home.jsp로 갈게 http://localhost:8088/로 갈 수 있슴 
		
		logger.debug("글쓰기 완료! ");
		
		return new ResponseEntity<String>("CREATE_OK",HttpStatus.OK);
	}// addBoard
	
	//글 조회 (본문 보기) - GET /boards/100 
	// http://localhost:8088/boards/17901 => 주소를 어떻게 매핑 할거니? {bno} => 패스베리어블...
	@RequestMapping(value = "/{bno}", method = RequestMethod.GET)
	public ResponseEntity<BoardVO> readBoard(@PathVariable("bno") int bno) throws Exception{
		logger.debug("readBoard(bno) 호출 @@@@");
		
		BoardVO boardVO = bService.getBoard(bno); // BoardVO 객체 들고 온 것 
		logger.debug("글 조회 성공 @@@@@");
		logger.debug(""+boardVO);
		
		// 전달 하고자 하는 데이터 + 상태
		ResponseEntity<BoardVO> respEntity = null;
		try {
			respEntity= new ResponseEntity<BoardVO>(boardVO, HttpStatus.OK);//제네릭 없어도 됨
			logger.debug("글 조회 성공 완료, 상태정보 리턴완료 ");
			
		}catch(Exception e) {
			respEntity= new ResponseEntity<BoardVO>(boardVO, HttpStatus.INTERNAL_SERVER_ERROR);
//			respEntity= new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR); 제네릭 없이 가능 
		}
		return respEntity;
		
	}//readBoard
	
	//글 정보 수정 / PUT /board/100 + JSON
	@RequestMapping(value = "/{bno}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBoard(@PathVariable("bno") int bno, @RequestBody BoardVO vo) throws Exception{
		// 전달방식 PUT => 데이터 수정! 
		logger.debug("updateBoard() 호출@@@@ ");
		
		// bno(글번호) 저장 + JSON데이터 (수정 할 글 정보) 저장 
		logger.debug(" 글 번호 : " + bno); // postman에서 주소 쳐서 STS 콘솔에서 확인 할 수 있음 
		logger.debug("글 정보 : " + vo);
		// 글 정보 수정 메서드 호출 
		bService.modifyBoard(vo);
		
		// 결과 처리
		
		return new ResponseEntity<String>("updateOK", HttpStatus.OK);
	}//updateBoard
	
	//글 정보 삭제 DELETE /board/100 
	@RequestMapping(value = "/{bno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoard(@PathVariable("bno") int bno) throws Exception{
		logger.debug("deleteBoard() 호출");
		
		logger.debug("글 번호 : " + bno);
		
		int result = bService.removeBoard(bno);
		
		ResponseEntity<String> respEntity = null;
		if(result == 1) {
			//삭제 완료
			respEntity = new ResponseEntity<String>("deleteOK",HttpStatus.OK);
		}else {
			//삭제 안됨 
			respEntity = new ResponseEntity<String>("deleteNO",HttpStatus.INTERNAL_SERVER_ERROR);
		}// 데이터 처리 동작에 따른 전달 값 다르게 보내기 위해 사용
		
		return respEntity;
	}//deleteBoard
	
	//글 목록 조회 => /boards/list (GET) => 1페이지 10개씩 조회 할 수 있게
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> listBoard(Criteria cri) throws Exception{
		logger.debug("listBoard() 호출");
		
		//서비스 - 목록 조회 
		List<BoardVO> boardList = bService.getListPage(cri);
		
		return new ResponseEntity<List<BoardVO>>(boardList, HttpStatus.OK);
	}
	
	
	
	
}//BoardRestController
