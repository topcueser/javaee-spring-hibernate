package com.esertopcu.domain.user;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "PasswordResetToken")
@NamedQueries({
	@NamedQuery(name = "PasswordResetToken.findByToken", query = "SELECT p FROM PasswordResetToken p WHERE p.token = :token"),
	@NamedQuery(name = "PasswordResetToken.findByUserId", query = "SELECT p FROM PasswordResetToken p WHERE p.user.id = :userId"),
	@NamedQuery(name = "PasswordResetToken.deleteExpiryDateToken", query = "DELETE FROM PasswordResetToken p WHERE p.expiryDate <= :expiryDate"),
	@NamedQuery(name = "PasswordResetToken.findAllByExpiryDateLessThan", query = "SELECT p FROM PasswordResetToken p WHERE p.expiryDate <= :expiryDate")
})
public class PasswordResetToken {

	@Transient // Db tarafında kolonun oluşmasını istemiyorsak transient ile belirtilir.
	private final int EXPIRY_DATE = 60*24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Column
	private String token;
	
	@OneToOne(fetch = FetchType.EAGER) // token bilgisi getilirken user bilgisi ile birlikte getirilir.
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(foreignKeyDefinition = "user_fk"))
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	public PasswordResetToken() {
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
	}

	public PasswordResetToken(User user, String token) {
		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PasswordResetToken other = (PasswordResetToken) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private Date calculateExpiryDate(int eXPIRY_DATE2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, EXPIRY_DATE);
		return new Date(calendar.getTime().getTime());
	}
}
