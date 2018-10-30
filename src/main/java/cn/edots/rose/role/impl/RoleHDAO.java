package cn.edots.rose.role.impl;

import cn.edots.ormosia.dao.DomainHDAO;
import cn.edots.rose.element.Element;
import cn.edots.rose.role.Role;
import cn.edots.rose.role.RoleDAO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class RoleHDAO extends DomainHDAO<Long, Role> implements RoleDAO {

    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Role getById(Long roleId, Criterion... criteria) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("     role.id             AS roleId,");
        sql.append("     role.dateCreated    AS roleDateCreated,");
        sql.append("     role.deleted        AS roleDeleted,");
        sql.append("     role.lastUpdated    AS roleLastUpdated,");
        sql.append("     role.uuid           AS roleUuid,");
        sql.append("     role.version        AS roleVersion,");
        sql.append("     role.name           AS roleName,");
        sql.append("     element.id          AS elementId,");
        sql.append("     element.dateCreated AS elementDateCreated,");
        sql.append("     element.deleted     AS elementDeleted,");
        sql.append("     element.lastUpdated AS elementLastUpdated,");
        sql.append("     element.uuid        AS elementUuid,");
        sql.append("     element.version     AS elementVersion,");
        sql.append("     element.active      AS elementActive,");
        sql.append("     element.icon        AS elementIcon,");
        sql.append("     element.label       AS elementLabel,");
        sql.append("     element.link        AS elementLink,");
        sql.append("     element.sequence    AS elementSequence,");
        sql.append("     child.id            AS childId,");
        sql.append("     child.dateCreated   AS childDateCreated,");
        sql.append("     child.deleted       AS childDeleted,");
        sql.append("     child.lastUpdated   AS childLastUpdated,");
        sql.append("     child.uuid          AS childUuid,");
        sql.append("     child.version       AS childVersion,");
        sql.append("     child.active        AS childActive,");
        sql.append("     child.icon          AS childIcon,");
        sql.append("     child.label         AS childLabel,");
        sql.append("     child.link          AS childLink,");
        sql.append("     child.parent_id     AS childParentId,");
        sql.append("     child.sequence      AS childSequence");
        sql.append(" FROM");
        sql.append("     role_tbl AS role");
        sql.append(" LEFT JOIN");
        sql.append("     role_element_tbl AS re");
        sql.append(" ON  role.id = re.role_id");
        sql.append(" LEFT JOIN");
        sql.append("     element_tbl AS element");
        sql.append(" ON  element.id = re.element_id");
        sql.append(" LEFT JOIN");
        sql.append("     element_children_tbl AS ec");
        sql.append(" ON  element.id = ec.parent_id");
        sql.append(" LEFT JOIN");
        sql.append("     element_tbl AS child");
        sql.append(" ON  ec.child_id = child.id");
        sql.append(" WHERE");
        sql.append("     role.id = :id");
        sql.append(" AND element.parent_id IS NULL");
        sql.append(" ORDER BY");
        sql.append("     element.sequence ASC,");
        sql.append("     child.sequence ASC");

        NativeQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", roleId);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.addScalar("roleId", LongType.INSTANCE);
        query.addScalar("roleDateCreated", DateType.INSTANCE);
        query.addScalar("roleDeleted", BooleanType.INSTANCE);
        query.addScalar("roleLastUpdated", DateType.INSTANCE);
        query.addScalar("roleUuid", StringType.INSTANCE);
        query.addScalar("roleVersion", IntegerType.INSTANCE);
        query.addScalar("roleName", StringType.INSTANCE);
        query.addScalar("elementId", LongType.INSTANCE);
        query.addScalar("elementDateCreated", DateType.INSTANCE);
        query.addScalar("elementDeleted", BooleanType.INSTANCE);
        query.addScalar("elementLastUpdated", DateType.INSTANCE);
        query.addScalar("elementUuid", StringType.INSTANCE);
        query.addScalar("elementVersion", IntegerType.INSTANCE);
        query.addScalar("elementActive", BooleanType.INSTANCE);
        query.addScalar("elementIcon", StringType.INSTANCE);
        query.addScalar("elementLabel", StringType.INSTANCE);
        query.addScalar("elementLink", StringType.INSTANCE);
        query.addScalar("elementSequence", IntegerType.INSTANCE);
        query.addScalar("childId", LongType.INSTANCE);
        query.addScalar("childDateCreated", DateType.INSTANCE);
        query.addScalar("childDeleted", BooleanType.INSTANCE);
        query.addScalar("childLastUpdated", DateType.INSTANCE);
        query.addScalar("childUuid", StringType.INSTANCE);
        query.addScalar("childVersion", IntegerType.INSTANCE);
        query.addScalar("childActive", BooleanType.INSTANCE);
        query.addScalar("childIcon", StringType.INSTANCE);
        query.addScalar("childLabel", StringType.INSTANCE);
        query.addScalar("childLink", StringType.INSTANCE);
        query.addScalar("childParentId", LongType.INSTANCE);
        query.addScalar("childSequence", IntegerType.INSTANCE);

        List<Map<String, Object>> list = query.list();
        Role role = null;
        Map<Object, Element> elements = new LinkedHashMap<Object, Element>();
        for (Map<String, Object> map : list) {
            if (role == null) {
                role = new Role();
                role.setId((Long) map.get("roleId"));
                role.setDateCreated((Date) map.get("roleDateCreated"));
                role.setLastUpdated((Date) map.get("roleLastUpdated"));
                role.setUuid((String) map.get("roleUuid"));
                role.setDeleted((Boolean) map.get("roleDeleted"));
                role.setVersion((Integer) map.get("roleVersion"));
                role.setName((String) map.get("roleName"));
            }

            if (map.get("elementId") != null) {
                Element element = elements.get(map.get("elementId"));
                if (element == null) {
                    element = new Element();
                    element.setId((Long) map.get("elementId"));
                    element.setDateCreated((Date) map.get("elementDateCreated"));
                    element.setLastUpdated((Date) map.get("elementLastUpdated"));
                    element.setDeleted((Boolean) map.get("elementDeleted"));
                    element.setUuid((String) map.get("elementUuid"));
                    element.setVersion((Integer) map.get("elementVersion"));
                    element.setActive((Boolean) map.get("elementActive"));
                    element.setIcon((String) map.get("elementIcon"));
                    element.setLabel((String) map.get("elementLabel"));
                    element.setLink((String) map.get("elementLink"));
                    element.setSequence((Integer) map.get("elementSequence"));
                    elements.put(map.get("elementId"), element);
                }

                if (map.get("childId") != null) {
                    Set<Element> children = element.getChildren();
                    if (children == null) children = new HashSet<Element>();
                    Element child = new Element();
                    child.setId((Long) map.get("childId"));
                    child.setDateCreated((Date) map.get("childDateCreated"));
                    child.setLastUpdated((Date) map.get("childLastUpdated"));
                    child.setDeleted((Boolean) map.get("childDeleted"));
                    child.setUuid((String) map.get("childUuid"));
                    child.setVersion((Integer) map.get("childVersion"));
                    child.setActive((Boolean) map.get("childActive"));
                    child.setIcon((String) map.get("childIcon"));
                    child.setLabel((String) map.get("childLabel"));
                    child.setLink((String) map.get("childLink"));
                    child.setSequence((Integer) map.get("childSequence"));
                    children.add(child);
                    element.setChildren(children);
                }
            }
        }
        if (role != null) role.setElements(new ArrayList<Element>(elements.values()));
        return role;
    }

    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Role getByName(String name, Criterion... criteria) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("     role.id             AS roleId,");
        sql.append("     role.dateCreated    AS roleDateCreated,");
        sql.append("     role.deleted        AS roleDeleted,");
        sql.append("     role.lastUpdated    AS roleLastUpdated,");
        sql.append("     role.uuid           AS roleUuid,");
        sql.append("     role.version        AS roleVersion,");
        sql.append("     role.name           AS roleName,");
        sql.append("     element.id          AS elementId,");
        sql.append("     element.dateCreated AS elementDateCreated,");
        sql.append("     element.deleted     AS elementDeleted,");
        sql.append("     element.lastUpdated AS elementLastUpdated,");
        sql.append("     element.uuid        AS elementUuid,");
        sql.append("     element.version     AS elementVersion,");
        sql.append("     element.active      AS elementActive,");
        sql.append("     element.icon        AS elementIcon,");
        sql.append("     element.label       AS elementLabel,");
        sql.append("     element.link        AS elementLink,");
        sql.append("     element.sequence    AS elementSequence,");
        sql.append("     child.id            AS childId,");
        sql.append("     child.dateCreated   AS childDateCreated,");
        sql.append("     child.deleted       AS childDeleted,");
        sql.append("     child.lastUpdated   AS childLastUpdated,");
        sql.append("     child.uuid          AS childUuid,");
        sql.append("     child.version       AS childVersion,");
        sql.append("     child.active        AS childActive,");
        sql.append("     child.icon          AS childIcon,");
        sql.append("     child.label         AS childLabel,");
        sql.append("     child.link          AS childLink,");
        sql.append("     child.parent_id     AS childParentId,");
        sql.append("     child.sequence      AS childSequence");
        sql.append(" FROM");
        sql.append("     role_tbl AS role");
        sql.append(" LEFT JOIN");
        sql.append("     role_element_tbl AS re");
        sql.append(" ON  role.id = re.role_id");
        sql.append(" LEFT JOIN");
        sql.append("     element_tbl AS element");
        sql.append(" ON  element.id = re.element_id");
        sql.append(" LEFT JOIN");
        sql.append("     element_children_tbl AS ec");
        sql.append(" ON  element.id = ec.parent_id");
        sql.append(" LEFT JOIN");
        sql.append("     element_tbl AS child");
        sql.append(" ON  ec.child_id = child.id");
        sql.append(" WHERE");
        sql.append("     role.name = :name");
        sql.append(" AND element.parent_id IS NULL");
        sql.append(" ORDER BY");
        sql.append("     element.sequence ASC,");
        sql.append("     child.sequence ASC");

        NativeQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("name", name);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.addScalar("roleId", LongType.INSTANCE);
        query.addScalar("roleDateCreated", DateType.INSTANCE);
        query.addScalar("roleDeleted", BooleanType.INSTANCE);
        query.addScalar("roleLastUpdated", DateType.INSTANCE);
        query.addScalar("roleUuid", StringType.INSTANCE);
        query.addScalar("roleVersion", IntegerType.INSTANCE);
        query.addScalar("roleName", StringType.INSTANCE);
        query.addScalar("elementId", LongType.INSTANCE);
        query.addScalar("elementDateCreated", DateType.INSTANCE);
        query.addScalar("elementDeleted", BooleanType.INSTANCE);
        query.addScalar("elementLastUpdated", DateType.INSTANCE);
        query.addScalar("elementUuid", StringType.INSTANCE);
        query.addScalar("elementVersion", IntegerType.INSTANCE);
        query.addScalar("elementActive", BooleanType.INSTANCE);
        query.addScalar("elementIcon", StringType.INSTANCE);
        query.addScalar("elementLabel", StringType.INSTANCE);
        query.addScalar("elementLink", StringType.INSTANCE);
        query.addScalar("elementSequence", IntegerType.INSTANCE);
        query.addScalar("childId", LongType.INSTANCE);
        query.addScalar("childDateCreated", DateType.INSTANCE);
        query.addScalar("childDeleted", BooleanType.INSTANCE);
        query.addScalar("childLastUpdated", DateType.INSTANCE);
        query.addScalar("childUuid", StringType.INSTANCE);
        query.addScalar("childVersion", IntegerType.INSTANCE);
        query.addScalar("childActive", BooleanType.INSTANCE);
        query.addScalar("childIcon", StringType.INSTANCE);
        query.addScalar("childLabel", StringType.INSTANCE);
        query.addScalar("childLink", StringType.INSTANCE);
        query.addScalar("childParentId", LongType.INSTANCE);
        query.addScalar("childSequence", IntegerType.INSTANCE);

        List<Map<String, Object>> list = query.list();
        Role role = null;
        Map<Object, Element> elements = new LinkedHashMap<Object, Element>();
        for (Map<String, Object> map : list) {
            if (role == null) {
                role = new Role();
                role.setId((Long) map.get("roleId"));
                role.setDateCreated((Date) map.get("roleDateCreated"));
                role.setLastUpdated((Date) map.get("roleLastUpdated"));
                role.setUuid((String) map.get("roleUuid"));
                role.setDeleted((Boolean) map.get("roleDeleted"));
                role.setVersion((Integer) map.get("roleVersion"));
                role.setName((String) map.get("roleName"));
            }

            if (map.get("elementId") != null) {
                Element element = elements.get(map.get("elementId"));
                if (element == null) {
                    element = new Element();
                    element.setId((Long) map.get("elementId"));
                    element.setDateCreated((Date) map.get("elementDateCreated"));
                    element.setLastUpdated((Date) map.get("elementLastUpdated"));
                    element.setDeleted((Boolean) map.get("elementDeleted"));
                    element.setUuid((String) map.get("elementUuid"));
                    element.setVersion((Integer) map.get("elementVersion"));
                    element.setActive((Boolean) map.get("elementActive"));
                    element.setIcon((String) map.get("elementIcon"));
                    element.setLabel((String) map.get("elementLabel"));
                    element.setLink((String) map.get("elementLink"));
                    element.setSequence((Integer) map.get("elementSequence"));
                    elements.put(map.get("elementId"), element);
                }

                if (map.get("childId") != null) {
                    Set<Element> children = element.getChildren();
                    if (children == null) children = new HashSet<Element>();
                    Element child = new Element();
                    child.setId((Long) map.get("childId"));
                    child.setDateCreated((Date) map.get("childDateCreated"));
                    child.setLastUpdated((Date) map.get("childLastUpdated"));
                    child.setDeleted((Boolean) map.get("childDeleted"));
                    child.setUuid((String) map.get("childUuid"));
                    child.setVersion((Integer) map.get("childVersion"));
                    child.setActive((Boolean) map.get("childActive"));
                    child.setIcon((String) map.get("childIcon"));
                    child.setLabel((String) map.get("childLabel"));
                    child.setLink((String) map.get("childLink"));
                    child.setSequence((Integer) map.get("childSequence"));
                    children.add(child);
                    element.setChildren(children);
                }
            }
        }
        if (role != null) role.setElements(new ArrayList<Element>(elements.values()));
        return role;
    }
}
