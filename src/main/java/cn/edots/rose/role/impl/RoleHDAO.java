package cn.edots.rose.role.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import org.hibernate.Criteria;
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
        criterion.createAlias("elements", "element", CriteriaSpecification.LEFT_JOIN);
        criterion.createAlias("element.children", "child", CriteriaSpecification.LEFT_JOIN);
        if (criteria != null) for (Criterion c : criteria) criterion.add(c);
        criterion.add(Restrictions.isNull("element.parent"));
        criterion.addOrder(Order.asc("element.sequence"));
        return (Role) criterion.uniqueResult();
    }

    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Role getByName(String name, Criterion... criteria) {
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(Role.class);
        criterion.add(Restrictions.eq("name", name));
        criterion.createAlias("elements", "element", CriteriaSpecification.LEFT_JOIN);
        criterion.createAlias("element.children", "child", CriteriaSpecification.LEFT_JOIN);
        if (criteria != null) for (Criterion c : criteria) criterion.add(c);
        criterion.add(Restrictions.isNull("element.parent"));
        criterion.addOrder(Order.asc("element.sequence"));
        return (Role) criterion.uniqueResult();
    }
}
