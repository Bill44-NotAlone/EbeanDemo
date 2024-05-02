package bill.code.entites.company;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class DeveloperTeamWithCompany {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private CommonCompany commonCompany;

    @Override
    public String toString() {
        return "DeveloperTeamWithCompany{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", commonCompany=" + commonCompany +
          '}';
    }
}
