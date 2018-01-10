package cn.edots.rose.role;

import cn.edots.ormosia.model.Domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017-12-06.
 */
@MappedSuperclass
public abstract class Role extends Domain {

    private static final long serialVersionUID = -3436005697269313872L;

    private String username;
    private String password;
    private int value;

    @Column(name = "username", length = 24, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", length = 64, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
