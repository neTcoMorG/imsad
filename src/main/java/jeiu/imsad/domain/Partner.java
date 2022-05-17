package jeiu.imsad.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Partner {

    public Partner () {}

    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "회사명 공백X") //메세지 지정가능
    private String company;

    @NotBlank(message = "대표 이름 공백X")
    private String ceo;

    @NotBlank(message = "연락처 공백X")
    private String phone;
}
