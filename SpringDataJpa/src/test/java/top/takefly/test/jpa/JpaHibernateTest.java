package top.takefly.test.jpa;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.takefly.Jpa.pojo.Customer;
import top.takefly.Jpa.utls.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * @program: Jpa_Hibernate
 * @description: 用于jpa的第一个测试
 * @author: 戴灵飞
 * @create: 2019-07-26 16:17
 **/
public class JpaHibernateTest {

    private EntityTransaction transaction = null;
    private EntityManager entityManager = null;

    @Before
    public void init(){
        entityManager = JpaUtils.getEntityManager();
        transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
    }

    @After
    public void destory(){
        transaction.commit();
        JpaUtils.close(entityManager);
    }

    @Test
    public void persistTest(){
        Customer customer = new Customer("天猫","电商" ,"杭州","15886449241");
        entityManager.persist(customer);
    }

    @Test
    public void mergeTest(){
        Customer customer = entityManager.find(Customer.class, 1L);
        customer.setCustLevel("1");
        customer.setCustSource("深圳市公明区");
        entityManager.merge(customer);
        System.out.println(customer);
    }

    @Test
    public void removeTest(){
        Customer customer = entityManager.find(Customer.class, 1L);
        entityManager.remove(customer);
    }

    @Test
    public void findByIdTest(){
        Customer customer = entityManager.find(Customer.class, 2L);
        System.out.println(customer);
    }
}
