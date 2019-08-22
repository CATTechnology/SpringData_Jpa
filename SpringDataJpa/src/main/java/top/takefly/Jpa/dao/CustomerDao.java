package top.takefly.Jpa.dao;

import org.hibernate.annotations.Persister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.takefly.Jpa.pojo.Customer;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query(value = "From Customer where custAddress =  ?1 ")
    public List<Customer> findCustomerByCustAddress(String custAddress);

    @Query(value = "From Customer where custName = ?1 and custAddress = ?2")
    public List<Customer> findCustomerByCustNameAndCustAddress(String name, String addr);

    @Query(value="update Customer set custName = ?1 where custId = ?2")
    @Modifying
    public Integer updateCustomer(String custName,Long custId);

    @Query(value = "select * from cst_customer" , nativeQuery = true)
    public List<Customer> findCustomer();

    public List<Customer> findCustomerByCustAddressLikeAndCustLevel(String addr , String level);

    public Integer removeCustomerByCustAddress(String addr);

    public List<Customer> findAllByCustSourceIsNull();

}
