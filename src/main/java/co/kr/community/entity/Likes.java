package co.kr.community.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "l_no")
	private Long lNo;
	
	@ManyToOne
	@JoinColumn(name = "b_no")
	private Board board;

	@ManyToOne
	@JoinColumn(name = "username")
	private Member member;
	
}
