package com.server.fitnessgym.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "option_role")
public class OptionRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role roleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "option_id")
	private Option optionId;
	
	@Column(name = "create_right")
	private int createRight;
	
	@Column(name = "edit_right")
	private int editRight;
	
	@Column(name = "delete_right")
	private int deleteRight;
	
	@Column(name = "view_right")
	private int viewRight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public Option getOptionId() {
		return optionId;
	}

	public void setOptionId(Option optionId) {
		this.optionId = optionId;
	}

	public int getCreateRight() {
		return createRight;
	}

	public void setCreateRight(int createRight) {
		this.createRight = createRight;
	}

	public int getEditRight() {
		return editRight;
	}

	public void setEditRight(int editRight) {
		this.editRight = editRight;
	}

	public int getDeleteRight() {
		return deleteRight;
	}

	public void setDeleteRight(int deleteRight) {
		this.deleteRight = deleteRight;
	}

	public int getViewRight() {
		return viewRight;
	}

	public void setViewRight(int viewRight) {
		this.viewRight = viewRight;
	}
	
}