package com.mindtree.shoppingapp.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "p_book")
@Getter
@Setter
public class Book extends Product {

	private static final long serialVersionUID = 2188691405131008446L;
	private String genre;
	private String author;
	private String publications;

	@Override
	public int compareTo(Product o) {
		return this.getProductName().compareTo(o.getProductName());
	}

}
