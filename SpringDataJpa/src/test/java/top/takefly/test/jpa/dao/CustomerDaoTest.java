package top.takefly.test.jpa.dao;

import org.apache.commons.logging.impl.SLF4JLog;
import org.apache.commons.logging.impl.SLF4JLogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.takefly.Jpa.dao.CustomerDao;
import top.takefly.Jpa.pojo.Customer;

import java.util.List;

/**
 * @program: Jpa_Hibernate
 * @description: 用于测试CustomerDao
 * @author: 戴灵飞
 * @create: 2019-07-30 21:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void findTest() {
        Customer customer = customerDao.findOne(1L);
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveTest() {
        Customer customer = new Customer("戴灵飞", "it教育", "1", "15886449241");
        customer.setCustId(5L);
        customerDao.save(customer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findOneTest() {
        Customer customer = customerDao.findOne(2L);
        System.out.println(customer);
    }

    @Test
    @Transactional
    public void getOneTest() {
        Customer customer = customerDao.getOne(2L);
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateTest() {
        Customer customer = customerDao.getOne(6L);
        customer.setCustAddress("深圳市");
        Customer updateCustomer = customerDao.save(customer);
        System.out.println(updateCustomer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteTest() {
        Customer customer = customerDao.getOne(2L);
        customerDao.delete(customer);
        System.out.println(customer);
    }

    @Test
    @Transactional
    public void customFindTest() {
        List<Customer> customers = customerDao.findCustomerByCustAddress("杭州2");
        System.out.println(customers);
    }

    @Test
    @Transactional
    public void customFindByCustNameAndAddressTest() {
        List<Customer> customers = customerDao.findCustomerByCustNameAndCustAddress("戴灵飞", "深圳市");
        System.out.println(customers);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateCustomTest() {
        Integer rows = customerDao.updateCustomer("张三", 4L);
        System.out.println(rows);
    }

    @Test
    @Transactional
    public void QueryCustomerTest() {
        List<Customer> customer = customerDao.findCustomer();
        for (Customer customer1 : customer) {
            System.out.println(customer1);
        }
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED , isolation = Isolation.REPEATABLE_READ , readOnly = false)
    public void persist() {
        for (int i = 0; i < 100; i++) {
            Customer customer = new Customer();
            customer.setCustAddress("湖南");
            customer.setCustLevel("it教育");
            customer.setCustName("张三");
            customer.setCustSource("教育");
            customer.setCustIndustry("top");
            customerDao.save(customer);
        }
    }


}
