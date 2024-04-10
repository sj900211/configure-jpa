package run.freshr.common.extensions.unit;

import run.freshr.common.data.CursorData;
import run.freshr.common.extensions.request.SearchExtension;

/**
 * Paging 조회가 있는 Unit 공통 기능을 정의
 *
 * @param <E>  Entity
 * @param <ID> ID 데이터 유형
 * @param <S>  SearchExtension 을 상속받은 Get Parameter VO
 * @author FreshR
 * @apiNote Paging 조회가 있는 Unit 공통 기능을 정의
 * @since 2024. 3. 27. 오후 1:46:56
 */
public interface UnitCursorExtension<E, ID, S extends SearchExtension<ID>>
    extends UnitPageExtension<E, ID, S> {

  /**
   * 페이징 데이터 조회
   *
   * @param search SearchExtension 을 상속받은 Get Parameter VO
   * @return page
   * @apiNote 페이징 데이터 조회
   * @author FreshR
   * @since 2024. 3. 27. 오후 1:46:56
   */
  CursorData<E> getCursor(S search);

}
