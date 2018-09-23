package cn.edots.rose.role.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleHDAO extends DomainHDAO<Long, Role> implements RoleDAO {

    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Role get(String username, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append(Role.class.getSimpleName());
        sql.append(" WHERE ");
        sql.append("     (username = :username OR username = :username) ");
        sql.append("     AND password = :password");
        return (Role) sessionFactory.getCurrentSession()
                .createQuery(sql.toString())
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Role getById(Long roleId, Criterion... criteria) {
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(Role.class);
        criterion.add(Restrictions.eq("id", roleId));
        criterion.createAlias("elements", "element", CriteriaSpecification.LEFT_JOIN);
        if (criteria != null) for (Criterion c : criteria) criterion.add(c);
        return (Role) criterion.uniqueResult();
    }
}
