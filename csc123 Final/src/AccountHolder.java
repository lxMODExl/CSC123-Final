import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountHolder {

	private String name, dateOfBirth, ssn, address;
	
	public AccountHolder(String name, String dateOfBirth, String ssn, String address) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
		this.address = address;
	}
	
	public AccountHolder(String name, String dateOfBirth, String ssn) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
	}

	public AccountHolder() {
		
	}

	public String getName() {
		return name;
	}

	public String getDOB() {
		return dateOfBirth;
	}

	public String getSSN() {
		return ssn;
	}

	public String getAddress() {
		return address;
	}
	
	public long getAgeInYears(String birthDate) throws Exception{

		SimpleDateFormat format = new SimpleDateFormat("MM/DD/YYYY");
		Date dob = format.parse(birthDate);
		Date today = new Date();
		
		long dobInMillisec = dob.getTime();	
		long todayInMillisec = today.getTime();
		long delta = todayInMillisec-dobInMillisec;
		
		long age = delta/1000/60/60/24/365;
		return age;
	}
}