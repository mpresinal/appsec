/* Apache Velocity variables */

package com.presinal.appsec.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.presinal.appsec.role.config")
public class RoleRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleRestApplication.class, args);
	}

}
