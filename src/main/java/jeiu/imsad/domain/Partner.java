package jeiu.imsad.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Partner {

    public Partner () {}

    @Id @GeneratedValue
    private Long id;

    private String company;
    private String ceo;
    private String phone;
}
