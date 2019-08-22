package top.takefly.Jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.takefly.Jpa.pojo.Customer;
import top.takefly.Jpa.pojo.LinkMan;

public interface LkmManDao extends JpaRepository<LinkMan, Long> , JpaSpecificationExecutor<LinkMan> {
}
