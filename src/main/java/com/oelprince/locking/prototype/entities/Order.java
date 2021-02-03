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
@Table(name = "C_ORDER")
public class Order implements Serializable {

    private Timestamp version;

    private String orderNbr;
    private String customerNbr;
    private String entryNbr;

    @JsonProperty("entryAmt")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal entryAmt;

    private String crteById;
    private String updById;
    private Timestamp crteTs;
    private Timestamp updTs;

    @Id
    @Column(name = "order_nbr", nullable = false, length = 20)
    public String getOrderNbr() {
        return orderNbr;
    }

    @Column(name = "customer_nbr", nullable = false, length = 20)
    public String getCustomerNbr() {
        return customerNbr;
    }

    public void setOrderNbr(String orderNbr) {
        this.orderNbr = orderNbr;
    }


    public void setCustomerNbr(String customerNbr) {
        this.customerNbr = customerNbr;
    }

    @Basic
    @Column(name = "entry_nbr", nullable = true, length = 15)
    public String getEntryNbr() {
        return entryNbr;
    }

    public void setEntryNbr(String entryNbr) {
        this.entryNbr = entryNbr;
    }

    @Basic
    @Column(name = "entry_amt", nullable = false, precision = 2)
    public BigDecimal getEntryAmt() {
        return entryAmt;
    }

    public void setEntryAmt(BigDecimal entryAmt) {
        this.entryAmt = entryAmt;
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
        Order that = (Order) o;
        return Objects.equals(orderNbr, that.orderNbr) &&
                Objects.equals(customerNbr, that.customerNbr) &&
                Objects.equals(entryNbr, that.entryNbr) &&
                Objects.equals(crteById, that.crteById) &&
                Objects.equals(updById, that.updById) &&
                Objects.equals(crteTs, that.crteTs) &&
                Objects.equals(updTs, that.updTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNbr, customerNbr, crteById, updById, crteTs, updTs);
    }
}
