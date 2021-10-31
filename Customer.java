public class Customer {

	private String name;
	private String contact;
	private boolean member;

	public Customer(String name,String contact,boolean member){
		this.name=name;
		this.contact=contact;
		this.member=member;
	}
	
	public String getName(){
		return name;
	}

	public String getContact(){
		return contact;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setContact(String contact){
		this.contact=contact;
	}
	
	public boolean getMember(){
		return member;
	}
	
	public void setMember(boolean member){
		this.member=member;
	}
	

}