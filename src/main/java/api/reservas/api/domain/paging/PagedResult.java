package api.reservas.api.domain.paging;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class PagedResult<T> {

    private final PagingInfo pagingInfo;
    private final Collection<T> pagedRecords;

    protected PagedResult(Collection<T> pagedRecords, PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
        this.pagedRecords = pagedRecords;
    }

    public static <U> PagedResult<U> emptyResult() {
        return new PagedResult<>(Collections.emptyList(), PagingInfo.of());
    }

    public static <T> PagedResult<T> of(Page<T> result, PagingInfo pagingInfo) {
        pagingInfo.setTotalPages(result.getTotalPages());
        return new PagedResult<>(result.getContent(), pagingInfo);
    }

    public static <T> PagedResult<T> of(List<T> list) {
        return new PagedResult<>(list, null);
    }

    @SuppressWarnings("unchecked")
    public <U> PagedResult<U> map(Function<? super T, ? extends U> converter) {
        if (pagedRecords.isEmpty()) {
            return PagedResult.of(List.of());
        }

        return new PagedResult<>((Collection<U>) this.pagedRecords.stream().map(converter), this.pagingInfo);
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public Collection<T> getPagedRecords() {
        return pagedRecords;
    }
}
