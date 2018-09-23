package cn.edots.rose.role.impl;

import cn.edots.ormosia.dao.DomainDAO;
import cn.edots.ormosia.service.DomainServiceBean;
import cn.edots.rose.element.Element;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import cn.edots.rose.role.RoleService;
import org.hibernate.criterion.Criterion;

import javax.annotation.Resource;
import java.util.List;

public class AbsRoleServiceBean extends DomainServiceBean<Long, Role> implements RoleService {

    @Resource
    private RoleDAO roleHDAO;

    public DomainDAO<Long, Role> getEntityDAO() {
        return roleHDAO;
    }

    public List<Element> elements(Long roleId, Criterion... criteria) {
        Role role = ((RoleDAO) getEntityDAO()).getById(roleId, criteria);
        if (role == null) return null;
        return role.getElements();
    }

    public Role byName(String name, Criterion... criteria) {
        return roleHDAO.getByName(name, criteria);
    }
}
