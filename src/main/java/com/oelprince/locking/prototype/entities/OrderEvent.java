package com.oelprince.locking.prototype.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ORDER_EVENT")
public class OrderEvent {

    private String eventId;
    private String objId;
    private String actionCd;

    private String crteById;
    private String updById;
    private Timestamp crteTs;
    private Timestamp updTs;

    @Id
    @Column(name = "cl_bill_event", nullable = false, length = 34)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Column(name = "obj_id", nullable = false, length = 20)
    public String getObjId() {
        return objId;
    }


    public void setObjId(String objId) {
        this.objId = objId;
    }

    @Column(name = "action_cd", nullable = false, length = 10)
    public String getActionCd() {
        return actionCd;
    }

    public void setActionCd(String actionCd) {
        this.actionCd = actionCd;
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


}
