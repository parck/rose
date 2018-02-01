package cn.edots.rose.role.element;

import cn.edots.ormosia.service.DomainService;
import cn.edots.rose.role.Role;

import java.util.List;

public interface ElementService extends DomainService<Long, Element> {

    boolean set(Role role, List<Long> resIds);

    boolean set(Role role, Long resId);

    boolean cancel(Role role, List<Long> resIds);

    boolean cancel(Role role, Long resId);
}
