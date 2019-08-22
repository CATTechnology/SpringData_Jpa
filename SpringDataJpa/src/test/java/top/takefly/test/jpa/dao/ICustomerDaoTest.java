package top.takefly.test.jpa.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.takefly.Jpa.dao.ICustomerDao;
import top.takefly.Jpa.pojo.Customer;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @program: Jpa_Hibernate
 * @description: 测试
 * @author: 戴灵飞
 * @create: 2019-08-21 16:51
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ICustomerDaoTest {

    @Autowired
    private ICustomerDao customerDao;

    @Test
    public void CountTest(){
        System.out.println(customerDao.count());
    }

    @Test
    public void findOneTest(){
        Customer customer = customerDao.findOne(150L);
        System.out.println(customer);
        customer.setCustName("天上");
        customerDao.save(customer);
        System.out.println(customer);
    }

    @Test
    public void save(){
        Customer customer = new Customer();
        customer.setCustAddress("湖南");
        customer.setCustLevel("it教育");
        customer.setCustName("张三");
        customer.setCustSource("教育");
        customer.setCustIndustry("top");
        customerDao.save(customer);
    }

    @Test
    public void SpecificationFindOneTest(){
        Sort sort = new Sort(Sort.Direction.DESC ,  "custId");
        Pageable pageable = new PageRequest(1, 5 , sort);

        Page<Customer> page = customerDao.findAll((root, criteriaQuery, cb) -> {
            Path<Object> custName = root.get("custName");
            Path<Object> custLevel = root.get("custLevel");
            //Predicate like = cb.like(custName.as(String.class), keywords);
            Predicate like = custName.isNotNull();
            Predicate equal = cb.like(custLevel.as(String.class), "%123");
            Predicate and = cb.and(like, equal);
            return and;
        }, pageable);

        List<Customer> content = page.getContent();

        System.out.println(page.getTotalPages());
        for (Customer customer : content) {
            System.out.println(customer);
        }
    }
}
