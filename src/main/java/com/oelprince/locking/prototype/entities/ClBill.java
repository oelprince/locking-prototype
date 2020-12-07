package com.oelprince.locking.prototype.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
@Table(name = "cl_bill", schema = "billing", catalog = "billing_db")
public class ClBill implements Serializable {
    private String billNbr;
    private String billStusCd;
    private String filerCd;

    @JsonProperty("entryAmt")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal billTotlAmt;

    private String crteById;
    private String updById;
    private Timestamp crteTs;
    private Timestamp updTs;

    @Id
    @Column(name = "bill_nbr", nullable = false, length = 20)
    public String getBillNbr() {
        return billNbr;
    }

    public void setBillNbr(String billNbr) {
        this.billNbr = billNbr;
    }

    @Basic
    @Column(name = "bill_stus_cd", nullable = false, length = 20)
    public String getBillStusCd() {
        return billStusCd;
    }

    public void setBillStusCd(String billStusCd) {
        this.billStusCd = billStusCd;
    }

    @Basic
    @Column(name = "filer_cd", nullable = false, length = 3)
    public String getFilerCd() {
        return filerCd;
    }

    public void setFilerCd(String filerCd) {
        this.filerCd = filerCd;
    }

    @JsonSerialize(using = MoneySerializer.class)
    @Basic
    @Column(name = "bill_totl_amt", nullable = false, precision = 2)
    public BigDecimal getBillTotlAmt() {
        return billTotlAmt;
    }

    public void setBillTotlAmt(BigDecimal billTotlAmt) {
        this.billTotlAmt = billTotlAmt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClBill clBill = (ClBill) o;
        return Objects.equals(billNbr, clBill.billNbr) &&
                Objects.equals(billStusCd, clBill.billStusCd) &&
                Objects.equals(billTotlAmt, clBill.billTotlAmt) &&
                Objects.equals(crteById, clBill.crteById) &&
                Objects.equals(updById, clBill.updById) &&
                Objects.equals(crteTs, clBill.crteTs) &&
                Objects.equals(updTs, clBill.updTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNbr, billStusCd, billTotlAmt, crteById, updById, crteTs, updTs);
    }
}
