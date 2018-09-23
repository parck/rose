package cn.edots.rose.role;

import cn.edots.ormosia.model.Domain;
import cn.edots.rose.element.Element;

import javax.persistence.*;
import java.util.List;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017-12-06.
 */
@Entity
@Table(name = "role_tbl")
public class Role extends Domain {

    private static final long serialVersionUID = -3436005697269313872L;

    private String name;
    private List<Element> elements;

    @Column(name = "name", length = 20, nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "role_element_tbl", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "element_id"))
    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
