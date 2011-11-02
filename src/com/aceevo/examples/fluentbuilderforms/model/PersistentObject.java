package com.aceevo.examples.fluentbuilderforms.model;

public abstract class PersistentObject {

	protected Long id;

	public PersistentObject() {
		// TODO Auto-generated constructor stub
	}

	public PersistentObject(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
