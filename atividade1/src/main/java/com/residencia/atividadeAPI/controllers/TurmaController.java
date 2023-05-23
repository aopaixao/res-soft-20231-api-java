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

import com.residencia.atividadeAPI.entities.Turma;
import com.residencia.atividadeAPI.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	TurmaService turmaService;
	
	@GetMapping	
	public ResponseEntity<List<Turma>>  getAllTurmas(){
		return new ResponseEntity<>(turmaService.getAllTurmaes(),
				HttpStatus.OK);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Turma> getTurmaById(@PathVariable Long id) {
		
		Turma turmaResponse = turmaService.getTurmaById(id);
		if(turmaResponse == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(turmaResponse,
					HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Turma> saveTurma(@RequestBody Turma turma) {
		
		return new ResponseEntity<>(turmaService.saveTurma(turma),
				HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma) {
		
		if(turmaService.getTurmaById(turma.getIdTurma()) != null) {
			return new ResponseEntity<> (turmaService.updateTurma(turma),
					HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (turma,
					HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Boolean> deleteTurma(@PathVariable Long id) {
		
		if(turmaService.deleteTurma(id) == false)
			return new ResponseEntity<>(false,
					HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<>(true,
					HttpStatus.OK);		
	}
}

