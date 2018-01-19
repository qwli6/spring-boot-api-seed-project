package com.itqiwen.blog.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sblog_weight", catalog = "simple_blog")
public class Weight implements java.io.Serializable{
	private static final long serialVersionUID = 5721543158324488726L;
	
	private Integer id;
	private Float   weight;
	private Integer    createAt;
	
	public Weight(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "weight", length = 4)
	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	@Column(name = "create_at")
	public Integer getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}
	
}
