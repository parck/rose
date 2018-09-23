package cn.edots.rose.role;

import cn.edots.ormosia.service.DomainService;
import cn.edots.rose.element.Element;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface RoleService extends DomainService<Long, Role> {

    List<Element> elements(Long roleId, Criterion... criteria);

    Role byName(String name, Criterion... criteria);


}
