package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// 디비 접속(mybatis,mapper,자원해제)
	//@Inject
	@Autowired
	private SqlSession sqlSession;
	
	// 사용하는 mapper의 이름
	private static final String NAMESPACE 
	 = "com.itwillbs.mapper.BoardMapper";

//	추가적으로 사용되는 mapper의 이름 설정(+필요한경우)
//	private static final String NAMESPACE_MEMBER
//	= "com.itwillbs.mapper.MemberMapper";
		
	@Override
	public void create(BoardVO vo) throws Exception {
		logger.debug(" service -> DAO 호출 ");
		
		// DB에 글쓰기(insert) 수행
		int result
		= sqlSession.insert(NAMESPACE + ".createBoard", vo);
		
		if(result >= 1) {
			logger.debug(" DB 글쓰기 완료! ");
		}		
	}

	
	
	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		logger.debug(" getBoardListAll() 호출 - 연결된 매퍼 호출 ");
		
		logger.debug(" 결과를 서비스로 리턴 ");
		return sqlSession.selectList(NAMESPACE + ".getListAll");
	}



	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		logger.debug(" updateViewCnt(Integer bno) 호출 ");
		
		int result = sqlSession.update(NAMESPACE + ".updateViewCnt", bno);
		
		if(result >0) {
			logger.debug(bno+"번 글조회수 1증가!!!");
		}
		
	}


	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		logger.debug(" getBoard(Integer bno) 호출 ");
		// sql 구문 호출
		return sqlSession.selectOne(NAMESPACE + ".getBoard",bno);
	}



	@Override
	public void modifyBoard(BoardVO vo) throws Exception {
		logger.debug("modifyBoard(BoardVO vo) 호출");
		
		sqlSession.update(NAMESPACE + ".modifyBoard", vo);
	}



	@Override
	public int deleteBoard(Integer bno) throws Exception {
		logger.debug(" deleteBoard(Integer bno) ");	
		
		return sqlSession.delete(NAMESPACE+".removeBoard", bno);
//		return sqlSession.delete(NAMESPACE+".removeBoard");
	}



	@Override
	public List<BoardVO> getListPage(Integer page) throws Exception {
		logger.debug(" getListPage(Integer page) ");
		
		if(page <= 0) {
			page = 1;
		}
		// 1 -> 1페이지 / 2 -> 2페이지
		page = (page - 1) * 10;
		
		return sqlSession.selectList(NAMESPACE + ".listPage",page);
	}



	@Override
	public List<BoardVO> getListPage(Criteria cri) throws Exception {
		logger.debug(" getListPage(Criteria cri) 호출 ");
		
		return sqlSession.selectList(NAMESPACE + ".listCri",cri);
	}



	@Override
	public int getBoardCount() throws Exception {
		logger.debug(" getBoardCount() 호출 ");
		
		return sqlSession.selectOne(NAMESPACE + ".boardCount");
	}
	
	
	
	
	

}
