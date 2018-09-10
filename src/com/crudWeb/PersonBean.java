package com.crudWeb;



import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean @RequestScoped
public class PersonBean {

	private int id;  
	private String firstName;  
	private String lastName;  
	private String birthDate;  
	private String email;  
	private String address;
	final static ArrayList<PersonBean> personsList = new ArrayList<PersonBean>();  
	
	public ArrayList<PersonBean> personsListFromDB;

	public int getId() {
		return id;	
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String email) {
		this.lastName = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}  
	
	public int getMaxId() {
		int id = 0;
		int size = personsList.size();
		if (size > 0) {
		for(int i = 0; i < size;i++)
			
			if (personsList.get(i).getId()>=id) {
				id = personsList.get(i).getId();
			};
			
		}
		
		
		System.out.println("MaxId = " + id);
		return id;
		
	}
	

	@PostConstruct
	public void init() {
		personsListFromDB = DatabaseOperation.getPersonsListFromDB();
	}
	
	

	public ArrayList<PersonBean> personsList() {
		return personsListFromDB;
	}
	
	public String savePersonDetails(PersonBean newPersonObj) {
		newPersonObj.setId(getMaxId()+1);
		return DatabaseOperation.savePersonDetailsInDB(newPersonObj);
	}
	
	public String editPersonRecord(int personId) {
		return DatabaseOperation.editPersonRecordInDB(personId);
	}
	
	public String updatePersonDetails(PersonBean updatePersonObj) {
		return DatabaseOperation.updatePersonDetailsInDB(updatePersonObj);
	}
	
	public String deletePersonRecord(int personId) {
		return DatabaseOperation.deletePersonRecordInDB(personId);
	}
	




	
	public void validateNameUniqueness(FacesContext context, UIComponent comp,
			Object value, int personId, String name) {
		
		System.out.println(context.toString() + " " + comp.toString() + " inside validate method");
		if (DatabaseOperation.validateNameUniquenessInDB(personId, name) == false) {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage(
					"bad combination, already used");
			context.addMessage(comp.getClientId(context), message);
	}
	

}}