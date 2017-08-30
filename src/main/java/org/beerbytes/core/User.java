package org.beerbytes.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"), })
public class User {
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Privilege privilege;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usercredentials_id")
	private UserCredentials userCredentials;

	public User(final String username, final Privilege privilege, UserCredentials userCredentials) {
		this.username = username;
		this.privilege = privilege;
		this.userCredentials = userCredentials;
	}

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public long getId() {
		return id;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result + ((userCredentials == null) ? 0 : userCredentials.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (privilege != other.privilege)
			return false;
		if (userCredentials == null) {
			if (other.userCredentials != null)
				return false;
		} else if (!userCredentials.equals(other.userCredentials))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", privilege=" + privilege + ", userCredentials=" + userCredentials + "]";
	}

}
