package com.residencia.atividadeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.atividadeAPI.entities.Telefone;
import com.residencia.atividadeAPI.repositories.TelefoneRepository;

@Service
public class TelefoneService {
	
	@Autowired
	TelefoneRepository telefoneRepository;
	
	public List <Telefone>getAllTelefones() {
		
		return telefoneRepository.findAll();
	}
	
	public Telefone getTelefoneById(Long id) {
		
		return telefoneRepository.findById(id).orElse(null);	
	}
	
	public Telefone saveTelefone(Telefone telefone) {
		Boolean verificaInstrutor = true;
        List<Telefone>listaTelefone = this.getAllTelefones();
        for (Telefone tel : listaTelefone) {
            if(tel.getInstrutor().getIdInstrutor() == telefone.getInstrutor().getIdInstrutor()) {
                verificaInstrutor = false;
            }
        }
        if(verificaInstrutor) {
            return telefoneRepository.save(telefone);
        }else {
            return null;
        }		
	}
	
	public Telefone updateTelefone(Telefone telefone) {
		
		return telefoneRepository.save(telefone);
	}	
	
	public Boolean deleteTelefone(Long id) {
		
		if(telefoneRepository.findById(id).orElse(null) != null) {
			telefoneRepository.deleteById(id);
			if(telefoneRepository.findById(id).orElse(null) == null)
				return true;
			else
				return false;
		}		
		else
			return false;
	}

}
