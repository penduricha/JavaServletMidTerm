package dao;
import java.util.*;
import entities.*;
import i_dao.IPersonDAO;

public class PersonDAO implements IPersonDAO {
	public List<Person> getPersons() {
		return persons;
	}
	
	List<Person> persons=new ArrayList<>();
	public PersonDAO() {
		// TODO Auto-generated constructor stub
		this.persons.add(new Person("HS1","Phu", "Phan", "Q12", "Game"));
		this.persons.add(new Person("HS2","Hung", "Phan", "Q12", "Game"));
		this.persons.add(new Person("HS3","Phong", "Phan", "Q12", "Bong"));
	}
	@Override
	public boolean addPerson(Person person) {
		// TODO Auto-generated method stub
		if(!persons.contains(person)) {
			persons.add(person);
		}
		return true;
	}
	@Override
	public boolean deletePerson(String id) {
		Person person=findPerson(id);
		if(person!=null) {
			persons.remove(person);
			return true;
		}
		return false;
	}
	@Override
	public Person findPerson(String id) {
		// TODO Auto-generated method stub
		for(Person person: getPersons()) {
			if(person.getId().equalsIgnoreCase(id))
				return person;
		}
		return null;
	}
	@Override
	public boolean updatePerson(Person person) {
		Person personFind=findPerson(person.getId());
		if(personFind!=null) {
			personFind.setId(person.getId());
			personFind.setFirstname(person.getFirstname());
			personFind.setLastname(person.getLastname());
			personFind.setAddress(person.getAddress());
			personFind.setBio(person.getBio());
			return true;
		}
		return false;
	}
	@Override
	public String getPersonsString() {
		// TODO Auto-generated method stub
		String s="";
		for(Person person: getPersons()) {
			s+=person+"\n";
		}
		return s;
	}

}
