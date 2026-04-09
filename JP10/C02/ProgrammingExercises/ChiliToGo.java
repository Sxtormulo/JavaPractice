void main()
{
    final int ADULTS_MEAL_PRICE = 7, CHILDS_MEAL_PRICE = 4; //Meals price
    int orderedAdultsMeals, orderedChildsMeals;
    int adultsMealsTotalPrice, childsMealsTotalPrice, mealsTotalPrice;
    
    orderedAdultsMeals = Integer.parseInt(
                                      IO.readln(
                                            "Insert the numbers of adults's meals ordered>>> ")); 
    orderedChildsMeals = Integer.parseInt(
                                      IO.readln(
                                            "Insert the numbers of child's meals ordered>>> "));
    
    adultsMealsTotalPrice = orderedAdultsMeals * ADULTS_MEAL_PRICE;
    childsMealsTotalPrice = orderedChildsMeals * CHILDS_MEAL_PRICE;
    mealsTotalPrice = adultsMealsTotalPrice + childsMealsTotalPrice;
    
    String recipe = """
        The Huntington Boys and Girls Club fundraise sells:
        *   %d Adult's meals at %d. Total = %d
        *   %d Child's meals at %d. Total = %d
        *   Total collected = %d""".formatted(
            orderedAdultsMeals,
            ADULTS_MEAL_PRICE,
            adultsMealsTotalPrice,
            orderedChildsMeals,
            CHILDS_MEAL_PRICE,
            childsMealsTotalPrice,
            mealsTotalPrice);
    IO.println(recipe);
}
