package backend.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST = {
            // --sawgger-ui
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",

            //Login
            "/api/users/login/**",

            //Register
            "/api/users/create/**"
    };

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Validar el Token
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //Configurar Conexiones Externas
        http.cors(Customizer.withDefaults()); //Permite request de servidores externos al del backend?
        http.csrf(AbstractHttpConfigurer::disable); //Permite reutilizar el token entre servidores?


        //Permisos para las rutas
        http.authorizeHttpRequests( auth-> auth
                .requestMatchers(AUTH_WHITELIST).permitAll()

                .requestMatchers(HttpMethod.POST,"/api/authorities/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/authorities/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/cities/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/cities/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/clients/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/clients/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/comments/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/comments/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/events/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/events/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/event-types/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/event-types/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/favorites/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/favorites/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/promoters/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/promoters/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/purchased-tickets/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/purchased-tickets/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/ticket-types/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/ticket-types/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST,"/api/transactions/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.GET,"/api/transactions/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.GET, "/api/users/list").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAnyAuthority("BORRAR")
                .requestMatchers(HttpMethod.DELETE, "/api/users/force/{id}").hasAnyAuthority("BORRAR_FORZADO")
                .requestMatchers(HttpMethod.GET, "/api/users/status").hasAnyAuthority("CONSULTA_ESTADO")
                .anyRequest().authenticated()
        );





        //Tipo de gestión de sesiones
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();

    }



}
