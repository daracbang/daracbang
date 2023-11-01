package a503.daracbang.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.global.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckDuplicateMemberInfoService {

	private final MemberRepository memberRepository;

	public void checkDuplicateLoginId(String loginId) {
		if (memberRepository.existsByLoginId(loginId)) {
			throw new CustomException(MemberErrorCode.DUPLICATE_MEMBER_LOGIN_ID);
		}
	}

	public void checkDuplicateNickname(String nickname) {
		if (memberRepository.existsByNickname(nickname)) {
			throw new CustomException(MemberErrorCode.DUPLICATE_MEMBER_NICKNAME);
		}
	}
}
