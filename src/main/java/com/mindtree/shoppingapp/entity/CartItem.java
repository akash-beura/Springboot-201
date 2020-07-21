package com.mindtree.shoppingapp.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem implements Serializable, Comparable<CartItem> {

	private static final long serialVersionUID = -7894895724328434507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartItemId;
	private String itemName;
	private int itemQuantity;
	private float itemCost;
	private float totalItemPrice;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Cart cart;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartItemId;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (cartItemId != other.cartItemId)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}

	@Override
	public int compareTo(CartItem o) {
		return (int) this.totalItemPrice - (int) o.getTotalItemPrice();
	}

}
