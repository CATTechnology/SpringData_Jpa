package top.takefly.test.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.takefly.Jpa.utls.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.*;

/**
 * @program: Jpa_Hibernate
 * @description: 复杂查询
 * @author: 戴灵飞
 * @create: 2019-07-26 20:33
 **/
public class JpaQueryLanguage {

    private EntityManager entityManager = null;

    private EntityTransaction transaction = null;

    @Before
    public void init() {
        entityManager = JpaUtils.getCurrentThreadEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();

    }

    @After
    public void destory(){
        transaction.commit();
        JpaUtils.close(entityManager);
    }

    public void printResult(Query query){

        List resultList = query.getResultList();
        System.out.println("数据量:"+resultList.size());
        for (Object o : resultList) {
            System.out.println(o);
        }
    }

    @Test
    public void findTest(){
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        printResult(query);
    }

    @Test
    public void findPageTest(){
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        int currentPage = 1;
        int rows = 3;
        query.setFirstResult((currentPage -1)*rows);
        query.setMaxResults(rows);
        printResult(query);
    }

    @Test
    public void findConditionTest(){
        String jpql = "from Customer where custName like ?";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1 , "%3");
        printResult(query);
    }

    @Test
    public void findOrderTest(){
        String jpql = "from Customer order by custLevel desc";
        Query query = entityManager.createQuery(jpql);
        printResult(query);
    }

    @Test
    public void statisticsTest(){
        String jpql = "select count(*) from Customer";
        Query query = entityManager.createQuery(jpql);
        Object querySingleResult = query.getSingleResult();
        System.out.println("总共有"+querySingleResult+"个客户");
        HashSet set = new HashSet();
//        String;
//        StringBuffer;
//        StringBuilder;
    }

}
