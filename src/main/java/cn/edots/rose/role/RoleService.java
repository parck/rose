package cn.edots.rose.role;

import cn.edots.ormosia.service.DomainService;
import cn.edots.rose.element.Element;
import cn.edots.rose.role.Role;

import java.util.List;

public interface RoleService<R extends Role> extends DomainService<Long, R> {

    R login(String username, String password);

    List<Element> elements(R role);

}
