package com.tenstech.spring_security_reuse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditedEntity implements Serializable {

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    protected LocalDateTime created;

    @LastModifiedDate
    @Column(name = "last_modified")
    protected LocalDateTime lastModified;

    @Column(name = "created_by")
    @CreatedBy
    protected String createdBy;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    protected String modifiedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false, updatable = false)
    private Long id;
}