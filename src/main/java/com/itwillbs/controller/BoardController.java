package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value = "/board/*")
/* @RequestMapping(value = "/board/*")  
 *  ~.bo 끝나는 주소를 설정한것처럼
 *   /board/~ 주소로 설정
 */
public class BoardController {

	private static final Logger logger 
	   = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService bService;
	
	// http://localhost:8088/regist (x)
	// http://localhost:8088/board/regist (o)
	// 글쓰기 GET - 글정보를 입력
	@RequestMapping(value = "/regist",method = RequestMethod.GET)
	public void registGET() throws Exception {
		logger.debug(" registGET() 호출 ");
		logger.debug(" 연결된 뷰페이지(/views/board/regist.jsp)를 출력 ");
	}
	
	// 글쓰기 POST - 입력된 글정보를 디비에 저장
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public String registPOST(/*@ModelAttributes*/BoardVO vo,
			                 RedirectAttributes rttr) throws Exception {
		logger.debug(" registPOST() 호출 ");
		// 한글 인코딩 처리(생략) => 필터 설정(web.xml) 
		// 전달정보(파라메터) 저장 => 파라메터 자동수집
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@ 글 작성 정보 : "+vo);
		
		// 서비스 -> DAO 호출  : 글쓰기 호출
		bService.regist(vo);
		
		// 글쓰기 성공 정보를 view페이지로 전달
		// => /board/success.jsp 페이지에 "글쓰기 성공!"
		//model.addAttribute("result","createOK");
		rttr.addFlashAttribute("result","createOK");
		
		
		logger.debug(" 연결된 (/board/listAll)페이지로 이동 ");
		//return "/board/success";
		return "redirect:/board/listAll";
	}
	
	// http://localhost:8088/board/listAll
	// http://localhost:8088/board/listAll?result=createOK
	
	// 글 목록조회GET (list) - 기존의 DB에 저장된 값을 가져와서 출력
	@RequestMapping(value = "/listAll",method = RequestMethod.GET)
	public void listAllGET(Model model,HttpSession session) throws Exception {
		logger.debug(" listAllGET() 호출 ");
		
		// 서비스 -> DAO -> 글 목록을 조회하는 동작 수행
		List<BoardVO> boardListAll = bService.listAll();
		
		//logger.debug(""+boardListAll);
		logger.debug("결과 리스트 크기 : "+boardListAll.size());
		// 디비에서 가져온정보를 화면에 출력
		// => 정보를 view페이지로 전달(Model객체 생성)
		model.addAttribute("boardListAll",boardListAll);
		
		// 조회수 증가를 체크하기위한 속성값 생성(session 영역에 저장)
		// viewcntCheck-on일때만 조회수를 1증가
		session.setAttribute("viewcntCheck", "on");
		
		// view 이동 후 출력		
	}
	
	// 글 본문내용 보기 (Read) - 디비에서 특정 글정보를 조회해서 화면에 출력
	// http://localhost:8088/board/read?bno=12
	@RequestMapping(value = "/read",method = RequestMethod.GET)
	public void readGET(/* @ModelAttribute("bno") int bno, */
			            @RequestParam("bno") int bno,
			            HttpSession session,
			            Model model) throws Exception{
		logger.debug(" readGET() 호출 ");

		// 전달정보 저장(bno)
		logger.debug(" bno : "+bno);
		
		// 서비스 -> DAO -> 조회수 1증가 메서드
		// +기능 추가 list->read 이동하는 경우만 조회수 1증가
		if(session.getAttribute("viewcntCheck").equals("on")) {
			bService.updateViewCnt(bno);			
			
			//session.invalidate();  아이디도 초기화
			//session.removeAttribute("viewcntCheck"); null값
			session.setAttribute("viewcntCheck", "off");
		}
		
		
		// 서비스 -> DAO -> 특정 글정보를 조회하는 메서드
		BoardVO resultVO = bService.getBoard(bno);
		// 리턴받은 특정 글정보를 연결된 뷰페이지에 출력 (Model)
		model.addAttribute("resultVO", resultVO);
		
		//model.addAttribute(bService.getBoard(bno));
		
		// 뷰페이지로 이동 후 출력	
		// /board/read.jsp
	}
	
	
	// 글 수정하기 GET
	@RequestMapping(value = "/modify",method = RequestMethod.GET)	
	public void modifyGET(@RequestParam("bno") int bno,
			              Model model) throws Exception {
		logger.debug(" modifyGET() 호출 ");
		
		// 전달정보 저장 bno
		logger.debug(" 수정할 글번호 : 	"+bno);
		
		// 특정 글번호에 해당하는 글정보를 뷰페이지에 출력
		model.addAttribute(bService.getBoard(bno));
		// 연결된 뷰페이지(/board/modify.jsp)로 이동
		
	}
	
