package com.residencia.atividadeAPI.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(
		scope = Turma.class,
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idTurma"
		)
@Entity
@Table (name = "turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long idTurma;
	
	@Column (name = "nome_disciplina")
	private String nome_disciplina;
	
	@Column (name = "dia_semana")
	private String dia_semana;
	
	@ManyToOne
	@JoinColumn (name = "id_instrutor" , referencedColumnName = "id")
	private Instrutor instrutor;

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public String getNome_disciplina() {
		return nome_disciplina;
	}

	public void setNome_disciplina(String nome_disciplina) {
		this.nome_disciplina = nome_disciplina;
	}

	public String getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	

	
	
}
