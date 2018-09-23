package cn.edots.rose.role.impl;

import cn.edots.ormosia.service.DomainServiceBean;
import cn.edots.rose.element.Element;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import cn.edots.rose.role.RoleService;
import org.hibernate.criterion.Criterion;

import java.util.List;

public abstract class AbsRoleServiceBean extends DomainServiceBean<Long, Role> implements RoleService {

    public List<Element> elements(Long roleId, Criterion... criteria) {
        Role role = ((RoleDAO) getEntityDAO()).getById(roleId, criteria);
        if (role == null) return null;
        return role.getElements();
    }

    public Role byName(String name, Criterion... criteria) {
        return ((RoleDAO) getEntityDAO()).getByName(name, criteria);
    }
}
