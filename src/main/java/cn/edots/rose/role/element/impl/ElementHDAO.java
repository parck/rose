package cn.edots.rose.role.element.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.role.element.Element;
import cn.edots.rose.role.element.ElementDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ElementHDAO extends DomainHDAO<Long, Element> implements ElementDAO<Element> {

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
        hql.append(" SET roles = (roles &~ :value)");
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
        hql.append(" SET roles = (roles &~ :value)");
        hql.append(" WHERE id = :resId");
        return sessionFactory.getCurrentSession()
                .createSQLQuery(hql.toString())
                .setParameter("value", value)
                .setParameter("resId", resId)
                .executeUpdate() == 1;
    }
}
