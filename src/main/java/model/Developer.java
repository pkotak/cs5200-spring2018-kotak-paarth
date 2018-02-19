package model;

public class Developer extends Person{
	int personId;
	String developerKey;

	public Developer() {
		super();
	}

	public Developer(int personId, String developerKey) {
		super();
		this.personId = personId;
		this.developerKey = developerKey;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getDeveloperKey() {
		return developerKey;
	}

	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}

	@Override
	public String toString() {
		return "Developer [personId=" + personId + ", developerKey=" + developerKey +
				", First Name=" + super.getFirstName() + 
				", Last Name=" + super.getLastName() +
				", Username=" + super.getUsername() + 
				", Password=" + super.getPassword() +
				", Email=" + super.getEmail() + 
				", DOB=" + super.getDob() +"]";
	}

	
}
