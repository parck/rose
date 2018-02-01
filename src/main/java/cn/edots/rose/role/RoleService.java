package cn.edots.rose.role;

import cn.edots.ormosia.service.DomainService;
import cn.edots.rose.role.Role;

public interface RoleService<R extends Role> extends DomainService<Long, R> {

    R login(String username, String password);

}
