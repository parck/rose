package cn.edots.rose.role.resource;

import cn.edots.ormosia.model.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resource_tbl")
public class Resource extends Domain {

    private static final long serialVersionUID = -5336647483187775872L;

    private Resource parent;
    private String label;
    private String icon;
    private String link;
    private boolean active;
    private int sequence;
    private int roles;

    private List<Resource> children;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
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

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    @Transient
    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }
}
