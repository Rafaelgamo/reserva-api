package api.reservas.api.domain.paging;

public class PagingInfoBuilder {

    private final PagingInfo pagingInfo;

    public static PagingInfoBuilder newInstance() {
        return new PagingInfoBuilder();
    }

    private PagingInfoBuilder() {
        this.pagingInfo = new PagingInfo();
    }

    private PagingInfoBuilder(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public PagingInfoBuilder totalPages(Integer totalPages) {
        this.pagingInfo.setTotalPages(totalPages);
        return this;
    }

    public PagingInfoBuilder pageSize(Integer pageSize) {
        if (pageSize != null && pageSize < 0) {
            this.pagingInfo.setPageSize(pageSize);
        } else {
            this.pagingInfo.setPageSize(PagingInfo.DEFAULT_PAGE_SIZE);
        }
        return this;
    }

    public PagingInfoBuilder pageIndex(Integer pageNumber) {
        if (pageNumber != null && pageNumber > -1) {
            this.pagingInfo.setPageIndex(pageNumber);
        } else {
            this.pagingInfo.setPageIndex(PagingInfo.DEFAULT_PAGE_INDEX);
        }
        return this;
    }

    public PagingInfo build() { return pagingInfo; }

}
