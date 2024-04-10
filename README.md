# Configure > JPA
> JPA 설정 및 정의
> - ## [JpaConfiguration](./src/main/java/run/freshr/common/configurations/JpaConfiguration.java)
>> JPA 설정
> - ## [QueryDslConfiguration](./src/main/java/run/freshr/common/configurations/QueryDslConfiguration.java)
>> JPAQuery 를 프로젝트 전역에서 주입받을 수 있도록 설정
> - ## [CursorClaimData](./src/main/java/run/freshr/common/data/CursorClaimData.java)
>> Cursor 정보 모델  
>> 토큰에 저장될 데이터 모델
> - ## [CursorData](./src/main/java/run/freshr/common/data/CursorData.java)
>> Page 를 상속받아 Cursor 기능을 정의한 모델  
>> nextPageToken 이 추가된 형태
> - ## [ResponseData](./src/main/java/run/freshr/common/data/ResponseData.java)
>> 반환 형태를 통일하기 위한 모델  
>> 모든 반환 데이터는 이 모델로 감싸여 반환된다.
> - ## [EntityAuditLogicalExtension](./src/main/java/run/freshr/common/extensions/entity/EntityAuditLogicalExtension.java)
>> 작성자 정보와 논리 삭제 정책을 가지는 Entity 공통 속성을 정의
> - ## [EntityAuditPhysicalExtension](./src/main/java/run/freshr/common/extensions/entity/EntityAuditPhysicalExtension.java)
>> 작성자 정보와 물리 삭제 정책을 가지는 Entity 공통 속성을 정의
> - ## [EntityPhysicalExtension](./src/main/java/run/freshr/common/extensions/entity/EntityPhysicalExtension.java)
>> 물리 삭제 정책을 가지는 Entity 공통 속성을 정의
> - ## [EntityLogicalExtension](./src/main/java/run/freshr/common/extensions/entity/EntityLogicalExtension.java)
>> 논리 삭제 정책을 가지는 Entity 공통 속성을 정의
> - ## [SearchEnumExtension](./src/main/java/run/freshr/common/extensions/enumerations/SearchEnumExtension.java)
>> 키워드 검색 기능을 Enum 과 Functional Interface 로 처리하기 위한 기능을 설계
> - ## [ResponseAuditLogicalExtension](./src/main/java/run/freshr/common/extensions/response/ResponseAuditLogicalExtension.java)
>> 작성자 정보와 논리 삭제 정책을 가지는 반환 모델 공통 속성을 정의
> - ## [ResponseAuditPhysicalExtension](./src/main/java/run/freshr/common/extensions/response/ResponseAuditPhysicalExtension.java)
>> 작성자 정보와 물리 삭제 정책을 가지는 반환 모델 공통 속성을 정의
> - ## [ResponseLogicalExtension](./src/main/java/run/freshr/common/extensions/response/ResponseLogicalExtension.java)
>> 논리 삭제 정책을 가지는 반환 모델 공통 속성을 정의
> - ## [ResponsePhysicalExtension](./src/main/java/run/freshr/common/extensions/response/ResponsePhysicalExtension.java)
>> 물리 삭제 정책을 가지는 반환 모델 공통 속성을 정의
> - ## [UnitPageExtension](./src/main/java/run/freshr/common/extensions/unit/UnitPageExtension.java)
>> 페이징 조회가 있는 RDB Unit 의 공통 기능 정의  
>> Unit 의 대한 자세한 내용은 `library-core-unit` 를 참고
> - ## [UnitCursorExtension](./src/main/java/run/freshr/common/extensions/unit/UnitCursorExtension.java)
>> Cursor 페이징 조회가 있는 RDB Unit 의 공통 기능 정의
> - ## [CursorPagingFunctional](./src/main/java/run/freshr/common/functional/CursorPagingFunctional.java)
>> Cursor 페이징 기능 함수형 인터페이스 정의
> - ## [PagingFunctional](./src/main/java/run/freshr/common/functional/PagingFunctional.java)
>> 페이징 기능 함수형 인터페이스 정의
> - ## [SearchEnumFunctional](./src/main/java/run/freshr/common/functional/SearchEnumFunctional.java)
>> SearchEnumExtension 을 사용한 자연어 검색 기능 함수형 인터페이스 정의
> - ## [SearchKeywordFunctional](./src/main/java/run/freshr/common/functional/SearchKeywordFunctional.java)
>> 자연어 검색 기능 함수형 인터페이스 정의
> - ## [QueryUtilAware](./src/main/java/run/freshr/common/utils/QueryUtilAware.java)
>> QueryDsl 공통 기능 정의  
>> 함수형 인터페이스를 활용한 기능이 정의되어 있음