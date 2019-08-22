package top.takefly.test.jpa.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.takefly.Jpa.dao.CustomerDao;
import top.takefly.Jpa.pojo.Customer;

import java.util.List;

/**
 * @program: Jpa_Hibernate
 * @description: 方法命名规则查询
 * @author: 戴灵飞
 * @create: 2019-07-31 11:15
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CustomerDaoMethodNamingRuleQueryTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
//    @Transactional
    public void findCustomerByCustAddressAndAndCustLevel_Test(){
        List<Customer> customer = customerDao.findCustomerByCustAddressLikeAndCustLevel("杭州%" , "1");
        for (Customer customer1 : customer) {
            System.out.println(customer1);
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void removeCustomerByAddressTest(){
        Integer rows = customerDao.removeCustomerByCustAddress("杭州2");
        System.out.println(rows);
    }


    @Test
    @Transactional
    public void findAllByCustSourceIsNullTest(){
        List<Customer> rows = customerDao.findAllByCustSourceIsNull();
        for (Customer row : rows) {
            System.out.println(row);
        }
    }



}
