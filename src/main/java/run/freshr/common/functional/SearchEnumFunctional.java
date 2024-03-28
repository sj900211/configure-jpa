package run.freshr.common.functional;

import com.querydsl.core.BooleanBuilder;
import run.freshr.common.extensions.enumerations.SearchEnumExtension;

/**
 * 자연어 검색 functional interface
 *
 * @param <E> SearchEnumExtension 상속받은 검색 유형 Enum
 * @author FreshR
 * @apiNote SearchEnumExtension 을 사용한 자연어 검색 함수형 인터페이스 정의
 * @since 2024. 3. 27. 오후 1:46:56
 */
@FunctionalInterface
public interface SearchEnumFunctional<E extends SearchEnumExtension> {

  /**
   * 자연어 검색
   *
   * @param word        검색어
   * @param enumeration SearchEnumExtension 상속받은 검색 유형 Enum
   * @return boolean builder
   * @apiNote 자연어 검색
   * @author FreshR
   * @since 2024. 3. 27. 오후 1:46:56
   */
  BooleanBuilder search(String word, E enumeration);

}
