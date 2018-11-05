package com.hool.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableJpaRepositories("com.hool.app.repository")
@EntityScan("com.hool.app")
public class HoolApplication extends SpringBootServletInitializer  implements CommandLineRunner{
	//private static Logger logger = LoggerFactory.getLogger(HoolApplication.class);
	
	
	//@Autowired
	//private GameTableMemberRepository  gameTableMemberRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HoolApplication.class, args);			
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(HoolApplication.class);
	}
	
	@Override
	public void run(String... args) throws Exception {		
		//  gameTableMemberRepository.findAll().iterator().forEachRemaining((p)->System.out.println(p.getMember()));	
		  	  
		  /*for(GameTableMember gm:gameTableMemberRepository.findAll()){
			  
			  System.out.println(gm.getMember().getUserName());
		  }*/
	           
    }
}
