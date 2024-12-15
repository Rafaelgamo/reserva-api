package api.reservas.api.domain.paging;

import org.springframework.data.domain.PageRequest;

import java.util.Objects;

public class PagingInfo {

    private Integer totalRecords;
    private Integer pageSize;
    private Integer pageIndex;

    protected static final Integer DEFAULT_PAGE_SIZE = 25;
    protected static final Integer DEFAULT_PAGE_INDEX = 0;

    // unused
    protected PagingInfo(Integer totalRecords, Integer pageSize, Integer pageIndex) {
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    // unused
    protected PagingInfo(Integer pageIndex, Integer pageSize) {
        this.pageIndex = Objects.isNull(pageIndex)
                ? DEFAULT_PAGE_INDEX
                : pageIndex;

        this.pageSize = Objects.isNull(pageSize)
                ? DEFAULT_PAGE_SIZE
                : pageSize;
    }

    protected PagingInfo() { }

    public PageRequest toPageRequest() {
        var pageIndex = this.pageIndex != null ? this.pageIndex : 0;
        var pageSize = this.pageSize != null ? this.pageSize : DEFAULT_PAGE_SIZE;
        return PageRequest.of(pageIndex, pageSize);
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalRecords = totalPages;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
