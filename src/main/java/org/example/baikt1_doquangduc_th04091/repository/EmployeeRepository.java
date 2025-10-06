package org.example.baikt1_doquangduc_th04091.repository;

import org.example.baikt1_doquangduc_th04091.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
