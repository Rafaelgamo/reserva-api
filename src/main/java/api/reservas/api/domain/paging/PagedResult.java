package api.reservas.api.domain.paging;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

public class PagedResult<T> {

    private PagingInfo pagingInfo;
    private Collection<T> pagedRecords;

    public PagedResult(Collection<T> pagedRecords, PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
        this.pagedRecords = pagedRecords;
    }

    public PagedResult(Page<T> projectMaterialPage, PagingInfo pagingInfo) {
        this.pagedRecords = projectMaterialPage.getContent();

        pagingInfo.setTotalPages(projectMaterialPage.getTotalPages());
        this.pagingInfo = pagingInfo;
    }

    public static <U> PagedResult<U> emptyResult() {
        return new PagedResult<>(Collections.emptyList(), PagingInfoBuilder.newInstance().build());
    }

    @SuppressWarnings("unchecked")
    public <U> PagedResult<U> map(Function<? super T, ? extends U> converter) {
        return new PagedResult<>((Collection<U>) this.pagedRecords.stream().map(converter), this.pagingInfo);
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public Collection<T> getPagedRecords() {
        return pagedRecords;
    }
}
