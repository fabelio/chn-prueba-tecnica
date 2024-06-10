package com.chn.customer.management.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHECKBOOK_STATUS_HIST")
public class CheckbookStatusHist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKBOOK_STATUS_HIST_id_gen")
    @SequenceGenerator(name = "CHECKBOOK_STATUS_HIST_id_gen", sequenceName = "CHECKBOOK_STATUS_HIST_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "REASON", length = 200)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CHECKBOOK_ID", nullable = false)
    @JsonIgnore
    private Checkbook checkbook;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CHECKBOOK_STATUS_ID", nullable = false)
    private CheckbookStatus checkbookStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Checkbook getCheckbook() {
        return checkbook;
    }

    public void setCheckbook(Checkbook checkbook) {
        this.checkbook = checkbook;
    }

    public CheckbookStatus getCheckbookStatus() {
        return checkbookStatus;
    }

    public void setCheckbookStatus(CheckbookStatus checkbookStatus) {
        this.checkbookStatus = checkbookStatus;
    }

}