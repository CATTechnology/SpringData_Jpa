package top.takefly.Jpa.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: Jpa_Hibernate
 * @description: 联系人实体
 * @author: 戴灵飞
 * @create: 2019-07-31 14:12
 **/
@Entity
@Table(name = "cst_linkman")
public class LinkMan implements Serializable {

    public LinkMan(){}

    public LinkMan(String lkmName, String lkmGender, String lkmPhone) {
        this.lkmName = lkmName;
        this.lkmGender = lkmGender;
        this.lkmPhone = lkmPhone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lkm_id")
    private Long lkmId;

    @Column(name="lkm_name" , length = 16 )
    private String lkmName;

    @Column(name="lkm_gender" , length = 16 )
    private String lkmGender;

    @Column(name="lkm_phone" , length = 16 )
    private String lkmPhone;

    @Column(name="lkm_mobile" , length = 16 )
    private String lkmMobile;

    @Column(name="lkm_email" , length = 64 )
    private String lkmEmail;

    @Column(name="lkm_position" , length = 16 )
    private String lkmPosition;

    @Column(name="lkm_memo" , length = 512 )
    private String lkmMemo;

    /**
     * 关联与客户
     */
//    @ManyToOne(targetEntity = Customer.class , cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
//    @JoinColumn(name="lkm_cust_id" , referencedColumnName = "cust_id" )
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="lkm_cust_id" , referencedColumnName = "cust_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getLkmId() {
        return lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "lkmId=" + lkmId +
                ", lkmName='" + lkmName + '\'' +
                ", lkmGender='" + lkmGender + '\'' +
                ", lkmPhone='" + lkmPhone + '\'' +
                '}';
    }
}
