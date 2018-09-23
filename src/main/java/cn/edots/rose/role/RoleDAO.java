package cn.edots.rose.role;

import cn.edots.ormosia.dao.DomainDAO;
import org.hibernate.criterion.Criterion;

public interface RoleDAO extends DomainDAO<Long, Role> {

    Role get(String username, String password);

    Role getById(Long roleId, Criterion... criteria);
}
