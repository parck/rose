package cn.edots.rose.role.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleHDAO extends DomainHDAO<Long, Role> implements RoleDAO {

    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Role getById(Long roleId, Criterion... criteria) {
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(Role.class);
        criterion.add(Restrictions.eq("id", roleId));
        criterion.setFetchMode("elements", FetchMode.JOIN);
        criterion.setFetchMode("elements.children", FetchMode.JOIN);
        if (criteria != null) for (Criterion c : criteria) criterion.add(c);
        criterion.add(Restrictions.isNull("elements.parent"));
        criterion.addOrder(Order.asc("elements.sequence"));
        return (Role) criterion.uniqueResult();
    }

    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Role getByName(String name, Criterion... criteria) {
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(Role.class);
        criterion.add(Restrictions.eq("name", name));
        criterion.setFetchMode("elements", FetchMode.JOIN);
        criterion.setFetchMode("elements.children", FetchMode.JOIN);
        if (criteria != null) for (Criterion c : criteria) criterion.add(c);
        criterion.add(Restrictions.isNull("elements.parent"));
        criterion.addOrder(Order.asc("elements.sequence"));
        return (Role) criterion.uniqueResult();
    }
}
