package bill.code.entites.company;

import jakarta.persistence.*;

@Table(name = "developmentteams")
@Entity
public class CommonDeveloperTeam {

    @Id @GeneratedValue
    private Integer id;
    private String name;
    @Column(name = "CompanyId")
    private Integer commonCompanyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCommonCompanyId() {
        return commonCompanyId;
    }

    public void setCommonCompanyId(Integer commonCompanyId) {
        this.commonCompanyId = commonCompanyId;
    }

    @Override
    public String toString() {
        return "CommonDeveloperTeam{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", commonCompanyId=" + commonCompanyId +
          '}';
    }
}
