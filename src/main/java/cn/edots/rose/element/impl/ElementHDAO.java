package cn.edots.rose.element.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.element.Element;
import cn.edots.rose.element.ElementDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ElementHDAO extends DomainHDAO<Long, Element> implements ElementDAO<Element> {

}
