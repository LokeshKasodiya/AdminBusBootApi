package org.jsp.adminbusapp.service;

import java.util.Optional;

import org.jsp.adminbusapp.dao.AdminDao;
import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.exception.AdminNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setData(adminDao.saveAdmin(admin));
		structure.setMessage("Admin Registered succesfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findById(admin.getId());
		if (recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.setId(admin.getId());
			dbAdmin.setName(admin.getName());
			dbAdmin.setPhone(admin.getPhone());
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setGst(admin.getGst());
			dbAdmin.setPassword(admin.getPassword());
			dbAdmin.setBuses(admin.getBuses());
			structure.setData(adminDao.saveAdmin(dbAdmin));
			structure.setMessage("Admin Details Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
		}
		throw new AdminNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Admin>> loginByPhone(long phone, String password) {
		Optional<Admin> dbAdmin = adminDao.loginByPhone(phone, password);
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		if (dbAdmin.isPresent()) {
			structure.setData(dbAdmin.get());
			structure.setMessage("Login Successfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new AdminNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Admin>> loginByEmail(String email, String password) {
		Optional<Admin> dbAdmin = adminDao.loginByEmail(email, password);
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		if (dbAdmin.isPresent()) {
			structure.setData(dbAdmin.get());
			structure.setMessage("Login Successfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new AdminNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Admin>> findById(int id) {
		Optional<Admin> dbAdmin = adminDao.findById(id);
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		if (dbAdmin.isPresent()) {
			structure.setData(dbAdmin.get());
			structure.setMessage("Admin Found ");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new AdminNotFoundException();	}
}
