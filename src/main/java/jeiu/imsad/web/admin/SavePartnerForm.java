package jeiu.imsad.web.admin;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SavePartnerForm {

    @NotBlank(message = "회사 이름을 입력해주세요.")
    private String companyName;

    @NotBlank(message = "아이디를 입력해주세요")
    private String loginId;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일을 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "휴대폰 번호를 입력해주세요")
    private String phone;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String confirmPassword;
}
