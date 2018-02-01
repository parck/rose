package cn.edots.rose.role;

import cn.edots.ormosia.dao.DomainDAO;
import cn.edots.rose.role.Role;

public interface RoleDAO<R extends Role> extends DomainDAO<Long, R> {

    R get(String username, String password);
}
