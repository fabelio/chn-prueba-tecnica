package com.chn.customer.management.entities;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHECKBOOK")
public class Checkbook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKBOOK_id_gen")
    @SequenceGenerator(name = "CHECKBOOK_id_gen", sequenceName = "CHECKBOOK_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "\"number\"")
    private String number;

    @Column(name = "CHECK_QUANTITY")
    private Long checkQuantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENT_STATUS_ID")
    private CheckbookStatusHist currentStatus;

    @OneToMany(mappedBy = "checkbook")
    @JsonIgnore
    private Set<Check> checks = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCheckQuantity() {
        return checkQuantity;
    }

    public void setCheckQuantity(Long checkQuantity) {
        this.checkQuantity = checkQuantity;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CheckbookStatusHist getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CheckbookStatusHist currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Set<Check> getChecks() {
        return checks;
    }

    public void setChecks(Set<Check> checks) {
        this.checks = checks;
    }

}