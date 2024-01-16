package com.example.springbootaws.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass   //entity 클래스들이 이 클래스를 상속할 경우 필드들도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //이 클래스에 Auditing 기능을 포함시킨다.
public class BaseTimeEntity {

	//entity들의 CreatedDate, modifiedDate를 자동으로 관리하는 역할이다.

	//entity가 생성되어 저장될 때 시간이 자동 저장이 됩니다.
	@CreatedDate
	private LocalDateTime CreatedDate;

	//조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
