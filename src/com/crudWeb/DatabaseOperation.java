package com.crudWeb;


import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;


public class DatabaseOperation {
	
	public static ArrayList personsListFromDB;




				 
	public static ArrayList<PersonBean> getPersonsListFromDB() {

		return PersonBean.personsList;
	}
	


	public static String savePersonDetailsInDB(PersonBean newPersonObj) {
		boolean saveResult = false;
		String navigationResult = "";
 
		saveResult = PersonBean.personsList.add(newPersonObj); 
		System.out.println(newPersonObj.getFirstName() + " added to list");
		if(saveResult != false) {
			navigationResult = "/personsList.xhtml?faces-redirect=true";
		} else {
			navigationResult = "/createPerson.xhtml?faces-redirect=true";
		}
		return navigationResult;
	}

	public static int getIdPositionInList(int Id) {
		int pos = 0;
		for (int i = 0; i < PersonBean.personsList.size(); i++) {
			if(PersonBean.personsList.get(i).getId() == Id) {
				pos=i;
			};
			
		} 
		
		return pos;
	}
	public static String editPersonRecordInDB(int personId) {
		int id = getIdPositionInList(personId);
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

			sessionMapObj.put("editRecordObj", PersonBean.personsList.get(id));
		
		
		return "/editPerson.xhtml?faces-redirect=true";
	}


	public static String updatePersonDetailsInDB(PersonBean updatePersonObj) {
		int pos = getIdPositionInList(updatePersonObj.getId());
		PersonBean.personsList.get(pos).setFirstName(updatePersonObj.getFirstName());
		PersonBean.personsList.get(pos).setLastName(updatePersonObj.getLastName());
		PersonBean.personsList.get(pos).setBirthDate(updatePersonObj.getBirthDate());
		PersonBean.personsList.get(pos).setEmail(updatePersonObj.getEmail());
		PersonBean.personsList.get(pos).setAddress(updatePersonObj.getAddress());

		return "/personsList.xhtml?faces-redirect=true";
	}


	
	public static String deletePersonRecordInDB(int personId){
		PersonBean.personsList.remove(getIdPositionInList(personId));
		return "/personsList.xhtml?faces-redirect=true";
	}
	
	public static boolean validateNameUniquenessInDB(int personId, String name) {
		String oname;
		for (int i = 0; i < PersonBean.personsList.size(); i++) {
			if(PersonBean.personsList.get(i)!=PersonBean.personsList.get(getIdPositionInList(i))) {
			oname = PersonBean.personsList.get(getIdPositionInList(personId)).getFirstName() ;
			if(oname == name)
				System.out.println("validatisoon aiai" + name + " oname "+ oname);
				return false;
			}}
		System.out.println("all good");
		return true;
	}
}