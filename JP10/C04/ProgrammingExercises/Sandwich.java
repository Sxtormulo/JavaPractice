//Copyright Gabriel Soto 2025
//Class to store sandwich data
class Sandwich
{
    
    private String mainIngredient;
    private String breadType;
    private Double price;
    
    Sandwich (String ingredient, String bread, Double price)
    {
        mainIngredient = ingredient;
        breadType = bread;
        this.price = price;
    }
    
    /**
    * Sets the value of price.
    * @param price The value to assign price.
    */
    public void setPrice(Double price) {
        this.price = price;
    }
    
	/**
	 * Returns the value of mainIngredient.
	 */
	public String getMainIngredient() {
		return mainIngredient;
	}


	/**
	 * Sets the value of mainIngredient.
	 * @param mainIngredient The value to assign mainIngredient.
	 */
	public void setMainIngredient(String mainIngredient) {
		this.mainIngredient = mainIngredient;
	}


	/**
	 * Returns the value of breadType.
	 */
	public String getBreadType() {
		return breadType;
	}


	/**
	 * Sets the value of breadType.
	 * @param breadType The value to assign breadType.
	 */
	public void setBreadType(String breadType) {
		this.breadType = breadType;
	}


	/**
	 * Returns the value of price.
	 */
	public Double getPrice() {
		return price;
	}



    
}
    