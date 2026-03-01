package vn.edu.hcmut.cse.adsoftweng.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    
    public List<Student> getAll() {
        return repository.findAll();
    }
    
    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Student student) {
        repository.save(student);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<Student> searchByName(String name) {
        return repository.findAll().stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}