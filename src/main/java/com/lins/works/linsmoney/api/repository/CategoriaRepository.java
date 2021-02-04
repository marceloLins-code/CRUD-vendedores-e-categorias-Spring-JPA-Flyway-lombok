package com.lins.works.linsmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.works.linsmoney.api.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

	

}
