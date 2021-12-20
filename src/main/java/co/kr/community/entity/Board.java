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
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "b_no")
	private Long bNo;
	
	@ManyToOne
	@JoinColumn(name = "c_no")
	private Category category;
	
	@Column(name = "b_divide")
	private String bDivide;
	
	@Column(name = "b_title")
	private String bTitle;
	
	@Column(name = "b_content")
	private String bContent;
	
	@Column(name = "b_writer")
	private String bWriter;
	
	@CreationTimestamp
	@Column(name = "b_regdate", updatable = false)
	private Timestamp bRegdate;
	
	@UpdateTimestamp
	@Column(name = "b_moddate")
	private Timestamp bModdate;
	
	@Column(name = "b_viewcnt")
	private int bViewcnt;

}
