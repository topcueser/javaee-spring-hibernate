package com.esertopcu.domain.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
	@NamedQuery(name = "Department.findByDepartmentName", query = "SELECT d.name FROM Department d"),
	@NamedQuery(name = "Department.findLocationAndEmployeesByDepartmentId", query = "SELECT d FROM Department d LEFT OUTER JOIN FETCH d.location LEFT OUTER JOIN FETCH d.employees WHERE d.id = :id")
})
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id", foreignKey = @ForeignKey(foreignKeyDefinition = "location_fk"))
	private Location location;
	
	//FetchType
	// Veritabanımıza hibernate kullanarak department tablosuna select attığımızda o tabloya bağlı tablolarında gelip gelmeyeceğini 
	// FetchType kullanarak belirtiyoruz.
	
	// Department department = sorgu.....
	// departmen.getLocation() LAZY olduğunda location'unu ALAMAYIZ.
	// EAGER olduğunda location'da alabiliriz. Location location = departmen.getLocation();
	// location.getCity();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employees;
	
	public Department() {
		
	}

	public Department(String name, Location location) {
		this.name = name;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
