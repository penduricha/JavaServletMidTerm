package i_dao;

import entities.Person;

public interface IPersonDAO {
	public boolean addPerson(Person person);
	public boolean deletePerson(String id);
	public Person findPerson(String id);
	public boolean updatePerson(Person person);
	public String getPersonsString();
}
