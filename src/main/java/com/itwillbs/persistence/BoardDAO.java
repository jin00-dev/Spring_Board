package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {
	
	//글쓰기(Create) 동작
	public void create(BoardVO vo) throws Exception;
	
	//글목록 조회(Read) 동작
	public List<BoardVO> getBoardListAll() throws Exception;
	
	// 글 조회수 1증가(Update) 동작
	public void updateViewCnt(Integer bno) throws Exception;
	
	// 특정 글 정보조회(Read) 동작
	public BoardVO getBoard(Integer bno) throws Exception;
	
	// 특정 글정보 수정(Update) 동작
	public void modifyBoard(BoardVO vo) throws Exception;
	
	// 특정 글정보 삭제(Delete) 동작
	public int deleteBoard(Integer bno) throws Exception;
	
	// 페이징처리 후 리스트 목록조회
	public List<BoardVO> getListPage(Integer page) throws Exception;
	
	// 페이징처리 후 리스트 목록조회
	public List<BoardVO> getListPage(Criteria cri) throws Exception;
	
	// 게시판 글 총 개수 조회
	public int getBoardCount() throws Exception;
	

}
