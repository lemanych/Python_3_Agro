
public class Company {
	private String name;
	private String propertyType;
	private int employeesNumber;
	private String productType;
	private String income;
	
	public void output() {
		System.out.println("Компанія: "+ getName( ) 
							+ "\nТип власності: " + getPropertyType() 
							+ "\nКількість працівників: " + getEmployeesNumber()
							+ "\nТип продукції: " + getProductType()
							+ "\nПрибуток: " + getIncome() + "\n");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public int getEmployeesNumber() {
		return employeesNumber;
	}

	public void setEmployeesNumber(int employeesNumber) {
		this.employeesNumber = employeesNumber;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}
}
