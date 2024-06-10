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
@Table(name = "CHECK_STATUS_HIST")
public class CheckStatusHist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECK_STATUS_HIST_id_gen")
    @SequenceGenerator(name = "CHECK_STATUS_HIST_id_gen", sequenceName = "CHECK_STATUS_HIST_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "REASON", length = 200)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CHECK_ID", nullable = false)
    @JsonIgnore
    private Check check;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CHECK_STATUS_ID", nullable = false)
    private CheckStatus checkStatus;

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

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

}