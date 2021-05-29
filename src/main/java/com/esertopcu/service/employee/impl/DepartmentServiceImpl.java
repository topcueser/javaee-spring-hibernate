package com.esertopcu.service.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esertopcu.dao.employee.DepartmentRepository;
import com.esertopcu.domain.employee.Department;
import com.esertopcu.service.employee.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRespository;
	
	@Override
	public boolean saveDepartment(Department department) {
		return departmentRespository.saveDepartment(department);
	}

	@Override
	public boolean deleteDepartment(Department department) {
		return departmentRespository.deleteDepartment(department);
	}

	@Override
	public Department updateDepartment(Department department) {
		return departmentRespository.updateDepartment(department);
	}

	@Override
	public Department findDepartmentById(Long departmentId) {
		return departmentRespository.findDepartmentById(departmentId);
	}

	@Override
	public List<Department> findAllDepartments() {
		return departmentRespository.findAllDepartments();
	}

	@Override
	public List<String> findDepartmentNames() {
		return departmentRespository.findDepartmentNames();
	}

}
