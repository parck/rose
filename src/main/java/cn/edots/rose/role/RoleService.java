package cn.edots.rose.role;

import cn.edots.ormosia.service.DomainService;
import cn.edots.rose.element.Element;

import java.util.List;

public interface RoleService extends DomainService<Long, Role> {

    Role login(String username, String password);

    List<Element> elements(Role role);

}
