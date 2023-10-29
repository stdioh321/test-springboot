package com.devsuperior.userdept.controller;

import com.devsuperior.userdept.controller.dto.DepartamentCreateForm;
import com.devsuperior.userdept.controller.dto.DepartamentDto;
import com.devsuperior.userdept.controller.dto.UserCreateForm;
import com.devsuperior.userdept.entities.Departament;
import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.DepartmentRepository;
import com.devsuperior.userdept.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController()
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;


    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Departament> result = departmentRepository.findById(id);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody DepartamentCreateForm departamentCreateForm) {
        Departament d = departmentRepository.save(departamentCreateForm.toEntity());
        return ResponseEntity.ok(DepartamentDto.toInstance(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putById(@PathVariable Long id, @Valid @RequestBody DepartamentCreateForm departamentCreateForm) {
        Optional<Departament> optDepartament = this.departmentRepository.findById(id);
        if (this.departmentRepository.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        Departament departament = optDepartament.get();
        departament.setName(departamentCreateForm.getName());
        return ResponseEntity.ok(this.departmentRepository.save(departament));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Departament> optDepartament = this.departmentRepository.findById(id);
        if (this.departmentRepository.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        Departament departament = optDepartament.get();
        this.departmentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

