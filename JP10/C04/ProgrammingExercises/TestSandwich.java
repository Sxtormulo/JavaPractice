void main()
{
    String mainIngredient;
    String breadType;
    Double price;
    
    mainIngredient = IO.readln("Enter a ingredient for the sandwich");
    breadType = IO.readln("Enter a bread type");
    price = Double.parseDouble(IO.readln("Enter the sandwich price"));
    
    Sandwich userSandwich = new Sandwich(mainIngredient,breadType,price);
    
    IO.println("The sandwich of "+userSandwich.getMainIngredient()+"  with "+userSandwich.getBreadType()+" bread costs: "+userSandwich.getPrice());
}