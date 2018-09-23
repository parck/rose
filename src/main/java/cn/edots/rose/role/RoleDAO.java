package cn.edots.rose.role;

import cn.edots.ormosia.dao.DomainDAO;
import cn.edots.rose.element.Element;
import cn.edots.rose.role.Role;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface RoleDAO<R extends Role> extends DomainDAO<Long, R> {

    R get(String username, String password);

    R getById(Long roleId, Criterion... criteria);
}
