package com.blog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TBL_ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="createdBy", nullable = false)
	private String createdBy;
	
	@Column(name="createdDate", nullable = false)
	private Date createdDate;
	
	@Column(name="modifiedBy", nullable = false)
	private String modifiedBy;
	
	@Column(name="modifiedDate", nullable = false)
	private Date modifiedDate;
	
	@PreUpdate
	@PrePersist
	public void updateTimeStamps()
	{
		Date date = new Date();
		String userId = "admin";
		this.modifiedDate = date;
		this.modifiedBy = userId;
		if(this.createdDate == null) {
			this.createdDate = date;
			this.createdBy = userId;
		}
	}
	
}
