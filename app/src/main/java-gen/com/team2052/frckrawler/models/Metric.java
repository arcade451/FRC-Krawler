package com.team2052.frckrawler.models;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "METRIC".
 */
@Entity(active = true)
public class Metric implements java.io.Serializable {

    @Id(autoincrement = true)
    @Unique
    private Long id;
    private String name;
    private Integer category;
    private Integer type;
    private String data;
    private boolean enabled;
    private int priority;

    /**
     * Used to resolve relations
     */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient MetricDao myDao;

    @ToMany(joinProperties = {
            @JoinProperty(name = "id", referencedName = "metric_id")
    })
    private List<MatchDatum> matchDatumList;

    @ToMany(joinProperties = {
            @JoinProperty(name = "id", referencedName = "metric_id")
    })
    private List<PitDatum> pitDatumList;

    @Generated
    public Metric() {
    }

    public Metric(Long id) {
        this.id = id;
    }

    @Generated
    public Metric(Long id, String name, Integer category, Integer type, String data, boolean enabled, int priority) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
        this.data = data;
        this.enabled = enabled;
        this.priority = priority;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMetricDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<MatchDatum> getMatchDatumList() {
        if (matchDatumList == null) {
            __throwIfDetached();
            MatchDatumDao targetDao = daoSession.getMatchDatumDao();
            List<MatchDatum> matchDatumListNew = targetDao._queryMetric_MatchDatumList(id);
            synchronized (this) {
                if (matchDatumList == null) {
                    matchDatumList = matchDatumListNew;
                }
            }
        }
        return matchDatumList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetMatchDatumList() {
        matchDatumList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<PitDatum> getPitDatumList() {
        if (pitDatumList == null) {
            __throwIfDetached();
            PitDatumDao targetDao = daoSession.getPitDatumDao();
            List<PitDatum> pitDatumListNew = targetDao._queryMetric_PitDatumList(id);
            synchronized (this) {
                if (pitDatumList == null) {
                    pitDatumList = pitDatumListNew;
                }
            }
        }
        return pitDatumList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetPitDatumList() {
        pitDatumList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}
