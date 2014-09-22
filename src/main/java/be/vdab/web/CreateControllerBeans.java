package be.vdab.web;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import be.vdab.entities.UserInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = CreateControllerBeans.class)
public class CreateControllerBeans extends WebMvcConfigurerAdapter {
	
	@Inject
	private UsersConnectionRepository usersConnectionRepository;

	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/JSP/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:teksten");
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}

	@Bean
	LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieMaxAge(604800); // 604800=7 dagen
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserInterceptor(usersConnectionRepository));
	}

	
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/signin");
		registry.addViewController("/signout");
	}
}
