package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import sun.tools.jar.CommandLine;


@SpringBootApplication
public class DynamicRuleEngineDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicRuleEngineDemoApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext context){
//        return args -> {
//            System.out.println("Spring Boot 管理的所有bean:");
//            String[] beanNames = context.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//        };
//    }

}
