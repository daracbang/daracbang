package a503.daracbang.domain.member.interceptor;

import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.global.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import a503.daracbang.domain.member.util.JwtUtil;
import a503.daracbang.domain.member.util.MemberContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ValidTokenInterceptor implements HandlerInterceptor {

	private JwtUtil jwtUtil;

	@Autowired
	public ValidTokenInterceptor(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("interceptor 호출");

		String jwt = jwtUtil.getJwtFromHeader(request);

		// NPP 발생 막기
		if(jwt == null){
			throw new CustomException(MemberErrorCode.NOT_CONTAIN_JWT);
		}


		jwtUtil.validateToken(jwt);
		MemberContextHolder.memberIdHolder.set(jwtUtil.getIdFromJwt(jwt));

		return true;
	}

	/*
		preflight 문제 생길 시 구현 예정
	 */

}
