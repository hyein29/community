package co.kr.community.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
                .authorizeRequests()
                    .mvcMatchers("/", "/member/register/**", "/board/**", "/css/**").permitAll() // 누구나 접근 허용
                    .mvcMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관없이 권한이 있어야 접근
                    .and()
                .formLogin()
                    .loginPage("/member/login") // 로그인 페이지 링크
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Override // static 디렉토리의 하위 파일은 인증을 무시하도록 설정
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled " // 반드시 이 순서로
                        + "from member "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select m.username, r.name "
                        + "from member_role mr inner join member m on mr.username = m.username "
                        + "inner join role r on mr.role_no = r.role_no "
                        + "where m.username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
