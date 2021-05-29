package com.esertopcu.dao.employee.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.esertopcu.dao.employee.DepartmentRepository;
import com.esertopcu.domain.employee.Department;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean saveDepartment(Department department) {
		entityManager.persist(department);
		return true;
	}

	@Override
	public boolean deleteDepartment(Department department) {
		if(entityManager.contains(department)) {
			entityManager.remove(department);
			return true;
		}
		Department deleteDepartment = findDepartmentById(department.getId());
		entityManager.remove(deleteDepartment);
		return true;
	}

	@Override
	public Department updateDepartment(Department department) {
		Department updatedDepartment = entityManager.merge(department);
		entityManager.flush();
		return updatedDepartment;
	}

	@Override
	public Department findDepartmentById(Long departmentId) {
		TypedQuery<Department> typedQuery = entityManager.createNamedQuery("Department.findLocationAndEmployeesByDepartmentId", Department.class);
		typedQuery.setParameter("id", departmentId);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<Department> findAllDepartments() {
		TypedQuery<Department> typedQuery = entityManager.createNamedQuery("Department.findAdd", Department.class);
		return typedQuery.getResultList();
	}

	@Override
	public List<String> findDepartmentNames() {
		TypedQuery<String> typedQuery = entityManager.createNamedQuery("Department.findByDepartmentName", String.class);
		return typedQuery.getResultList();
	}

}
