package cn.edots.rose.element;

import cn.edots.ormosia.dao.DomainDAO;

import java.util.List;

public interface ElementDAO<RES extends Element> extends DomainDAO<Long, RES> {


    int set(int value, List<Long> resIds);

    boolean set(int value, Long resId);

    int cancel(int value, List<Long> resIds);

    boolean cancel(int value, Long resId);
}
