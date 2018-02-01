package cn.edots.rose.role.impl;

import cn.edots.ormosia.service.DomainServiceBean;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import cn.edots.rose.role.RoleService;

public abstract class RoleServiceBean<R extends Role> extends DomainServiceBean<Long, R> implements RoleService<R> {

    public R login(String username, String password) {
        return ((RoleDAO<R>) getEntityDAO()).get(username, password);
    }
}
