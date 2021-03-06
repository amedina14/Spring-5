package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/* Sobre escribir metodo para especificar y filtrar los accesos a las rutas. */	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/listar/**","/css/**","/js/**","/images/**").permitAll()
		.antMatchers("/uploads/**").hasAnyRole("USER")
		.antMatchers("/ver/**").hasAnyRole("USER")
		.antMatchers("/form/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/factura/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login")	
			.permitAll()
		.and()
		.logout().permitAll();
	}

	/*
	 * Configuramos nuestro encriptador por defecto: BCryptPasswordEncoder.
	 * Requerido en spring 2.2.5 (superiores al 2.1.)
	 * 
	 * Lo guardamos en el contenedor de spring con Bean y queda disponible
	 * en el contexto de spring security.
	 * 
	 * Lo instanciamos de una.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		/* 
		 * Con PasswordEncoder ahora podemos crear los usuarios y cifrar sus contraseñas.
		 * Invocamos el metodo y lo almacenamos en encoder. 
		 */
		PasswordEncoder encoder = passwordEncoder();
				
		/* 
		 * Por cada user que se registra, la password se cifra automaticamente.
		 * 
		 * Configuramos la forma en que se va a cifrar la password.
		 * 
		 * Llamada estatica, :: obtiene el argumento de la funcion lambda
		 * (password) y la pasa al metodo encode y la retorna en encoder.
		 * Forma implicita, alias o simplificacion de Java 8.
		 */
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		/*
		UserBuilder users = User.builder().passwordEncoder(password -> {
			return encoder.encode(password);
		});
		*/

		/* Se crean los usuarios en memoria */
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
		.withUser(users.username("adrian").password("12345").roles("USER"));
	}
}
