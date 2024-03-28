package run.freshr.common.extensions.entity;

import static jakarta.persistence.FetchType.LAZY;
import static run.freshr.common.configurations.DefaultColumnConfiguration.FALSE;
import static run.freshr.common.configurations.DefaultColumnConfiguration.TRUE;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Auditor 정보를 갖는 논리 삭제 MappedSuperclass
 *
 * @param <A> Auditor 데이터 유형
 * @author FreshR
 * @apiNote Audit 정보와 논리 삭제 정책을 가진 MappedSuperclass
 * @since 2024. 3. 27. 오후 1:46:56
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityAuditLogicalExtension<A> {

  @Transient
  @ColumnDefault(TRUE)
  @Comment("사용 여부")
  protected Boolean useFlag;

  @Transient
  @ColumnDefault(FALSE)
  @Comment("삭제 여부")
  protected Boolean deleteFlag;

  @Transient
  @CreatedDate
  @Comment("등록 날짜 시간")
  protected LocalDateTime createAt;

  @Transient
  @LastModifiedDate
  @Comment("마지막 수정 날짜 시간")
  protected LocalDateTime updateAt;

  @Transient
  @CreatedBy
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "creator_id")
  @Comment("등록자 일련 번호")
  protected A creator;

  @Transient
  @LastModifiedBy
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "updater_id")
  @Comment("수정자 일련 번호")
  protected A updater;

  /**
   * 삭제 처리
   *
   * @apiNote 삭제 처리
   * @author FreshR
   * @since 2024. 3. 27. 오후 1:46:56
   */
  protected void remove() {
    this.useFlag = false;
    this.deleteFlag = true;
  }

}
