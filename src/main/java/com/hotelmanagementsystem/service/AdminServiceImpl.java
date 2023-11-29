package com.hotelmanagementsystem.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.dao.Admin;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService
{

	@Autowired
	private AdminRepository adminRepository;
	@Override
	public Admin registerAdmin( Admin admin) {
		return adminRepository.save(admin);
	}
	
	@Override
	public Admin loginAdmin(String adminemail, String adminpassword) {
		Admin admin=adminRepository.findAdminByEmail(adminemail);
		if(admin!=null && adminpassword.equals(admin.getAdminpassword())) {
		return admin;
		}
		return null;
	}
	
	@Override
	public void deleteAdminById(Integer adminid) throws GlobalException {
		Optional<Admin> ad=adminRepository.findById(adminid);
		Admin ad1=null;
		if(!ad.isPresent()) {
			throw new GlobalException("Admin with id "+adminid+" not found");
		}
		adminRepository.deleteById(adminid);
	}
	
	
	@Override
	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}
	@Override
	public Admin getAdminById(Integer adminid) throws GlobalException {
		Optional<Admin> a=adminRepository.findById(adminid);
		Admin a1=null;
		if(!a.isPresent()) {
			throw new GlobalException("admin with id "+adminid+" not found");
		}
		a1=adminRepository.findById(adminid).get();
		return a1;
	}
	@Override
	public Admin updateAdminById(Integer adminid, Admin admin) throws GlobalException {
		Optional<Admin> a=adminRepository.findById(adminid);
		Admin a1=null;
		if(a.isPresent()) {
			a1=a.get();
			if(admin.getAdminname()!=null) {
				a1.setAdminname(admin.getAdminname());
			}
			if(admin.getAdminemail()!=null) {
				a1.setAdminemail(admin.getAdminemail());
			}
			if(admin.getAdminpassword()!=null) {
				a1.setAdminpassword(admin.getAdminpassword());
			}
		}else {
			throw new GlobalException("Admin with id "+adminid+" not found");
		}
		return adminRepository.save(a1);
	}
	@Override
	public Admin getAdminByEmail(String adminemail) {
		
		return adminRepository.findAdminByEmail(adminemail);
	}

	@Override
	public Admin updateAdminPassword(Integer adminid, String newPassword) throws GlobalException {
		Optional<Admin> optionalAdmin = adminRepository.findById(adminid);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setAdminpassword(newPassword);
            return adminRepository.save(admin);
        } else {
            throw new GlobalException("Customer with ID " + adminid + " not found");
     
	}
	
	}
}