	// 글 수정하기 POST
	@RequestMapping(value = "/modify",method = RequestMethod.POST )
	public String modifyPOST(/* @ModelAttribute */ BoardVO vo) throws Exception{
		logger.debug(" modifyPOST() 호출 ");
		// 한글처리 인코딩(web.xml필터사용-생략)
		// 전달정보 저장
		logger.debug(""+vo);
		
		// 서비스 -> DAO - 글 수정 메서드 호출
		bService.modifyBoard(vo);
		
		//return "/board/modifyA"; (X) 처리동작x,단순 뷰페이지만 연결
		return "redirect:/board/listAll";
	}
	
	
	// 게시판 글 삭제
	@RequestMapping(value = "/remove",method = RequestMethod.POST)
	public String removePOST(int bno) throws Exception{
		logger.debug(" removePOST() 호출 ");
		// 전달정보 bno 저장
		logger.debug(" 삭제할 글번호 : "+bno);
		
		// 서비스 -> DAO -> 글정보 삭제 
		int result = bService.removeBoard(bno);
		
		if(result != 1) {
			// 삭제 실패
			return "redirect:/board/read?bno="+bno;
		}
		// /board/listAll 페이지로 이동		
				
		return "redirect:/board/listAll";
	}
	
	
	// http://localhost:8088/board/listAll (x)
	// http://localhost:8088/board/listCri (o)
	// http://localhost:8088/board/listCri?page=3
	// http://localhost:8088/board/listCri?pageSize=20
	// http://localhost:8088/board/listCri?page=5&pageSize=25
	
	// 게시판 목록조회(페이징처리)
	@RequestMapping(value = "/listCri",method = RequestMethod.GET)
	public void listPageCriGET(Criteria cri,Model model,HttpSession session) throws Exception {
		logger.debug(" listPageCriGET() 호출 ");
		
		//cri.setPage(2);
		
		// 서비스 -> DAO -> 페이징처리한 리스트 가져오기
		List<BoardVO> boardList = bService.getListPage(cri);
		
		//리스트 사이즈 확인
		logger.debug(" 글 개수 : "+boardList.size());
		
		// Model 객체에 리스트 정보를 저장
		model.addAttribute("boardList", boardList);
		// 조회수 증가를 체크하기위한 속성값 생성(session 영역에 저장)
		// viewcntCheck-on일때만 조회수를 1증가
		session.setAttribute("viewcntCheck", "on");
		
		// 페이지 이동(/board/listCri.jsp)
	}
	
	// http://localhost:8088/board/listPage
	// 게시판 목록조회(페이징처리)
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPageGET(Criteria cri,Model model,HttpSession session) throws Exception {
			logger.debug(" listPageGET() 호출 ");
			
			// 페이징처리( 페이지 블럭 처리 객체 )
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			//pageVO.setTotalCount(1664);
			pageVO.setTotalCount(bService.getBoardCount());
			
			logger.debug(""+pageVO);
			// 페이징처리 정보도 뷰페이지로 전달
			model.addAttribute("pageVO", pageVO);					
			//cri.setPage(2);
			// 페이지 이동시 받아온 페이지 번호
			if(cri.getPage() > pageVO.getEndPage()) {
				// 잘못된 페이지 정보 입력
				cri.setPage(pageVO.getEndPage());
			}
			
			
			// 서비스 -> DAO -> 페이징처리한 리스트 가져오기
			List<BoardVO> boardList = bService.getListPage(cri);
			
			//리스트 사이즈 확인
			logger.debug(" 글 개수 : "+boardList.size());
			
			// Model 객체에 리스트 정보를 저장
			model.addAttribute("boardList", boardList);
			
			
			
			// 조회수 증가를 체크하기위한 속성값 생성(session 영역에 저장)
			// viewcntCheck-on일때만 조회수를 1증가
			session.setAttribute("viewcntCheck", "on");
			
			// 페이지 이동(/board/listPage.jsp)
		}
	
	
	
	
	
	
	
	
}
