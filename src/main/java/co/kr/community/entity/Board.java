package co.kr.community.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long b_no;
	
	@ManyToOne
	@JoinColumn(name = "c_no")
	private Category category;
	
	private String b_divide;
	
	private String b_title;
	
	private int b_secret;
	
	private String b_content;
	
	private String b_writer;
	
	private Timestamp b_regdate;
	
	private Timestamp b_moddate;
	
	private int b_views;

}
