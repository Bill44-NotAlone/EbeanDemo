package bill.code.entites.company;

import jakarta.persistence.*;

@Entity
@Table(name = "company", schema = "ebeanBd")
public class CommonCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

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

    @Override
    public String toString() {
        return "CommonCompany{" +
          "id=" + id +
          ", name='" + name + '\'' +
          '}';
    }
}
