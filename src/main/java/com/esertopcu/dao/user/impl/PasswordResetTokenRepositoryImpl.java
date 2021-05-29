package com.esertopcu.dao.user.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.esertopcu.dao.user.PasswordResetTokenRepository;
import com.esertopcu.domain.user.PasswordResetToken;
import com.esertopcu.domain.user.User;

@Repository
@Transactional
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken) {
		entityManager.persist(passwordResetToken);
		return passwordResetToken;
	}

	@Override
	public PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken) {
		PasswordResetToken updatedPasswordResetToken = entityManager.merge(passwordResetToken);
		return updatedPasswordResetToken;
	}

	@Override
	public PasswordResetToken deletePasswordResetToken(PasswordResetToken passwordResetToken) {
		if(entityManager.contains(passwordResetToken)) {
			entityManager.remove(passwordResetToken);
			return passwordResetToken;
		}
		PasswordResetToken prt = findPasswordResetTokenByToken(passwordResetToken.getToken());
		entityManager.remove(prt);
		return prt;
	}

	@Override
	@Transactional(readOnly = true) // sadece veri okuyor
	public PasswordResetToken findPasswordResetTokenByToken(String token) {
		TypedQuery<PasswordResetToken> typedQuery = entityManager.createNamedQuery("PasswordResetToken.findByToken", PasswordResetToken.class);
		typedQuery.setParameter("token", token);
		return typedQuery.getSingleResult();
	}
	
	@Override
	@Transactional(readOnly = true)
	public PasswordResetToken findPasswordResetTokenFindByUser(User user) {
		TypedQuery<PasswordResetToken> typedQuery = entityManager.createNamedQuery("PasswordResetToken.findByUserId", PasswordResetToken.class);
		typedQuery.setParameter("userId", user.getId());
		return typedQuery.getSingleResult();
	}

	@Override
	@Transactional(readOnly = true)
	public PasswordResetToken findPasswordResetTokenFindById(Long id) {
		return entityManager.find(PasswordResetToken.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PasswordResetToken> findAllByExpiryDateLessThan(Date date) {
		TypedQuery<PasswordResetToken> typedQuery = entityManager.createNamedQuery("PasswordResetToken.findAllByExpiryDateLessThan", PasswordResetToken.class);
		typedQuery.setParameter("userId", date, TemporalType.TIMESTAMP);
		return typedQuery.getResultList();
	}

	@Override
	public boolean deleteAllExpiredSince(Date date) {
		
		// TypedQuery : bize geriye veri dönecek şekilde çalışır.
		// Query : ise geriye veri dönmez. SQL sorgusu çalışır gibi işlem yapar.
		
		Query query = entityManager.createNamedQuery("PasswordResetToken.deleteExpiryDateToken");
		query.setParameter("date", date, TemporalType.TIMESTAMP);
		int result = query.executeUpdate();
		return result != -1;
	}
}
