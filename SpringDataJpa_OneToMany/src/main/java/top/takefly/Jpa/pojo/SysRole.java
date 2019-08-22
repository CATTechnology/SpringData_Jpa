package top.takefly.Jpa.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: Jpa_Hibernate
 * @description: 角色实体
 * @author: 戴灵飞
 * @create: 2019-07-31 16:46
 **/
@Entity
@Table(name="sys_role")
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_memo")
    private String roleMemo;

    /**
     * 多对多关系映射
     */
    @ManyToMany
    @JoinTable(
            name="user_role_rel",
            joinColumns = {@JoinColumn(name="role_id" , referencedColumnName = "role_id")},
            inverseJoinColumns = {@JoinColumn(name="user_id" , referencedColumnName = "user_id")}
    )
    private Set<SysUser> users = new HashSet<SysUser>(0);

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }

    public Set<SysUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SysUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleMemo='" + roleMemo + '\'' +
                '}';
    }
}
