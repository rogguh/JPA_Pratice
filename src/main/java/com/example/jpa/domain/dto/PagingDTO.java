package com.example.jpa.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PagingDTO {
    private long pageSize;       // LINE :: 게시 글 수
    private long firstPageNo;    // LINE :: 첫 번째 페이지 번호
    private long prevPageNo;     // LINE :: 이전 페이지 번호
    private long startPageNo;    // LINE :: 시작 페이지 (페이징 네비 기준)
    private long pageNo;         // LINE :: 페이지 번호
    private long endPageNo;      // LINE :: 끝 페이지 (페이징 네비 기준)
    private long nextPageNo;     // LINE :: 다음 페이지 번호
    private long finalPageNo;    // LINE :: 마지막 페이지 번호
    private long totalCount;     // LINE :: 게시 글 전체 수
    private long firstIndex;

    // LINE :: 검색
    private String searchType;
    private String searchText;

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.makePaging();
    }

    /**
     * 페이징 생성
     */
    private void makePaging() {
        if (this.totalCount == 0) return; // 게시 글 전체 수가 없는 경우
        if (this.pageNo == 0) this.setPageNo(1); // 기본 값 설정
        if (this.pageSize == 0) this.setPageSize(10); // 기본 값 설정

        long finalPage = ((totalCount + (pageSize - 1))) / pageSize; // 마지막 페이지
        if (this.pageNo > finalPage) this.setPageNo(finalPage); // 기본 값 설정

        if (this.pageNo < 0 || this.pageNo > finalPage) this.pageNo = 1; // 현재 페이지 유효성 체크

        boolean isNowFirst = pageNo == 1 ? true : false; // 시작 페이지 (전체)
        boolean isNowFinal = pageNo == finalPage ? true : false; // 마지막 페이지 (전체)

        long startPage = ((this.pageNo - 1) / 10) * 10 + 1; // 시작 페이지 (페이징 네비 기준)
        long endPage = startPage + 10 - 1; // 끝 페이지 (페이징 네비 기준)

        if (endPage > finalPage) { // [마지막 페이지 (페이징 네비 기준) > 마지막 페이지] 보다 큰 경우
            endPage = finalPage;
        }

        this.setFirstPageNo(1); // 첫 번째 페이지 번호

        if (isNowFirst) {
            this.setPrevPageNo(1); // 이전 페이지 번호
        } else {
            this.setPrevPageNo(((this.pageNo - 1) < 1 ? 1 : (this.pageNo - 1))); // 이전 페이지 번호
        }
        this.setStartPageNo(startPage); // 시작 페이지 (페이징 네비 기준)
        this.setEndPageNo(endPage); // 끝 페이지 (페이징 네비 기준)

        if (isNowFinal) {
            this.setNextPageNo(finalPage); // 다음 페이지 번호
        } else {
            this.setNextPageNo(((pageNo + 1) > finalPage ? finalPage : (pageNo + 1))); // 다음 페이지 번호
        }

        this.setFinalPageNo(finalPage); // 마지막 페이지 번호

        this.firstIndex = (pageNo-1) * pageSize;
    }
}
