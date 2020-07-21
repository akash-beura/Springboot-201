package com.mindtree.shoppingapp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart implements Serializable, Comparable<Cart> {

	private static final long serialVersionUID = 156040533583774089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private float totalCost;
	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = "cart")
	@JsonIgnoreProperties("cart")
	private Set<CartItem> cartItems = new HashSet<>();
	@OneToOne
	@JsonIgnoreProperties("cart")
	private User user;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public int compareTo(Cart o) {
		return (int) this.getTotalCost() - (int) o.getTotalCost();
	}

}
