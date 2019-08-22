package top.takefly.Jpa.utls;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.util.LinkedList;

/**
 * @program: Jpa_Hibernate
 * @description: EntityManagerFactory的创建
 * @author: 戴灵飞
 * @create: 2019-07-26 16:18
 **/
public final class JpaUtils {

    private static EntityManagerFactory entityManagerFactory = null;

    /**
     * 当前的
     */
    private static transient int elementSize;

    /**
     *
     */
    private static transient int elementStock;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
    }

    private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();
    private static LinkedList<EntityManager> entityManagersPool = new LinkedList<>();

    public static EntityManager getEntityManager() {
        final int stockCount = elementStock;
        EntityManager entityManager = entityManagersPool.peek();
        if (entityManager == null && elementSize < 10) {
            entityManager = entityManagerFactory.createEntityManager();
            elementSize++;
        } else {
            elementStock = stockCount - 1;
        }
        return entityManager;
    }

    public static void close(EntityManager entityManager) {
        if (entityManagersPool.size() < 10) {
            elementStock++;
            entityManagersPool.add(entityManager);
        }
    }

    public static EntityManager getCurrentThreadEntityManager(){
        EntityManager entityManager = threadLocal.get();
        if(entityManager == null && elementSize < 10){
            entityManager = getEntityManager();
            threadLocal.set(entityManager);
        }

        return entityManager;
    }

    public static Integer getCapacity(){
        return elementSize;
    }

    public static Integer getStock(){
        return elementStock;
    }


}
