package cn.edots.rose.element;

import cn.edots.ormosia.model.Domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "element_tbl")
public class Element extends Domain {

    private static final long serialVersionUID = -5336647483187775872L;

    private Element parent;
    private String label;
    private String icon;
    private String link;
    private boolean active;
    private int sequence;
    private Set<Element> children;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Element getParent() {
        return parent;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }

    @Column(name = "label", nullable = false, length = 20)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Column(name = "icon", nullable = true)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "link", nullable = true)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "element_children_tbl", joinColumns = @JoinColumn(name = "parent_id"), inverseJoinColumns = @JoinColumn(name = "child_id"))
    public Set<Element> getChildren() {
        return children;
    }

    public void setChildren(Set<Element> children) {
        this.children = children;
    }
}
