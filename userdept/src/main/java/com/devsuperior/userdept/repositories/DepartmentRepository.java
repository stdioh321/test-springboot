package com.devsuperior.userdept.repositories;

import com.devsuperior.userdept.entities.Departament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Departament, Long> {
}