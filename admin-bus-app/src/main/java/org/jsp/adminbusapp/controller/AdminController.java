package org.jsp.adminbusapp.controller;

import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/admins")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);
	}

	@PutMapping("/admins")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
	}

	@PostMapping("/admins/loginByPhone")
	public ResponseEntity<ResponseStructure<Admin>> loginByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return adminService.loginByPhone(phone, password);
	}

	@PostMapping("/admins/loginByEmail")
	public ResponseEntity<ResponseStructure<Admin>> loginByEmail(@RequestParam String email,
			@RequestParam String password) {
		return adminService.loginByEmail(email, password);
	}
	@GetMapping("/admins/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findById(@PathVariable int id){
		return adminService.findById(id);
	}
}
