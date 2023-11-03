package a503.daracbang.domain.member.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.domain.member.util.JwtUtil;
import a503.daracbang.global.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginMemberService {

	private final MemberRepository memberRepository;
	private final JwtUtil jwtUtil;

	public String login(String loginId, String password) {
		Member member = memberRepository.findByLoginId(loginId)
			.orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));

		if (!encodePassword(member.getPassword()).equals(password)) {
			throw new CustomException(MemberErrorCode.INCORRECT_PASSWORD);
		}

		return jwtUtil.generateJwt(member.getId());
	}

	private String encodePassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
}
