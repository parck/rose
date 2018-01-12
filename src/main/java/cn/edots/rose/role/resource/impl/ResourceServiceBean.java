package cn.edots.rose.role.resource.impl;

import cn.edots.ormosia.dao.DomainDAO;
import cn.edots.ormosia.service.DomainServiceBean;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.resource.Resource;
import cn.edots.rose.role.resource.ResourceDAO;
import cn.edots.rose.role.resource.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceServiceBean extends DomainServiceBean<Long, Resource> implements ResourceService {

    @javax.annotation.Resource
    private ResourceDAO resourceHDAO;

    public DomainDAO<Long, Resource> getEntityDAO() {
        return resourceHDAO;
    }

    @Transactional
    public boolean set(Role role, List<Long> resIds) {
        return resourceHDAO.set(role.getValue(), resIds) == resIds.size();
    }

    @Transactional
    public boolean set(Role role, Long resId) {
        return resourceHDAO.set(role.getValue(), resId);
    }

    @Transactional
    public boolean cancel(Role role, List<Long> resIds) {
        return resourceHDAO.cancel(role.getValue(), resIds) == resIds.size();
    }

    @Transactional
    public boolean cancel(Role role, Long resId) {
        return resourceHDAO.cancel(role.getValue(), resId);
    }
}
