
import module java.base;

/**
 * CreatePurchase - Create a purchase object
 *
 * @author Sxtormulo
 * Copyright 2026-03-26T00:41-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: Invoice number and sales amount
 * Output: the data of a Purchase object
 *
 * Get and validate invoice number and sales amount
 * In the purchase object fill the tax amount automatically
 * Display the purchase data
 */
public class CreatePurchase
{

    static final int NO_SALE = 0;
    static final int MAX_ID = 8_000;
    static final int MIN_ID = 1_000;
    static final double SALES_TAX = 0.5;

    record Purchase(int invoiceID, int salesAmount, int salesTaxes, double salesTax)
            {

        public Purchase(int invoiceID, int salesAmount, int salesTaxes, double salesTax)
        {
            if(invoiceID < MIN_ID)
            {
                this.invoiceID = MIN_ID;
            }
            else if(invoiceID > MAX_ID)
            {
                this.invoiceID = MAX_ID;
            }
            else
            {
                this.invoiceID = invoiceID;
            }

            if(salesAmount < NO_SALE)
            {
                this.salesAmount = NO_SALE;
                this.salesTaxes = NO_SALE;
            }
            else
            {
                this.salesAmount = salesAmount;
                this.salesTaxes = salesTaxes;
            }
            this.salesTax = salesTax;
        }

        public Purchase(int invoiceID, int salesAmount, double salesTax)
        {
            int calculatedTaxes = (int) (salesAmount * salesTax);
            this(invoiceID, salesAmount, calculatedTaxes, salesTax);
        }

    }

    void main()
    {
        int invoiceID = getInvoiceID();
        int salesAmount = getSalesAmount();
        Purchase purchase = new Purchase(invoiceID, salesAmount, SALES_TAX);

        IO.println(purchase);
    }

    int getInvoiceID()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln("Enter invoice id>>> ")
                );
                if(userInput < MIN_ID || userInput > MAX_ID)
                {
                    throw new InputMismatchException(
                            "Invoice id is out of bounds(%d-%d): %d".formatted(MIN_ID,
                                                                               MAX_ID,
                                                                               userInput));
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidException)
            {
                IO.println(invalidException.getMessage());
            }
        }
        return userInput;
    }

    int getSalesAmount()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln("Enter sales amount>>> ")
                );
                if(userInput < NO_SALE)
                {
                    throw new InputMismatchException(
                            "Sales amount cannot be negative: %d".formatted(userInput));
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidException)
            {
                IO.println(invalidException.getMessage());
            }
        }
        return userInput;
    }
}
