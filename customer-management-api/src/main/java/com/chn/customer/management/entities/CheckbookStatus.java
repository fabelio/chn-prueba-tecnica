package com.chn.customer.management.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHECKBOOK_STATUS")
public class CheckbookStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKBOOK_STATUS_id_gen")
    @SequenceGenerator(name = "CHECKBOOK_STATUS_id_gen", sequenceName = "CHECKBOOK_STATUS_HIST_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODE", length = 100)
    private String code;

    @Column(name = "NAME", length = 100)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}