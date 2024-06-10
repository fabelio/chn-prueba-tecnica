package com.chn.customer.management.entities;
import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
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
@Table(name = "\"check\"")
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_id_gen")
    @SequenceGenerator(name = "check_id_gen", sequenceName = "CHECK_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "\"number\"")
    private Long number;

    @Column(name = "AMOUNT", precision = 10, scale = 4)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CHECKBOOK_ID", nullable = false)
    private Checkbook checkbook;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENT_STATUS_ID")
    private CheckStatusHist currentStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Checkbook getCheckbook() {
        return checkbook;
    }

    public void setCheckbook(Checkbook checkbook) {
        this.checkbook = checkbook;
    }

    public CheckStatusHist getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CheckStatusHist currentStatus) {
        this.currentStatus = currentStatus;
    }

}