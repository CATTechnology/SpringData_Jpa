package top.takefly.Jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.takefly.Jpa.pojo.SysUser;

public interface SysUserDao extends JpaRepository<SysUser, Long> , JpaSpecificationExecutor<SysUser> {

}
