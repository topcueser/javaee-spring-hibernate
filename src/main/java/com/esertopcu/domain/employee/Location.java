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
//@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l") // tek tek yazmak istenildiginde
@NamedQueries({
	@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
	@NamedQuery(name = "Location.findDepartmentById", query = "SELECT l FROM Location l LEFT OUTER JOIN FETCH l.departments WHERE l.id = :id")
})
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Column(length = 25)
	private String streetAddress;
	
	@Column(name = "postalCode")
	private int postalCode;
	
	//Eger hic birsey girmez isek hibernate default degerleri ile column olusturur
	private String city;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private List<Department> departments;
	
	//@OneToMany
	// 1'e n(cok), Bir tane location da birden fazla employee olabilir.
	// Bu cok department i bir collection yapısında tutmamız gerekir.
	// Örneğin city i adana olan bir location da 5 tane department olsun, departmentleri list halinde tutacağız.
	
	// Cascade 
	// Bu location'da bir değişiklik yaptığımızda ona bağlı birimlerin yani buradaki örneklerde department'lerde nasıl değişeceğini belirtiyoruz
	
	// mappedBy
	// MappedBy kullarak hangi clas'ın diğeri üzerinde üstün olduğunu birinci sınıf class olduğunu belirtiyoruz.
	// Nedeni : birinci sınıf department olsun istedik. Department tablosunda location foreign eklemesini söyledik.
	
	public Location() {
		
	}
	
	public Location(String streetAddress, int postalCode, String city) {
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
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
		Location other = (Location) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
