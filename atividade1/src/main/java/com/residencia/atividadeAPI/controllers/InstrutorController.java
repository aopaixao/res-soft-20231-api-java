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

import com.residencia.atividadeAPI.entities.Instrutor;
import com.residencia.atividadeAPI.services.InstrutorService;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

	@Autowired
	InstrutorService instrutorService;
	
	@GetMapping	
	public ResponseEntity<List<Instrutor>>  getAllInstrutores(){
		return new ResponseEntity<>(instrutorService.getAllInstrutores(),
				HttpStatus.OK);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Instrutor> getInstrutorById(@PathVariable Long id) {
		
		Instrutor instrutorResponse = instrutorService.getInstrutorById(id);
		if(instrutorResponse == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(instrutorResponse,
					HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Instrutor> saveInstrutor(@RequestBody Instrutor instrutor) {
		
		return new ResponseEntity<>(instrutorService.saveInstrutor(instrutor),
				HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Instrutor> updateInstrutor(@RequestBody Instrutor instrutor) {
		
		if(instrutorService.getInstrutorById(instrutor.getIdInstrutor()) != null) {
			return new ResponseEntity<> (instrutorService.updateInstrutor(instrutor),
					HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (instrutor,
					HttpStatus.NOT_FOUND);
		}		
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Boolean> deleteInstrutor(@PathVariable Long id) {
		
		if(instrutorService.deleteInstrutor(id) == false)
			return new ResponseEntity<>(false,
					HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<>(true,
					HttpStatus.OK);		
	}
}

