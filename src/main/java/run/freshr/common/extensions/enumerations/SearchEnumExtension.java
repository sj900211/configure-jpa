package run.freshr.common.extensions.enumerations;

import com.querydsl.core.BooleanBuilder;
import run.freshr.common.mappers.EnumModel;

/**
 * 키워드 검색 ENUM Interface
 *
 * @author FreshR
 * @apiNote 키워드 검색에서 검색 유형을 Enum 으로 관리할 때<br>
 *          Functional Interface 에 정의된 기능을 사용해서 QueryDsl 부분을 간단하게 구현할 수 있음<br>
 *          Functional Interface 를 사용할 때 검색 유형 Enum 은 해당 Interface 를 상속 받아야 한다.
 * @since 2024. 3. 27. 오후 1:46:56
 */
public interface SearchEnumExtension extends EnumModel {

  /**
   * 자연어 검색
   *
   * @param word 검색어
   * @return boolean builder
   * @apiNote 검색 유형에 따라 처리되도록 로직을 구현
   * @author FreshR
   * @since 2024. 3. 27. 오후 1:46:56
   */
  BooleanBuilder search(String word);

}
