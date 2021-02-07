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


    /**
     *
     */
    private static final long serialVersionUID = -619395132336105543L;

    //private Timestamp version;
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
        result = prime * result + ((customerFilerCd == null) ? 0 : customerFilerCd.hashCode());
        result = prime * result + ((customerNbr == null) ? 0 : customerNbr.hashCode());
        result = prime * result + ((customerStusCd == null) ? 0 : customerStusCd.hashCode());
        result = prime * result + ((customerTotlAmt == null) ? 0 : customerTotlAmt.hashCode());
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
        Customer other = (Customer) obj;
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
        if (customerFilerCd == null) {
            if (other.customerFilerCd != null)
                return false;
        } else if (!customerFilerCd.equals(other.customerFilerCd))
            return false;
        if (customerNbr == null) {
            if (other.customerNbr != null)
                return false;
        } else if (!customerNbr.equals(other.customerNbr))
            return false;
        if (customerStusCd == null) {
            if (other.customerStusCd != null)
                return false;
        } else if (!customerStusCd.equals(other.customerStusCd))
            return false;
        if (customerTotlAmt == null) {
            if (other.customerTotlAmt != null)
                return false;
        } else if (!customerTotlAmt.equals(other.customerTotlAmt))
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
