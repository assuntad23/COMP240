package test;

public class Demo {
	
	enum DayofTheWeek {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday};

	public static void main(String[] args) {

		//DayofTheWeek hireDay = DayofTheWeek.Monday;
		
		DayofTheWeek hireDateJohn = DayofTheWeek.Sunday;
		
		DayofTheWeek hireDateJane = DayofTheWeek.Friday;
		
		int comp = hireDateJohn.compareTo(hireDateJane);
			if (comp > 0)
				System.out.println("Jane has seniority");
			else if (comp < 0)
				System.out.println("John has seniority");
			else
				System.out.println("They are peers");
			
		System.out.println(hireDateJane.values());
		
		DayofTheWeek[] values = DayofTheWeek.values();
		
		switch(hireDateJohn){
		
		case Sunday:
			System.out.println("John was hired on Sunday; he cannot work at the Post Office or as a Car Salesman.");
			break;
		case Monday:
			System.out.println("John was hired on the worst day of the week.");
		}
	}

}
