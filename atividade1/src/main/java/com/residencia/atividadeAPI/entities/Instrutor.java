package com.residencia.atividadeAPI.entities;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(
		scope = Instrutor.class,
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idInstrutor"
		)
@Entity
@Table (name = "instrutor")
public class Instrutor {	
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column (name = "id")
		private Long idInstrutor;
		
		@Column (name = "rg")
		private Integer rg;
		
		@Column (name = "nome")
		private String nome;
		
		@OneToOne (mappedBy = "instrutor")
		private Telefone telefone;
		
		@OneToMany (mappedBy = "instrutor")
		private List<Turma> turmas;

		public Long getIdInstrutor() {
			return idInstrutor;
		}

		public void setIdInstrutor(Long idInstrutor) {
			this.idInstrutor = idInstrutor;
		}

		public Integer getRg() {
			return rg;
		}

		public void setRg(Integer rg) {
			this.rg = rg;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Telefone getTelefone() {
			return telefone;
		}

		public void setTelefone(Telefone telefone) {
			this.telefone = telefone;
		}

		public List<Turma> getTurmas() {
			return turmas;
		}

		public void setTurmas(List<Turma> turmas) {
			this.turmas = turmas;
		}

		
}
