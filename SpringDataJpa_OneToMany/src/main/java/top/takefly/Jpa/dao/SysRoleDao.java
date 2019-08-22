package top.takefly.Jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.takefly.Jpa.pojo.SysRole;

public interface SysRoleDao extends JpaRepository<SysRole, Long> , JpaSpecificationExecutor<SysRole> {

}
