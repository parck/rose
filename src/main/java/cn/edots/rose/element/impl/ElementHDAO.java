package cn.edots.rose.element.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.element.Element;
import cn.edots.rose.element.ElementDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ElementHDAO extends DomainHDAO<Long, Element> implements ElementDAO<Element> {

    @Transactional(rollbackFor = {Exception.class})
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

    @Transactional(rollbackFor = {Exception.class})
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

    @Transactional(rollbackFor = {Exception.class})
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

    @Transactional(rollbackFor = {Exception.class})
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
