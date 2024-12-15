package api.reservas.api.domain.paging;

import org.springframework.data.domain.Page;

public class PagedResultFactory {
    public static <T> PagedResult<T> of(Page<T> result, PagingInfo pagingInfo) {
        pagingInfo.setTotalPages(result.getTotalPages());
        return new PagedResult<>(result.getContent(), pagingInfo);
    }
}
