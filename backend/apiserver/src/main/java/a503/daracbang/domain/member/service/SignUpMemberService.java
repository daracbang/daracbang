package a503.daracbang.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import a503.daracbang.domain.member.dto.SignUpMemberRequest;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpMemberService {

	private final MemberRepository memberRepository;

	public Member signUp(SignUpMemberRequest signUpMemberRequest) {
		return memberRepository.save(signUpMemberRequest.toEntity());
	}
}
