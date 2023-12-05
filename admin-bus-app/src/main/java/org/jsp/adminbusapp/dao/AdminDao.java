package org.jsp.adminbusapp.dao;

import java.util.Optional;

import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepository adminRepository;

	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	public Optional<Admin> loginByPhone(long phone, String password) {
		return adminRepository.verifyUser(phone, password);
	}

	public Optional<Admin> loginByEmail(String email, String password) {
		return adminRepository.verifyUser(email, password);
	}

	public Optional<Admin> findById(int id) {
		return adminRepository.findById(id);
	}
}
