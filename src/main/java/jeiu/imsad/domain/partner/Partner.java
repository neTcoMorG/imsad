package jeiu.imsad.domain.partner;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Partner {

    public Partner () {}
    public Partner(Long id, String loginId, String name, String phone, String companyName, String password, String email) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.phone = phone;
        this.companyName = companyName;
        this.password = password;
        this.email = email;
    }

    @Id @GeneratedValue
    private Long id;
    @NotBlank(message = "회사명 공백X") //메세지 지정가능
    private String loginId;

    @NotBlank(message = "대표 이름 공백X")
    private String name;

    @NotBlank(message = "연락처 공백X")
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String companyName;

    @NotBlank
    private String password;
}
