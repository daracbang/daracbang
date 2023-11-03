package a503.daracbang.domain.member.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import a503.daracbang.domain.member.dto.request.SignUpMemberRequest;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.global.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpMemberService {

	private final MemberRepository memberRepository;

	public Member signUp(SignUpMemberRequest signUpMemberRequest) {
		boolean isExist = memberRepository.existsByLoginId(signUpMemberRequest.getLoginId());
		if (isExist) {
			throw new CustomException(MemberErrorCode.DUPLICATE_MEMBER_LOGIN_ID);
		}
		isExist = memberRepository.existsByNickname(signUpMemberRequest.getNickname());
		if (isExist) {
			throw new CustomException(MemberErrorCode.DUPLICATE_MEMBER_NICKNAME);
		}

		signUpMemberRequest.setPassword(encodePassword(signUpMemberRequest.getPassword()));

		return memberRepository.save(signUpMemberRequest.toEntity());
	}

	private String encodePassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
}
