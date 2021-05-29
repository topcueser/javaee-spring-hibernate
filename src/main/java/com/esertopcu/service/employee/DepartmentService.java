package com.esertopcu.service.employee;

import java.util.List;

import com.esertopcu.domain.employee.Department;

public interface DepartmentService {

	boolean saveDepartment(Department department);
	boolean deleteDepartment(Department department);
	Department updateDepartment(Department department);
	Department findDepartmentById(Long departmentId);
	List<Department> findAllDepartments();
	List<String> findDepartmentNames();
}
