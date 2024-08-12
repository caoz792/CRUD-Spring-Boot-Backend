package com.gestion.campistas.repository;

import com.gestion.campistas.model.Campista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampistaRepository extends JpaRepository<Campista, Long> {
}
