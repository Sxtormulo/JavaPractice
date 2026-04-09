/**
 * AcmePay - Calculate the worker payment
 *
 * @author Gabriel Soto 2026-02-16T22:48+00:00 SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: The worker shift number and the worked hours Output: Hours worked, payment and
 * worker data.
 *
 * Get the user shift and worked hours Choose the multipliers base in the shift Ask for
 * retirement plan if is shift two or three if choosed deduct the retirement percetage
 * Return worked hours, shift, hourly pay rate, regular pay, overtime pay, sum of
 * regular and overtime pay, retirement deduction and net payment
 */

final static int FULL_SHIFT = 40;
final static float OVERTIME_RATE_MULTIPLIER = 1.5f;
final static float RETIREMENT_PLAN_DEDUCTION = 0.03f;
final static String POSITIVE_ANSWER = "YES";



public enum Shifts
{
    FIRST(1, 17f),
    SECOND(2,18.5f),
    THIRD(3, 22f);

    final Shift shift;

    Shifts(int id, float payrate)
    {
        shift = new Shift(id, payrate);
    }
    record Shift(int id, float payrate) {};
}


public static Shifts getShift()
{
    int shiftID= Integer.parseInt(IO.readln("Enter your shift id>>> "));
    return switch(shiftID)
    {
        case 1 -> Shifts.FIRST;
        case 2 -> Shifts.SECOND;
        case 3 -> Shifts.THIRD;
        default -> throw new IllegalArgumentException("ID %d does not correspond to a shift."
                                                      .formatted(shiftID));
    };
}


static void main()
{
    Shifts userShift;
    int workedHours;
    float payrate, regularPay, overtimePay = 0, grossPay, retirementDeduction, netPay;

    userShift = getShift();
    payrate = userShift.shift.payrate();
    workedHours = Integer.parseInt(IO.readln("Enter the number of worked hours>>> "));

    if( workedHours > FULL_SHIFT)
    {
        regularPay = payrate * FULL_SHIFT;
        overtimePay = payrate * ( workedHours - FULL_SHIFT ) * OVERTIME_RATE_MULTIPLIER;
    }
    else
    {
        regularPay = payrate * workedHours;
    }

    grossPay = regularPay + overtimePay;

    retirementDeduction = getRetirementDeduction(userShift, grossPay);

    netPay = grossPay - retirementDeduction;

    displayPayroll(workedHours, userShift, regularPay, overtimePay, grossPay, retirementDeduction, netPay);
}

static float getRetirementDeduction(Shifts workerShift, float grossPay)
{
    return switch(workerShift)
    {
        case FIRST -> 0f;
        case SECOND, THIRD ->
        {
            String awnswer = IO.readln("You are elegible to retirement participation.\nIf you accept you are going to get the %%%.2f deducted from your salary\nDo you accept? [YES/NO]>>> "
                                       .formatted((RETIREMENT_PLAN_DEDUCTION * 100)))
                                       .toUpperCase();

            if(awnswer.equals(POSITIVE_ANSWER))
            {
                yield (grossPay * RETIREMENT_PLAN_DEDUCTION);
            }
            yield 0f;
        }
    };
}

static void displayPayroll(int workedHours, Shifts workerShift, float regularPay, float overtimePay, float grossPay, float retirementDeduction, float netPay)
{
    int regularHours, overtimeHours = 0, shiftID;
    float payrate;
    shiftID = workerShift.shift.id();
    payrate = workerShift.shift.payrate();

    if(workedHours > FULL_SHIFT)
    {
        regularHours = FULL_SHIFT;
        overtimeHours = workedHours - FULL_SHIFT;
    }
    else
    {
        regularHours = workedHours;
    }

    String payroll = """
        Worker of shift %d. Payrate %.2f
        Payment for %d regular worked hours = %.2f
        Payment for %d overtime worked hours = %.2f
        Gross Payment %.2f
        - Retirement Deduction %.2f
        Net payment %.2f
        """.formatted(shiftID,
                        payrate,
                        regularHours, regularPay,
                        overtimeHours, overtimePay,
                        grossPay,
                        retirementDeduction,
                        netPay);
    IO.println(payroll);

}
