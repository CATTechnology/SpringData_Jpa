package top.takefly.Jpa.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import top.takefly.Jpa.pojo.Customer;
import top.takefly.Jpa.pojo.LinkMan;

import java.util.Set;

/**
 * @program: Jpa_Hibernate
 * @description: dao接口测试
 * @author: 戴灵飞
 * @create: 2019-07-31 15:01
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LkmManDao lkmManDao;

    @Test
    @Transactional
    @Rollback(false)
    public void saveTest(){
        Customer customer = new Customer("王五" , "高中" , "it教育" , "老师" , "深圳","15886449241");
        LinkMan linkMan = new LinkMan("王上","男","88888888");

        customer.getLinkmans().add(linkMan);
        linkMan.setCustomer(customer);
        customerDao.save(customer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveAndUpdateTest(){
//        Customer customer = new Customer("消炎" , "高中" , "it教育" , "老师" , "深圳","15886449241");
        Customer customer = customerDao.findOne(2L);
        LinkMan linkMan = new LinkMan("上火2","女","9999999");

        linkMan.setCustomer(customer);
        lkmManDao.save(linkMan );
    }

    @Test
    @Transactional
    @Rollback(false)
    public void OnetoManyFindTest(){
        Customer customer = customerDao.findOne(2L);
        System.out.println(customer);

        Set<LinkMan> linkmans = customer.getLinkmans();
        for (LinkMan linkman : linkmans) {
            System.out.println(linkman);
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void ManytoOneFindTest(){
        LinkMan linkMan = lkmManDao.findOne(2L);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }


    /**
     * 连接删除
     */
    @Test
    @Transactional
    @Rollback(false)
    public void CustomerDaodeleteCascadeTest(){
        customerDao.delete(3L);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void LkmMandeleteCascadeTest(){
        lkmManDao.delete(2L);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void findAndSave(){
        
    }

}
