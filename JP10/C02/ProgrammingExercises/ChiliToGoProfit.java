void main()
{
    final int ADULTS_MEAL_PRICE = 7, CHILDS_MEAL_PRICE = 4; //Meals price
    final double ADULTS_MEAL_PROFIT_PRICE = 4.35, CHILDS_MEAL_PROFIT_PRICE = 3.10; //Production Meals price
    int orderedAdultsMeals, orderedChildsMeals;
    int adultsMealsTotalPrice, childsMealsTotalPrice, mealsTotalPrice;
    double adultsMealsProfit, childsMealsProfit, mealsTotalProfit;
    
    orderedAdultsMeals = Integer.parseInt(
                                      IO.readln(
                                            "Insert the numbers of adults's meals ordered>>> ")); 
    orderedChildsMeals = Integer.parseInt(
                                      IO.readln(
                                            "Insert the numbers of child's meals ordered>>> "));
    
    adultsMealsTotalPrice = orderedAdultsMeals * ADULTS_MEAL_PRICE;
    childsMealsTotalPrice = orderedChildsMeals * CHILDS_MEAL_PRICE;
    mealsTotalPrice = adultsMealsTotalPrice + childsMealsTotalPrice;
    
    adultsMealsProfit = adultsMealsTotalPrice -
                    ( orderedAdultsMeals * ADULTS_MEAL_PROFIT_PRICE );
    childsMealsProfit = childsMealsTotalPrice -
                    ( orderedChildsMeals * CHILDS_MEAL_PROFIT_PRICE );
    mealsTotalProfit = mealsTotalPrice -
                    ( adultsMealsProfit + childsMealsProfit );
    
    
    String recipe = """
        The Huntington Boys and Girls Club fundraise sells:
        *   %d Adult's meals at %d. Total = %d
        *   %d Child's meals at %d. Total = %d
        *   Total collected = %d
        Profit:
        Adults's meals profit = %.2f
        Child's meals profit = %.2f
        Totals profit = %.2f""".formatted(
            orderedAdultsMeals,
            ADULTS_MEAL_PRICE,
            adultsMealsTotalPrice,
            orderedChildsMeals,
            CHILDS_MEAL_PRICE,
            childsMealsTotalPrice,
            mealsTotalPrice,
            adultsMealsProfit,
            childsMealsProfit,
            mealsTotalProfit);
    IO.println(recipe);
}
