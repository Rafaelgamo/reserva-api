package api.reservas.api.domain.paging;

import org.springframework.data.domain.PageRequest;

import java.util.Objects;

public class PagingInfo {

    private Integer totalPages;
    private Integer pageSize;
    private Integer pageIndex;

    protected static final Integer DEFAULT_PAGE_SIZE = 25;
    protected static final Integer DEFAULT_PAGE_INDEX = 0;

    protected PagingInfo(Integer pageIndex, Integer pageSize) {
        this.pageIndex = Objects.isNull(pageIndex)
                ? DEFAULT_PAGE_INDEX
                : pageIndex;

        this.pageSize = Objects.isNull(pageSize)
                ? DEFAULT_PAGE_SIZE
                : pageSize;
    }

    protected PagingInfo() {
        this.pageIndex = DEFAULT_PAGE_INDEX;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public static PagingInfo of(Integer pageNumber, Integer pageSize) {
        return new PagingInfo(pageNumber, pageSize);
    }
    public static PagingInfo of() {
        return new PagingInfo();
    }

    public PageRequest toPageRequest() {
        var pageIndex = this.pageIndex != null ? this.pageIndex : 0;
        var pageSize = this.pageSize != null ? this.pageSize : DEFAULT_PAGE_SIZE;
        return PageRequest.of(pageIndex, pageSize);
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
