/* Apache Velocity variables */

package com.presinal.anxeliephotoshoot.security.rolegroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.presinal.anxeliephotoshoot.security.rolegroup.config")
public class RoleGroupRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleGroupRestApplication.class, args);
	}

}
