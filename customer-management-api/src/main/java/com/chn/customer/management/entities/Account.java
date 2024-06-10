package com.chn.customer.management.entities;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_id_gen")
    @SequenceGenerator(name = "ACCOUNT_id_gen", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "\"number\"")
    private String number;

    @Column(name = "OPENING_AMOUNT", nullable = false, precision = 10, scale = 4)
    private BigDecimal openingAmount;

    @Column(name = "BALANCE", nullable = false, precision = 10, scale = 4)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACCOUNT_TYPE_ID", nullable = false)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENT_STATUS_ID")
    private AccountStatusHist currentStatus;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Checkbook> checkbooks = new LinkedHashSet<>();

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

    public BigDecimal getOpeningAmount() {
        return openingAmount;
    }

    public void setOpeningAmount(BigDecimal openingAmount) {
        this.openingAmount = openingAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatusHist getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(AccountStatusHist currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Set<Checkbook> getCheckbooks() {
        return checkbooks;
    }

    public void setCheckbooks(Set<Checkbook> checkbooks) {
        this.checkbooks = checkbooks;
    }

}