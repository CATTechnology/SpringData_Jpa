package top.takefly.test.jpa;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.takefly.Jpa.pojo.Customer;
import top.takefly.Jpa.pojo.Customer2;
import top.takefly.Jpa.uitls.CloneUtil;
import top.takefly.Jpa.utls.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.UUID;

/**
 * @program: Jpa_Hibernate
 * @description: 用于jpa的第一个测试
 * @author: 戴灵飞
 * @create: 2019-07-26 16:17
 **/
public class JpaHibernateTest {

    private EntityTransaction transaction = null;
    private EntityManager entityManager = null;

    //@Before
    public void init(){
        entityManager = JpaUtils.getEntityManager();
        transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
    }

    @Before
    public void initEntityManager(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        System.out.println(entityManager);
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
        Customer customer = entityManager.find(Customer.class, 4L);
        customer.setCustLevel("1");
        customer.setCustSource("教育");
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
        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.println(customer);
    }

    @Test
    public void delete() throws Exception{
        Customer customer = entityManager.find(Customer.class, 18L);
        customer.setCustAddress("天山");
        Customer newCustomer = CloneUtil.clone(customer);
        newCustomer.setCustLevel(UUID.randomUUID().toString().replace("-" , ""));
        newCustomer.setCustId(null);
//        entityManager.persist(newCustomer);
        entityManager.remove(customer);
    }

    @Test
    public void find(){
        Customer2 customer2 = entityManager.find(Customer2.class, 4L);
        System.out.println(customer2);
    }
}
