package com.hool.app.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.hool.app.repository.MemberRepository;
import com.hool.app.service.MemberService;

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(SecurityConfig.class)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	    @Autowired
	    @Qualifier("dataSource")
	    private DataSource dataSource;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private PasswordEncoder oauthClientPasswordEncoder;
	    
	    @Autowired
	   	private MemberService memberService;

	    @Bean
	    public TokenStore tokenStore() {
	        return new JdbcTokenStore(dataSource);
	    }

	    @Bean
	    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
	        return new OAuth2AccessDeniedHandler();
	    }

	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
	        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(oauthClientPasswordEncoder);
	    }

	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.jdbc(dataSource);
	    }

	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
	        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
	    }
	    
	    @EventListener
	    public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent){
	        // write custom code here for login success audit
	        //System.out.println("User Oauth2 login success");
	        //System.out.println("This is success event : "+authorizedEvent.getAuthentication().getPrincipal());
	        UserDetails user=(UserDetails)authorizedEvent.getAuthentication().getPrincipal();
	    	memberService.updateOnlineStatus(1, user.getUsername());
	    }
	    @EventListener
	    public void authFailedEventListener(AbstractAuthenticationFailureEvent oAuth2AuthenticationFailureEvent){
	        // write custom code here login failed audit.
	        //System.out.println("User Oauth2 login Failed");
	        //System.out.println(oAuth2AuthenticationFailureEvent.getAuthentication().getPrincipal());
	    }
	    
}
