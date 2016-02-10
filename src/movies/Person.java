package movies;

public class Person {
	
	private String firstName;
	private String lastName;
	private Gender gender;
	private int salary;
	
	public Person(String firstName, String lastName, Gender gender,
			int salary){
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.salary = salary;
	}
	
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}

	
	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}

	String toXMLString(){
		String firstNameTag = Tool.toXMLTag("firstName", this.firstName);
		String lastNameTag = Tool.toXMLTag("lastName", this.lastName);
		String genderTag = Tool.toXMLTag("gender", this.gender.toString());
		String salaryTag = Tool.toXMLTag("salary", String.valueOf(salary));
		String personTag = Tool.toXMLTag("person", String.join("", firstNameTag, lastNameTag, 
				genderTag, salaryTag));
		return personTag;
	}
}
