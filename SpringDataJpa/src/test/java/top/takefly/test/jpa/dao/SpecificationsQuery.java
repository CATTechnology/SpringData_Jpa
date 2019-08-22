package top.takefly.test.jpa.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.takefly.Jpa.dao.CustomerDao;
import top.takefly.Jpa.pojo.Customer;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @program: Jpa_Hibernate
 * @description: 通过JpaSpecificationExecutor来查询
 * @author: 戴灵飞
 * @create: 2019-07-31 12:01
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecificationsQuery {

    @Autowired
    private CustomerDao customerDao;

    @Test
    @Transactional
    public void findTest(){
        final String keywords = "戴灵飞";
        Specification<Customer> specification = new Specification<Customer>(){
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //通过root获取Customer里面的字段属性
                Path<Object> custName = root.get("custName");
                Predicate like = cb.like(custName.as(String.class), keywords + "%");

                return like;
            }
        };
        List<Customer> customers = customerDao.findAll(specification);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    @Transactional
    public void findByAddressAndCustLevelTest(){

        Specification<Customer> specification = new Specification<Customer>(){
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //通过root获取Customer里面的字段属性
                Path<Customer> custName = root.get("custAddress");
                Predicate like = cb.equal(custName.as(String.class), "杭州3");

                Path<Customer> custLevel = root.get("custLevel");
                Predicate equal = cb.equal(custLevel.as(String.class), "1");
                return cb.and(like, equal);
            }
        };

        //遍历结果
        List<Customer> customers = customerDao.findAll(specification);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    @Transactional
    public void pageQueryTest(){
        final String keywords = "it";
        Specification<Customer> specification = new Specification<Customer>(){
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //通过root获取Customer里面的字段属性
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate like = cb.like(custIndustry.as(String.class), keywords + "%");

                return like;
            }
        };
        Pageable pageable = new PageRequest( 1 , 5);
        Page<Customer> customerPage = customerDao.findAll(specification, pageable);
        System.out.println("总共"+customerPage.getTotalPages()+"页，当前第"+(customerPage.getNumber()+1)+"页，共"+customerPage.getNumberOfElements()+"个元素");
        for (Customer customer : customerPage.getContent()) {
            System.out.println(customer);
        }
    }
}
