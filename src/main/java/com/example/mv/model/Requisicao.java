package com.example.mv.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "requisicao")
public class Requisicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@NotNull
	@Size(min = 3, max = 50)
	public String funcionario;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(unique=true)
	public String lanche;

	public String getLanche() {
		return lanche;
	}

	public void setLanche(String lanche) {
		this.lanche = lanche;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requisicao other = (Requisicao) obj;
		return Objects.equals(id, other.id);
	}

}
