package cn.edots.rose.element;

import cn.edots.ormosia.dao.DomainDAO;

import java.util.List;

public interface ElementDAO<RES extends Element> extends DomainDAO<Long, RES> {

}
