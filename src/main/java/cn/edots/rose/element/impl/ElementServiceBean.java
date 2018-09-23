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
}
