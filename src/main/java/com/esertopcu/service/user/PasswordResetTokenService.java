package com.esertopcu.service.user;

import java.util.Date;
import java.util.List;

import com.esertopcu.domain.user.PasswordResetToken;
import com.esertopcu.domain.user.User;

public interface PasswordResetTokenService {

	PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken);
	PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken);
	PasswordResetToken deletePasswordResetToken(PasswordResetToken passwordResetToken);
	PasswordResetToken findPasswordResetTokenByToken(String token);
	PasswordResetToken findPasswordResetTokenFindByUser(User user);
	PasswordResetToken findPasswordResetTokenFindById(Long id);
	
	// tarihi geçen tokenlerin listeni verir
	List<PasswordResetToken> findAllByExpiryDateLessThan(Date date);
	
	//@Scheduler annotation altında tanımlayacağız komutumuz sürekli çalışacak
	boolean deleteAllExpiredSince(Date date);
}
