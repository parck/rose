package cn.edots.rose.role.resource.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.role.resource.Resource;
import cn.edots.rose.role.resource.ResourceDAO;
import org.hibernate.query.criteria.internal.CriteriaUpdateImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaUpdate;
import java.util.List;

@Repository
public class ResourceHDAO extends DomainHDAO<Long, Resource> implements ResourceDAO<Resource> {

    public int set(int value, List<Long> resIds) {
        StringBuilder hql = new StringBuilder();
        hql.append(" UPDATE resource_tbl");
        hql.append(" SET roles = (roles | :value)");
        hql.append(" WHERE id IN :ids");
        return sessionFactory.getCurrentSession()
                .createSQLQuery(hql.toString())
                .setParameter("value", value)
                .setParameter("ids", resIds)
                .executeUpdate();
    }

    public boolean set(int value, Long resId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" UPDATE resource_tbl");
        hql.append(" SET roles = (roles | :value)");
        hql.append(" WHERE id = :resId");
        return sessionFactory.getCurrentSession()
                .createSQLQuery(hql.toString())
                .setParameter("value", value)
                .setParameter("resId", resId)
                .executeUpdate() == 1;
    }

    public int cancel(int value, List<Long> resIds) {
        StringBuilder hql = new StringBuilder();
        hql.append(" UPDATE resource_tbl");
        hql.append(" SET roles = (roles ^ :value)");
        hql.append(" WHERE id IN :ids");
        return sessionFactory.getCurrentSession()
                .createSQLQuery(hql.toString())
                .setParameter("value", value)
                .setParameter("ids", resIds)
                .executeUpdate();
    }

    public boolean cancel(int value, Long resId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" UPDATE resource_tbl");
        hql.append(" SET roles = (roles ^ :value)");
        hql.append(" WHERE id = :resId");
        return sessionFactory.getCurrentSession()
                .createSQLQuery(hql.toString())
                .setParameter("value", value)
                .setParameter("resId", resId)
                .executeUpdate() == 1;
    }
}
