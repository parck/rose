package cn.edots.rose.role.impl;

import cn.edots.ormosia.dao.DomainDAO;
import cn.edots.ormosia.service.DomainServiceBean;
import cn.edots.rose.element.Element;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import cn.edots.rose.role.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceBean extends DomainServiceBean<Long, Role> implements RoleService {

    @Resource
    private RoleDAO roleDAO;

    public DomainDAO<Long, Role> getEntityDAO() {
        return roleDAO;
    }

    public Role login(String username, String password) {
        return ((RoleDAO) getEntityDAO()).get(username, password);
    }

    public List<Element> elements(Role role) {
        role = ((RoleDAO) getEntityDAO()).getById(role.getId());
        if (role == null) return null;
        return role.getElements();
    }
}
