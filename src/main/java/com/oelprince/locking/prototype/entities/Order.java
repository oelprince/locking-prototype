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

    /**
     *
     */
    private static final long serialVersionUID = -7089722637037506473L;

    //private Timestamp version;

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

    // @Version
    // @Column(name = "version")
    // public Timestamp getVersion() {
    //     return version;
    // }

    // public void setVersion(Timestamp version) {
    //     this.version = version;
    // }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((crteById == null) ? 0 : crteById.hashCode());
        result = prime * result + ((crteTs == null) ? 0 : crteTs.hashCode());
        result = prime * result + ((customerNbr == null) ? 0 : customerNbr.hashCode());
        result = prime * result + ((entryAmt == null) ? 0 : entryAmt.hashCode());
        result = prime * result + ((entryNbr == null) ? 0 : entryNbr.hashCode());
        result = prime * result + ((orderNbr == null) ? 0 : orderNbr.hashCode());
        result = prime * result + ((updById == null) ? 0 : updById.hashCode());
        result = prime * result + ((updTs == null) ? 0 : updTs.hashCode());
        //result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (crteById == null) {
            if (other.crteById != null)
                return false;
        } else if (!crteById.equals(other.crteById))
            return false;
        if (crteTs == null) {
            if (other.crteTs != null)
                return false;
        } else if (!crteTs.equals(other.crteTs))
            return false;
        if (customerNbr == null) {
            if (other.customerNbr != null)
                return false;
        } else if (!customerNbr.equals(other.customerNbr))
            return false;
        if (entryAmt == null) {
            if (other.entryAmt != null)
                return false;
        } else if (!entryAmt.equals(other.entryAmt))
            return false;
        if (entryNbr == null) {
            if (other.entryNbr != null)
                return false;
        } else if (!entryNbr.equals(other.entryNbr))
            return false;
        if (orderNbr == null) {
            if (other.orderNbr != null)
                return false;
        } else if (!orderNbr.equals(other.orderNbr))
            return false;
        if (updById == null) {
            if (other.updById != null)
                return false;
        } else if (!updById.equals(other.updById))
            return false;
        if (updTs == null) {
            if (other.updTs != null)
                return false;
        } else if (!updTs.equals(other.updTs))
            return false;
        // if (version == null) {
        //     if (other.version != null)
        //         return false;
        // } else if (!version.equals(other.version))
        //     return false;
        return true;
    }

 
}
