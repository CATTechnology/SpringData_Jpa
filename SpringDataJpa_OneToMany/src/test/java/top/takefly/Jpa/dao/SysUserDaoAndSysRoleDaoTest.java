package top.takefly.Jpa.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.takefly.Jpa.pojo.SysRole;
import top.takefly.Jpa.pojo.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @program: Jpa_Hibernate
 * @description: SysRoleDao和SysUserDao组合
 * @author: 戴灵飞
 * @create: 2019-07-31 17:10
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SysUserDaoAndSysRoleDaoTest {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;


    @Test
    @Transactional
    @Rollback(false)
    public void saveTest(){
        SysRole role1 = new SysRole();
        role1.setRoleMemo("最高权限");
        role1.setRoleName("Admin");

        SysRole role2 = new SysRole();
        role2.setRoleMemo("普通权限");
        role2.setRoleName("user");

        SysRole role3 = new SysRole();
        role3.setRoleMemo("游客权限");
        role3.setRoleName("guest");

        SysUser user1 = new SysUser();
        user1.setUserName("zhangsan");
        user1.setUserPassword("980518");
        user1.setUserState("1");

        SysUser user2 = new SysUser();
        user2.setUserName("wangwu");
        user2.setUserPassword("980518");
        user2.setUserState("1");

        SysUser user3 = new SysUser();
        user3.setUserName("xiaolei");
        user3.setUserPassword("980518");
        user3.setUserState("1");

        //设置对应关系
        Set<SysUser> users = role1.getUsers();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        user1.getRoles().add(role3);

        role2.getUsers().add(user1);
        role2.getUsers().add(user2);
        role2.getUsers().add(user3);

        user2.getRoles().add(role1);
        user2.getRoles().add(role2);
        user2.getRoles().add(role3);

        role3.getUsers().add(user1);
        role3.getUsers().add(user2);
        role3.getUsers().add(user3);

        user3.getRoles().add(role1);
        user3.getRoles().add(role2);
        user3.getRoles().add(role3);

        sysUserDao.save(user1);
        sysUserDao.save(user2);
        sysUserDao.save(user3);

//        sysRoleDao.save(role1);
//        sysRoleDao.save(role2);
//        sysRoleDao.save(role3);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteTest(){
        sysRoleDao.delete(1L);
    }

    @Test
    @Transactional
    public void findRoleTest(){
        SysRole role = sysRoleDao.findOne(2L);
        System.out.println(role);
        for (SysUser user : role.getUsers()) {
            System.out.println(user);
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findUserTest(){
        SysUser user = sysUserDao.findOne(2L);
        System.out.println(user);
        for (SysRole role : user.getRoles()) {
            System.out.println(role);
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveCascadeTest(){
        SysRole role1 = new SysRole();
        role1.setRoleMemo("最高权限");
        role1.setRoleName("Admin");

        SysRole role2 = new SysRole();
        role2.setRoleMemo("普通权限");
        role2.setRoleName("user");

        SysRole role3 = new SysRole();
        role3.setRoleMemo("游客权限");
        role3.setRoleName("guest");

        SysUser user1 = new SysUser();
        user1.setUserName("zhangsan");
        user1.setUserPassword("980518");
        user1.setUserState("1");

        SysUser user2 = new SysUser();
        user2.setUserName("wangwu");
        user2.setUserPassword("980518");
        user2.setUserState("1");

        SysUser user3 = new SysUser();
        user3.setUserName("xiaolei");
        user3.setUserPassword("980518");
        user3.setUserState("1");

        sysRoleDao.save(role1);
        sysRoleDao.save(role2);
        sysRoleDao.save(role3);

        sysUserDao.save(user1);
        sysUserDao.save(user2);
        sysUserDao.save(user3);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateTest(){
        List<SysUser> users = sysUserDao.findAll();
        List<SysRole> roles = sysRoleDao.findAll();
        for (SysUser user : users) {
            for (SysRole role : roles) {
                user.getRoles().add(role);
                role.getUsers().add(user);
            }
        }

        sysUserDao.save(users);
    }
}
