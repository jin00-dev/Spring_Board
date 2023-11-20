package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardService {

	// 게시판 글쓰기
	public void regist(BoardVO vo) throws Exception;

	// 게시판 전체 목록 조회
	public List<BoardVO> listAll() throws Exception;

	// 게시판 글 조회수 1증가
	public void updateViewCnt(Integer bno) throws Exception;

	// 특정 게시판 글 정보 조회
	public BoardVO getBoard(Integer bno) throws Exception;

	// 특정 글 정보 수정
	public void modifyBoard(BoardVO vo) throws Exception;

	// 특정 글 정보 삭제
	public int removeBoard(Integer bno) throws Exception;

	// 페이징처리 후 리스트 목록조회
	public List<BoardVO> getListPage(Criteria cri) throws Exception;

	// 게시판 글 총 개수 조회
	public int getBoardCount() throws Exception;

}
