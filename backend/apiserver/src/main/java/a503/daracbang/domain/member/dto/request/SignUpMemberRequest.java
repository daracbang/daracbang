package a503.daracbang.domain.member.dto.request;

import a503.daracbang.domain.member.dto.request.valid.AllowedContentType;
import a503.daracbang.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SignUpMemberRequest {

	@NotBlank
	@Size(min = 2, max = 10, message = "로그인 ID는 2자 이상, 10자 이하로 입력해주세요.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String loginId;

	@NotBlank
	@Size(min = 4, max = 20, message = "비밀번호는 4자 이상, 20자 이하로 입력해주세요.")
	private String password;

	@NotBlank
	@Size(min = 2, max = 10)
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]*$")
	private String nickname;

	@AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
			allowedExtensions = {"jpg", "jpeg", "png"})
	private MultipartFile image;

	@Builder
	public Member toEntity(String imageUrl) {
		return Member.builder()
					 .loginId(loginId)
					 .password(password)
					 .nickname(nickname)
					 .profileImage(imageUrl)
					 .build();
	}
	@Builder
	public SignUpMemberRequest(String loginId, String password, String nickname,
			MultipartFile image) {
		this.loginId = loginId;
		this.password = password;
		this.nickname = nickname;
		this.image = image;
	}

	public SignUpMemberRequest(){
	}

}
