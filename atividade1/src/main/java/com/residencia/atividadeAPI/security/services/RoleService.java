package com.residencia.atividadeAPI.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.atividadeAPI.entities.Role;
import com.residencia.atividadeAPI.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
