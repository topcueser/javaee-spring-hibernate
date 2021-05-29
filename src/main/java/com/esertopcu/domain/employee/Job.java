package com.esertopcu.domain.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Job.findAll", query = "SELECT l FROM Location l"),
	@NamedQuery(name = "Job.findAllByTitle", query = "SELECT j FROM Job j WHERE j.title = :title"),
	@NamedQuery(name = "Job.findEmployeeById", query = "SELECT j FROM Job j LEFT OUTER JOIN FETCH j.employees WHERE j.id = :id")
})
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column
	private double minSalary;
	
	@Column
	private double maxSalary;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
	private List<Employee> employees;
	
	public Job() {
		
	}

	public Job(String title, double minSalary, double maxSalary) {
		this.title = title;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
