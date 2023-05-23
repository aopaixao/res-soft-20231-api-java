package com.residencia.atividadeAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.atividadeAPI.entities.Telefone;
import com.residencia.atividadeAPI.services.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	TelefoneService telefoneService;
	
	@GetMapping	
	public ResponseEntity<List<Telefone>>  getAllTelefones(){
		return new ResponseEntity<>(telefoneService.getAllTelefones(),
				HttpStatus.OK);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Telefone> getTelefoneById(@PathVariable Long id) {
		
		Telefone telefoneResponse = telefoneService.getTelefoneById(id);
		if(telefoneResponse == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(telefoneResponse,
					HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Telefone> saveTelefone(@RequestBody Telefone telefone) {
		
		if(telefoneService.saveTelefone(telefone) != null) {
			return new ResponseEntity<>(telefoneService.saveTelefone(telefone),
					HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(telefone,
					HttpStatus.NOT_MODIFIED);
		}
		
	}
	
	@PutMapping
	public ResponseEntity<Telefone> updateTelefone(@RequestBody Telefone telefone) {
		
		if(telefoneService.getTelefoneById(telefone.getIdTelefone()) != null) {
			return new ResponseEntity<> (telefoneService.updateTelefone(telefone),
					HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (telefone,
					HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Boolean> deleteTelefone(@PathVariable Long id) {
		
		if(telefoneService.deleteTelefone(id) == false)
			return new ResponseEntity<>(false,
					HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<>(true,
					HttpStatus.OK);		
	}
}

