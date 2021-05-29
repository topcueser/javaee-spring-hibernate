package com.esertopcu.service.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esertopcu.dao.user.PasswordResetTokenRepository;
import com.esertopcu.domain.user.PasswordResetToken;
import com.esertopcu.domain.user.User;
import com.esertopcu.service.user.PasswordResetTokenService;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService{

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepository.savePasswordResetToken(passwordResetToken);
	}

	@Override
	public PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepository.updatePasswordResetToken(passwordResetToken);
	}

	@Override
	public PasswordResetToken deletePasswordResetToken(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepository.deletePasswordResetToken(passwordResetToken);
	}

	@Override
	public PasswordResetToken findPasswordResetTokenByToken(String token) {
		return passwordResetTokenRepository.findPasswordResetTokenByToken(token);
	}

	@Override
	public PasswordResetToken findPasswordResetTokenFindByUser(User user) {
		return passwordResetTokenRepository.findPasswordResetTokenFindByUser(user);
	}

	@Override
	public PasswordResetToken findPasswordResetTokenFindById(Long id) {
		return passwordResetTokenRepository.findPasswordResetTokenFindById(id);
	}

	@Override
	public List<PasswordResetToken> findAllByExpiryDateLessThan(Date date) {
		return passwordResetTokenRepository.findAllByExpiryDateLessThan(date);
	}

	@Override
	public boolean deleteAllExpiredSince(Date date) {
		return deleteAllExpiredSince(date);
	}

}
