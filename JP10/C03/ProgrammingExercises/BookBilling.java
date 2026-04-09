void main()
{
    int quantity, discount;
    IO.println("Hello at Happy Memories Company the cost of a photobook is %2.2f"
               .formatted(computeBill()));
    quantity = Integer.parseInt(
        IO.readln("Enter the quatity of photobooks you need>>> "));
    IO.println("%d photobooks costs %2.2f"
               .formatted(quantity, computeBill(quantity)));
    discount = Integer.parseInt(
        IO.readln("Do you have a discount>>> "));
    IO.println("For the %d discount the price results %2.2f"
               .formatted(discount, computeBill(quantity, discount)));
}

static double computeBill()
{
    return computeBill(1, 0);
}

static double computeBill(int quantity)
{
    return computeBill(quantity, 0);
}

static double computeBill(int quantity, int discount)
{
    final double PHOTOBOOK_PRICE = 14.99;
    final int TAX  = 8;
    double preTaxCost, withDiscount, totalCost;

    preTaxCost = PHOTOBOOK_PRICE * quantity;
    withDiscount = preTaxCost - ( preTaxCost * ( (double) discount / 100));
    totalCost = withDiscount + (withDiscount * ( (double) TAX  / 100));
    return  totalCost;
}
