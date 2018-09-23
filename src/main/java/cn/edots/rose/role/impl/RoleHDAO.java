package cn.edots.rose.role.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.element.Element;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RoleHDAO<R extends Role> extends DomainHDAO<Long, R> implements RoleDAO<R> {

    @Transactional(rollbackFor = {Exception.class})
    public R get(String username, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append(type.getSimpleName());
        sql.append(" WHERE ");
        sql.append("     (cellphone = :username OR username = :username) ");
        sql.append("     AND password = :password");
        return (R) sessionFactory.getCurrentSession()
                .createQuery(sql.toString())
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Transactional(rollbackFor = {Exception.class})
    public R getById(Long roleId, Criterion... criteria) {
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(type.getSimpleName());
        criterion.add(Restrictions.eq("id", roleId));
        criterion.createAlias("elements", "element", CriteriaSpecification.LEFT_JOIN);
        if (criteria != null) for (Criterion c : criteria) criterion.add(c);
        return (R) criterion.uniqueResult();
    }
}
