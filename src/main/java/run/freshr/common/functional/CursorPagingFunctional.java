package run.freshr.common.functional;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import run.freshr.common.data.CursorData;
import run.freshr.common.extensions.request.SearchExtension;

/**
 * 페이징 functional interface
 *
 * @param <E> Entity
 * @param <Q> type parameter
 * @author FreshR
 * @apiNote 페이징 함수형 인터페이스 정의
 * @since 2024. 3. 27. 오후 1:46:56
 */
@FunctionalInterface
public interface CursorPagingFunctional<E, Q extends EntityPathBase<E>> {

  /**
   * 페이징 처리
   *
   * @param query      JPA Query
   * @param path       path
   * @param page       page
   * @param size       size
   * @param predicates predicates
   * @param orders     orders
   * @return page data
   * @apiNote 데이터 페이징 처리
   * @author FreshR
   * @since 2024. 3. 27. 오후 1:46:56
   */
  CursorData<E> paging(JPAQuery<E> query, Q path, Integer page, Integer size,
      List<Predicate> predicates, List<OrderSpecifier<?>> orders);

}
