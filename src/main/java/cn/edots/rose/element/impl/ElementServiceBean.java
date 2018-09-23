package cn.edots.rose.element.impl;

import cn.edots.ormosia.dao.DomainDAO;
import cn.edots.ormosia.service.DomainServiceBean;
import cn.edots.rose.role.Role;
import cn.edots.rose.element.Element;
import cn.edots.rose.element.ElementDAO;
import cn.edots.rose.element.ElementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ElementServiceBean extends DomainServiceBean<Long, Element> implements ElementService {

    @Resource
    private ElementDAO resourceHDAO;

    public DomainDAO<Long, Element> getEntityDAO() {
        return resourceHDAO;
    }

    public boolean set(Role role, List<Long> resIds) {
        return resourceHDAO.set(role.getValue(), resIds) == resIds.size();
    }

    public boolean set(Role role, Long resId) {
        return resourceHDAO.set(role.getValue(), resId);
    }

    public boolean cancel(Role role, List<Long> resIds) {
        return resourceHDAO.cancel(role.getValue(), resIds) == resIds.size();
    }

    public boolean cancel(Role role, Long resId) {
        return resourceHDAO.cancel(role.getValue(), resId);
    }
}
