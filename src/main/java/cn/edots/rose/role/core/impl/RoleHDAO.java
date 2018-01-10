package cn.edots.rose.role.core.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.core.RoleDAO;

public class RoleHDAO<R extends Role> extends DomainHDAO<Long, R> implements RoleDAO<R> {

    public R get(String username, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM " + type.getSimpleName());
        sql.append(" WHERE ");
        sql.append("     (cellphone = :username OR username = :username) ");
        sql.append("     AND password = :password");
        return (R) sessionFactory.getCurrentSession()
                .createQuery(sql.toString())
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }
}
