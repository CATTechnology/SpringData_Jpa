package top.takefly.Jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.takefly.Jpa.pojo.Customer;

/**
 * @author DLF
 */
@Repository
public interface ICustomerDao  extends JpaRepository<Customer,Long> , JpaSpecificationExecutor<Customer> {
}
