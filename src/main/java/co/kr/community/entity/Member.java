package co.kr.community.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@Id
	@Size(min = 4, max = 16, message = "아이디는 4~12자리로 입력해야 합니다.")
//	@JsonManagedReference
	private String username;
	
	@Size(min = 4, message = "비밀번호를 4자 이상 입력해주세요.")
	private String password;
	
	private String name;
	
	private String nickname;
	
	private String email;
	
	@CreationTimestamp
	@Column(name = "reg_date", updatable = false)
	private Timestamp regDate;
	
	@Column(name = "unreg_date")
	private Timestamp unregDate;
	
	private boolean enabled;
	
	@JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "member_role", // 조인 테이블 명
            joinColumns = @JoinColumn(name = "username"), // 현재 엔티티(user)를 참조하는 외래키 (member_role 테이블의 username)
            inverseJoinColumns = @JoinColumn(name = "role_no")) // 반대 엔티티(role)를 참조하는 외래키 (member_role 테이블의 role_no)
    private List<Role> roles = new ArrayList<>();
	

}
