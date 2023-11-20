package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	// BoardDAO객체 주입
	@Inject
	private BoardDAO bdao;
	
	@Override
	public void regist(BoardVO vo) throws Exception {
		//DAO의 해당동작 메서드 호출
		logger.debug("controller -> service 호출 ");
		bdao.create(vo);
		logger.debug("DAO -> service -> controller 전달");
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		logger.debug(" 컨트롤러가 서비스를 호출 ");
		
		logger.debug(" DAO의 결과를 받아서 컨트롤러 전달");
		return bdao.getBoardListAll();
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		logger.debug(" 조회수 1증가!! ");
		bdao.updateViewCnt(bno);		
	}

	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		logger.debug(" getBoard(Integer bno) 호출 ");
		
		return bdao.getBoard(bno);
	}

	@Override
	public void modifyBoard(BoardVO vo) throws Exception {
		logger.debug(" modifyBoard(BoardVO vo) 호출 @@@ ");
		
		bdao.modifyBoard(vo);
	}

	@Override
	public int removeBoard(Integer bno) throws Exception {
		logger.debug(" removeBoard(Integer bno)  호출 ");
		
		return bdao.deleteBoard(bno);
	}

	@Override
	public List<BoardVO> getListPage(Criteria cri) throws Exception {
		logger.debug(" getListPage(Criteria cri) 호출 ");
		
		return bdao.getListPage(cri);
	}

	@Override
	public int getBoardCount() throws Exception {
		logger.debug(" getBoardCount() 호출 ");
		return bdao.getBoardCount();
	}
	
	
	
}
