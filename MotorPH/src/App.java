import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //Employee info
        int[] employeeNumber = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11, 12, 13, 14, 15, 16, 17, 18, 19, 20,21, 22, 23, 24, 25, 26, 27, 28, 29, 30,31, 32, 33, 34};
        String[] employeeName = {"Garcia, Manuel III", "Lim, Antonio", "Aquino, Bianca Sofia", "Reyes, Isabella", "Hernandez, Eduard", "Villanueva, Andrea Mae", "San Jose, Brad ", "Romualdez, Alice", "Atienza, Rosie", "Alvaro, Roderick", "Salcedo, Anthony", "Lopez, Josie", "Farala, Martha", "Martinez, Leila", "Romualdez, Fredrick", "Mata, Christian", "De Leon, Selena", "San Jose, Allison", "Rosario, Cydney", "Bautista, Mark", "Lazaro, Darlene", "Delos Santos, Kolby", "Santos, Vella", "Del Rosario, Tomas", "Tolentino, Jacklyn", "Gutierrez, Percival", "Manalaysay, Garfield", "Villegas, Lizeth", "Ramos, Carol", "Maceda, Emelia", "Aguilar, Delia", "Castro, John Rafae", "Martinez, Carlos Ian", "Santos, Beatriz"};
        String[] employeeBirthday = {"10/11/1983", "06/19/1988", "08/04/1989", "06/16/1994", "09/23/1989", "02/14/1988", "03/15/1996", "05/14/1992", "09/24/1948", "03/30/1988", "09/14/1993", "01/14/1987", "01/11/1942", "07/11/1970", "03/10/1985", "10/21/1987", "02/20/1975", "06/24/1986", "10/06/1996", "02/12/1991", "11/25/1985", "02/26/1980", "12/31/1983", "12/18/1978", "05/19/1984", "12/18/1970", "08/28/1986", "12/12/1981", "08/20/1978", "04/14/1973", "01/27/1989", "02/09/1992", "11/16/1990", "08/07/1990"};

        //Employee info Scanners
        Scanner viewEmployeeInfoScanner = new Scanner(System.in);
        Scanner searchForEmployeeScanner = new Scanner(System.in);
        Scanner specificEmployeeScanner = new Scanner(System.in);

        int systemLoop = 0;

        while(systemLoop < 1){
            //Confirm if user wants to view employee info
            //viewEmployeeInfoScanner
            System.out.print("\nView employee information? (Y|N): ");
            String viewEmployeeInfo = viewEmployeeInfoScanner.nextLine();

            if(viewEmployeeInfo.equals("Y")){
                //Confirm if user wants to search for a specific employee
                //searchForEmployeeScanner
                System.out.print("Search for a specific employee or view all? (Press Y to search and A to view all): ");
                String searchForEmployee = searchForEmployeeScanner.nextLine();

                if(searchForEmployee.equals("Y")){
                    //specificEmployeeScanner
                    System.out.print("Enter employee number: ");
                    int specificEmployeeNumber = specificEmployeeScanner.nextInt();
                    int finalEmployeeNumber = specificEmployeeNumber - 1;

                    //Print specific employee details
                    System.out.println("Employee: " + "#" + employeeNumber[finalEmployeeNumber] + " " + employeeName[finalEmployeeNumber] + " " + "[" + employeeBirthday[finalEmployeeNumber] + "]");
                }else if(searchForEmployee.equals("A")){
                    for(int i = 0; i < 34; i++){
                        System.out.println("#" + employeeNumber[i] + " " + employeeName[i] + " " + "[" + employeeBirthday[i] + "]");
                    }
                }else{
                    System.out.println("Wrong Input! Try again");
                    continue;
                }
                continue;
            }else if(viewEmployeeInfo.equals("N")){
                System.out.println("\nCalculate Hours Worked:");
                System.out.println("Sample Input: [Time in: 0800] [Time out: 1700]");
                System.out.println("REMINDER: This is a case-sensitive program; if your input is invalid, errors will occur!\n");
                break;
            }else{
                System.out.println("Wrong Input! Try again");
                continue;
            }
        }
        //Calculating hours scanner
        Scanner hoursWorkedScanner = new Scanner(System.in);

        // Input time in and time out for each day of the week
        int totalMinutesWorked = 0;
        for (int day = 1; day <= 7; day++) {
            System.out.println("Day " + day);
            System.out.print("Enter time in (HHMM format): ");
            String timeInStr = hoursWorkedScanner.nextLine();

            System.out.print("Enter time out (HHMM format): ");
            String timeOutStr = hoursWorkedScanner.nextLine();

            int minutesWorked = calculateMinutesWorked(timeInStr, timeOutStr);
            totalMinutesWorked += minutesWorked;
        }

        int totalHours = totalMinutesWorked / 60;
        int remainingMinutes = totalMinutesWorked % 60;

        System.out.println("Total hours worked in the week: " + totalHours + " hours and " + remainingMinutes + " minutes");

        //Calculate gross weekly salary
        double employeeGrossSalary = 0;

        System.out.println("\nCalculate Gross Weekly Salary\n");
        Scanner hourlyRateScanner = new Scanner(System.in);
        System.out.print("Enter employee hourly rate: ");
        double employeeHourlyRate = hourlyRateScanner.nextDouble();

        if(remainingMinutes > 60){
            employeeGrossSalary = (totalHours + 1) * employeeHourlyRate;
            System.out.println("Employee Gross Weekly Salary: " + String.format("%.2f", employeeGrossSalary));
        }else{
            employeeGrossSalary = totalHours * employeeHourlyRate;
            System.out.println("Employee Gross Weekly Salary: " + String.format("%.2f", employeeGrossSalary));
        }

        //APPLYING DEDUCTIONS
        System.out.println("\nGeneral deductions:\n");

        //Amount of SSS contribution
        double employeeSssContribution = sssDeduction(employeeGrossSalary);
        System.out.println("SSS Deduction: " + String.format("%.2f", employeeSssContribution));
        //Amount of Pag-ibig contribution
        double employeePagIbigContribution = pagIbigDeduction(employeeGrossSalary);
        System.out.println("Pag-Ibig Deduction: " + String.format("%.2f", employeePagIbigContribution));
        //Amount of Philhealth contribution
        double employeePhilhealthContribution = philHealthDeduction(employeeGrossSalary);
        System.out.println("Philhealth Deduction: " + String.format("%.2f", employeePhilhealthContribution));
        //Total deductions
        double employeeTotalDeduction = totalDeductions(employeeSssContribution, employeePagIbigContribution, employeePhilhealthContribution);
        System.out.println("TOTAL DEDUCTIONS: " + String.format("%.2f", employeeTotalDeduction));
        //Deducted gross salary
        double grossSalaryWithDeduction = employeeGrossSalary - employeeTotalDeduction; 
        System.out.println("\nTAXABLE INCOME: " + String.format("%.2f", grossSalaryWithDeduction));
        //Witholding tax deductions
        double salaryWithTax = witholdingTaxDeduction(grossSalaryWithDeduction);
        System.out.println("Witholding Tax: " + String.format("%.2f", salaryWithTax));
        //Net weekly salary
        double netWeeklySalary = grossSalaryWithDeduction - salaryWithTax;
        System.out.println("\nEmployee Net Weekly Salary: " + String.format("%.2f", netWeeklySalary));

        //Closing all scanners
        viewEmployeeInfoScanner.close();
        searchForEmployeeScanner.close();
        specificEmployeeScanner.close();
        hoursWorkedScanner.close();
        hourlyRateScanner.close();
    }
    //Calculate minutes
    public static int calculateMinutesWorked(String timeInStr, String timeOutStr) {
        int timeInHours = Integer.parseInt(timeInStr.substring(0, 2));
        int timeInMinutes = Integer.parseInt(timeInStr.substring(2));
        int timeOutHours = Integer.parseInt(timeOutStr.substring(0, 2));
        int timeOutMinutes = Integer.parseInt(timeOutStr.substring(2));

        int totalMinutesIn = timeInHours * 60 + timeInMinutes;
        int totalMinutesOut = timeOutHours * 60 + timeOutMinutes;

        int totalMinutesWorked = totalMinutesOut - totalMinutesIn;

        return totalMinutesWorked;
    }
    //SSS amount (Method)
    public static double sssDeduction(double employeeGrossSalary){
        double totalSssDeduction = 0;
        if(employeeGrossSalary < 3250){
            totalSssDeduction = 135;
        }else if(employeeGrossSalary >= 3250 && employeeGrossSalary <= 3749.99){
            totalSssDeduction = 157.50;
        }else if(employeeGrossSalary >= 3750 && employeeGrossSalary <= 4249.99){
            totalSssDeduction = 180;
        }else if(employeeGrossSalary >= 4250 && employeeGrossSalary <= 4749.99){
            totalSssDeduction = 202.50;
        }else if(employeeGrossSalary >= 4750 && employeeGrossSalary <= 5249.99){
            totalSssDeduction = 225;
        }else if(employeeGrossSalary >= 5250 && employeeGrossSalary <= 5749.99){
            totalSssDeduction = 247.50;
        }else if(employeeGrossSalary >= 5750 && employeeGrossSalary <= 6249.99){
            totalSssDeduction = 270;
        }else if(employeeGrossSalary >= 6250 && employeeGrossSalary <= 6749.99){
            totalSssDeduction = 292.50;
        }else if(employeeGrossSalary >= 6750 && employeeGrossSalary <= 7249.99){
            totalSssDeduction = 315;
        }else if(employeeGrossSalary >= 7250 && employeeGrossSalary <= 7749.99){
            totalSssDeduction = 337.5;
        }else if(employeeGrossSalary >= 7750 && employeeGrossSalary <= 8249.99){
            totalSssDeduction = 360;
        }else if(employeeGrossSalary >= 8250 && employeeGrossSalary <= 8749.99){
            totalSssDeduction = 382.5;
        }else if(employeeGrossSalary >= 8750 && employeeGrossSalary <= 9249.99){
            totalSssDeduction = 405;
        }else if(employeeGrossSalary >= 9250 && employeeGrossSalary <= 9749.99){
            totalSssDeduction = 427.5;
        }else if(employeeGrossSalary >= 9750 && employeeGrossSalary <= 10249.99){
            totalSssDeduction = 450;
        }else if(employeeGrossSalary >= 10250 && employeeGrossSalary <= 10749.99){
            totalSssDeduction = 472.5;
        }else if(employeeGrossSalary >= 10750 && employeeGrossSalary <= 11249.99){
            totalSssDeduction = 495;
        }else if(employeeGrossSalary >= 11250 && employeeGrossSalary <= 11749.99){
            totalSssDeduction = 517.5;
        }else if(employeeGrossSalary >= 11750 && employeeGrossSalary <= 12249.99){
            totalSssDeduction = 540;
        }else if(employeeGrossSalary >= 12250 && employeeGrossSalary <= 12749.99){
            totalSssDeduction = 562.5;
        }else if(employeeGrossSalary >= 12750 && employeeGrossSalary <= 13249.99){
            totalSssDeduction = 585;
        }else if(employeeGrossSalary >= 13250 && employeeGrossSalary <= 13749.99){
            totalSssDeduction = 607.5;
        }else if(employeeGrossSalary >= 13750 && employeeGrossSalary <= 14249.99){
            totalSssDeduction = 630;
        }else if(employeeGrossSalary >= 14250 && employeeGrossSalary <= 14749.99){
            totalSssDeduction = 652.5;
        }else if(employeeGrossSalary >= 14750 && employeeGrossSalary <= 15249.99){
            totalSssDeduction = 675;
        }else if(employeeGrossSalary >= 15250 && employeeGrossSalary <= 15749.99){
            totalSssDeduction = 697.5;
        }else if(employeeGrossSalary >= 15750 && employeeGrossSalary <= 16249.99){
            totalSssDeduction = 720;
        }else if(employeeGrossSalary >= 16250 && employeeGrossSalary <= 16749.99){
            totalSssDeduction = 742.5;
        }else if(employeeGrossSalary >= 16750 && employeeGrossSalary <= 17249.99){
            totalSssDeduction = 765;
        }else if(employeeGrossSalary >= 17250 && employeeGrossSalary <= 17749.99){
            totalSssDeduction = 787.5;
        }else if(employeeGrossSalary >= 17750 && employeeGrossSalary <= 18249.99){
            totalSssDeduction = 810;
        }else if(employeeGrossSalary >= 18250 && employeeGrossSalary <= 18749.99){
            totalSssDeduction = 832.5;
        }else if(employeeGrossSalary >= 18750 && employeeGrossSalary <= 19249.99){
            totalSssDeduction = 855;
        }else if(employeeGrossSalary >= 19250 && employeeGrossSalary <= 19749.99){
            totalSssDeduction = 877.5;
        }else if(employeeGrossSalary >= 19750 && employeeGrossSalary <= 20249.99){
            totalSssDeduction = 900;
        }else if(employeeGrossSalary >= 20250 && employeeGrossSalary <= 20749.99){
            totalSssDeduction = 922.5;
        }else if(employeeGrossSalary >= 20750 && employeeGrossSalary <= 21249.99){
            totalSssDeduction = 945;
        }else if(employeeGrossSalary >= 21250 && employeeGrossSalary <= 21749.99){
            totalSssDeduction = 967.5;
        }else if(employeeGrossSalary >= 21750 && employeeGrossSalary <= 22249.99){
            totalSssDeduction = 990;
        }else if(employeeGrossSalary >= 22250 && employeeGrossSalary <= 22749.99){
            totalSssDeduction = 1012.5;
        }else if(employeeGrossSalary >= 22750 && employeeGrossSalary <= 23249.99){
            totalSssDeduction = 1035;
        }else if(employeeGrossSalary >= 23250 && employeeGrossSalary <= 23749.99){
            totalSssDeduction = 1057.5;
        }else if(employeeGrossSalary >= 23750 && employeeGrossSalary <= 24249.99){
            totalSssDeduction = 1080;
        }else if(employeeGrossSalary >= 24250 && employeeGrossSalary <= 24749.99){
            totalSssDeduction = 1102.5;
        }else if(employeeGrossSalary >= 24750){
            totalSssDeduction = 1125;
        }else{
            System.out.println("Wrong Input!");
        }

        return totalSssDeduction;
    }
    //Pag-ibig amount (Method)
    public static double pagIbigDeduction(double employeeGrossSalary){
        double pagIbigContribution = 0;
        if(employeeGrossSalary >= 1000 && employeeGrossSalary <= 1500){
            pagIbigContribution = employeeGrossSalary * .01;
            if(pagIbigContribution > 100){
                pagIbigContribution = 100;
            }
        }else if(employeeGrossSalary > 1500){
            pagIbigContribution = employeeGrossSalary * .02;
            if(pagIbigContribution > 100){
                pagIbigContribution = 100;
            }
        }
        return pagIbigContribution;
    }
    //Philhealth amount (Method)
    public static double philHealthDeduction(double employeeGrossSalary){
        double philHealthContribution = (employeeGrossSalary * .03) / 2;
        return philHealthContribution;
    }
    //Total deductions (Method)
    public static double totalDeductions(double employeeSssContribution, double employeePagIbigContribution, double employeePhilhealthContribution){
        double totalContribution = employeeSssContribution + employeePagIbigContribution + employeePhilhealthContribution;
        return totalContribution;
    }
    // Witholding tax (Method)
    public static double witholdingTaxDeduction(double grossSalaryWithDeduction){
        double taxContribution = 0;
        if(grossSalaryWithDeduction <= 20832){
            taxContribution = 0;
        }else if(grossSalaryWithDeduction >= 20833 && grossSalaryWithDeduction < 33333){
            taxContribution = (grossSalaryWithDeduction - 20833) * .2;
        }else if(grossSalaryWithDeduction >= 33333 && grossSalaryWithDeduction < 66667){
            taxContribution = ((grossSalaryWithDeduction - 33333) * .25) + 2500;
        }else if(grossSalaryWithDeduction >= 66667 && grossSalaryWithDeduction < 166667){
            taxContribution = ((grossSalaryWithDeduction - 66667) * .30) + 10833;
        }else if(grossSalaryWithDeduction >= 166667 && grossSalaryWithDeduction < 666667){
            taxContribution = ((grossSalaryWithDeduction - 166667) * .32) + 40833.33;
        }else{
            taxContribution = ((grossSalaryWithDeduction - 666667) * .35) + 200833.33;
        }
        return taxContribution;
    }
}
