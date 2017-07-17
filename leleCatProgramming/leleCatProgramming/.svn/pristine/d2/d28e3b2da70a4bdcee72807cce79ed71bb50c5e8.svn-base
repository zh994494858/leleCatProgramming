package cc.lelecat.entity.account.worker;

import javax.persistence.*;

/**
 * 用户信息表
 *
 * Modified by ACat,
 * 2016-11-25 11:32:10,
 * ACat i lele.
 */
@Entity
@Table(name = "worker_account_info")
public class AccountInfo {
    private Integer id;
    private String telephone;
    private String email;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountInfo that = (AccountInfo) o;

        if (id != null ?
                that.id != null && id.equals(that.id) : that.id != null)
            return false;
        if (telephone != null ?
                that.telephone != null && telephone.equals(that.telephone) : that.telephone != null)
            return false;
        if (email != null ?
                that.email != null && email.equals(that.email) : that.email != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id : 0;
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
