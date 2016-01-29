package movies;

public class Person {
	
	String firstName;
	String lastName;
	Gender gender;
	boolean hasOscar;
	boolean hasGoldenGlobe;
	
	public Person(String startFirstName, String startLastName, Gender startGender,
			boolean startHasOscar, boolean startHasGoldenGlobe){
		firstName = startFirstName;
		lastName = startLastName;
		gender = startGender;
		hasOscar = startHasOscar;
		hasGoldenGlobe = startHasGoldenGlobe;
	}
	
	String getFirstName(){
		return this.firstName;
	}
	
	void setFirstName(String newFirstName){
		this.firstName = newFirstName;
	}
	
	String getLastName(){
		return this.lastName;
	}
	
	void setLastName(String newLastName){
		this.lastName = newLastName;
	}
	
	Gender getGender(){
		return this.gender;
	}
	
	void setGender(Gender newGender){
		this.gender = newGender;
	}
	
	boolean isHasOscar(){
		return this.hasOscar;
	}
	
	void setHasOscar(boolean newHasOscar){
		this.hasGoldenGlobe = newHasOscar;
	}
	
	boolean isHasGoldenGlobe(){
		return this.hasGoldenGlobe;
	}
	
	void setHasGoldenGlobe(boolean newHasGoldenGlobe){
		this.hasGoldenGlobe = newHasGoldenGlobe;
	}
	
	String toXMLString(){
		String firstNameTag = Tool.toXMLTag("firstName", this.firstName);
		String lastNameTag = Tool.toXMLTag("lastName", this.lastName);
		String genderTag = Tool.toXMLTag("gender", this.gender.toString());
		String hasOscarTag = Tool.toXMLTag("hasOscar", String.valueOf(this.hasOscar));
		String hasGoldenGlobeTag = Tool.toXMLTag("hasGoldenGlobe", String.valueOf(this.hasGoldenGlobe));
		String personTag = Tool.toXMLTag("person", String.join("", firstNameTag, lastNameTag, 
				genderTag, hasOscarTag, hasGoldenGlobeTag));
		return personTag;
	}

}
