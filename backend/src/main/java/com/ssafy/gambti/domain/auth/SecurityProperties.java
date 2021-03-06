package com.ssafy.gambti.domain.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties("security")
@Component
@Data
public class SecurityProperties {

	CookieProperties cookieProps;
	FirebaseProperties firebaseProps;
	boolean allowCredentials;
	List<String> allowedOrigins;
	List<String> allowedHeaders;
	List<String> exposedHeaders;
	List<String> allowedMethods;
	List<String> allowedPublicApis;

}
