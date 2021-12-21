package co.kr.community.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cm_no")
	private Long cmNo;
	
	@ManyToOne
	@JoinColumn(name = "b_no")
	private Board board;
	
	@Column(name = "cm_seq")
	private int cmSeq;
	
	@Column(name = "cm_lvl")
	private int cmLvl;
	
	@Column(name = "cm_content")
	private String cmContent;
	
	@ManyToOne
	@JoinColumn(name = "cm_writer", referencedColumnName = "username")
	private Member member;
	
	@CreationTimestamp
	@Column(name = "cm_regdate", updatable = false)
	private Timestamp cmRegdate;

}
