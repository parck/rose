package cn.edots.rose.role;

import cn.edots.ormosia.dao.DomainDAO;
import org.hibernate.criterion.Criterion;

public interface RoleDAO extends DomainDAO<Long, Role> {

    Role getById(Long roleId, Criterion... criteria);

    Role getByName(String name, Criterion... criteria);
}
