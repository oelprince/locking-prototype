package com.oelprince.locking.prototype.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oelprince.locking.prototype.serializer.MoneySerializer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@XmlRootElement
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {


    private Timestamp version;
    private String customerNbr;
    private String customerStusCd;
    private String customerFilerCd;

    @JsonProperty("customerTotlAmt")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal customerTotlAmt;

    private String crteById;
    private String updById;
    private Timestamp crteTs;
    private Timestamp updTs;

    @Id
    @Column(name = "customer_nbr", nullable = false, length = 20)
    public String getCustomerNbr() {
        return customerNbr;
    }

    public void setCustomerNbr(String customerNbr) {
        this.customerNbr = customerNbr;
    }

    @Basic
    @Column(name = "customer_stus_cd", nullable = false, length = 20)
    public String getCustomerStusCd() {
        return customerStusCd;
    }

    public void setCustomerStusCd(String customerStusCd) {
        this.customerStusCd = customerStusCd;
    }

    @Basic
    @Column(name = "customer_filer_cd", nullable = false, length = 3)
    public String getCustomerFilerCd() {
        return customerFilerCd;
    }

    public void setCustomerFilerCd(String customerFilerCd) {
        this.customerFilerCd = customerFilerCd;
    }

    @JsonSerialize(using = MoneySerializer.class)
    @Basic
    @Column(name = "customer_totl_amt", nullable = false, precision = 2)
    public BigDecimal getCustomerTotlAmt() {
        return customerTotlAmt;
    }

    public void setCustomerTotlAmt(BigDecimal customerTotlAmt) {
        this.customerTotlAmt = customerTotlAmt;
    }

    @Basic
    @Column(name = "crte_by_id", nullable = false, length = 10)
    public String getCrteById() {
        return crteById;
    }

    public void setCrteById(String crteById) {
        this.crteById = crteById;
    }

    @Basic
    @Column(name = "upd_by_id", nullable = false, length = 10)
    public String getUpdById() {
        return updById;
    }

    public void setUpdById(String updById) {
        this.updById = updById;
    }

    @Basic
    @Column(name = "crte_ts", nullable = true)
    public Timestamp getCrteTs() {
        return crteTs;
    }

    public void setCrteTs(Timestamp crteTs) {
        this.crteTs = crteTs;
    }

    @Basic
    @Column(name = "upd_ts", nullable = true)
    public Timestamp getUpdTs() {
        return updTs;
    }

    public void setUpdTs(Timestamp updTs) {
        this.updTs = updTs;
    }

    @Version
    @Column(name = "version")
    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer clBill = (Customer) o;
        return Objects.equals(customerNbr, clBill.customerNbr) &&
                Objects.equals(customerStusCd, clBill.customerStusCd) &&
                Objects.equals(customerTotlAmt, clBill.customerTotlAmt) &&
                Objects.equals(crteById, clBill.crteById) &&
                Objects.equals(updById, clBill.updById) &&
                Objects.equals(crteTs, clBill.crteTs) &&
                Objects.equals(updTs, clBill.updTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNbr, customerStusCd, customerTotlAmt, crteById, updById, crteTs, updTs);
    }
}
