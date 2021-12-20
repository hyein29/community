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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@Id
	private String username;
	
	private String password;
	
	private String name;
	
	private String nickname;
	
	private String email;
	
	@CreationTimestamp
	@Column(name = "reg_date", updatable = false)
	private Timestamp regDate;
	
	@Column(name = "unreg_date")
	private Timestamp unregDate;
	
	private int enabled;
	
	
	@JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "member_role", // 조인 테이블 명
            joinColumns = @JoinColumn(name = "username"), // 현재 엔티티(user)를 참조하는 외래키 (member_role 테이블의 username)
            inverseJoinColumns = @JoinColumn(name = "role_id")) // 반대 엔티티(role)를 참조하는 외래키 (member_role 테이블의 role_no)
    private List<Role> roles = new ArrayList<>();

	
	
	
	

}
