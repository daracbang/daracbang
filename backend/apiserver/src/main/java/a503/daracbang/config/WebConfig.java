package a503.daracbang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import a503.daracbang.domain.member.interceptor.ValidTokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final ValidTokenInterceptor validTokenInterceptor;

	@Autowired
	public WebConfig(ValidTokenInterceptor validTokenInterceptor) {
		this.validTokenInterceptor = validTokenInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(validTokenInterceptor)
				.addPathPatterns("/api/**")
				.excludePathPatterns("/docs/**",
									 "/api/members",
									 "/api/members/login-id/**",
									 "/api/members/nickname/**",
									 "/api/members/login");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOriginPatterns("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);
	}
}
