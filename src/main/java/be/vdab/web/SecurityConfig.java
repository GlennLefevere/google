package be.vdab.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Configuration
public class SecurityConfig {
	
	/*@Configuration
	@Profile("dev")
	static class Dev{*/
		
		@Bean
		public TextEncryptor textEncryptor(){
			return Encryptors.noOpText();
		}
	//}
}
