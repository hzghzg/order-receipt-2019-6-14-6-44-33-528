package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    public static final double TAXRATE = .10;
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        printHeaders(output);

        // prints lineItems
        double totSalesTx = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : o.getLineItems()) {
            printLineItems(output,lineItem);

            // calculate sales tax @ rate of 10%
            totSalesTx += calculateSalesTax(lineItem);

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount += lineItem.totalAmount() + calculateSalesTax(lineItem);
        }

        // prints the state tax
        printTheStateTax(output,totSalesTx);

        // print total amount
        printTotalAmount(output,totalAmount);
        return output.toString();
    }

    public  StringBuilder printHeaders(StringBuilder output) {
        output.append("======Printing Orders======\n").append(o.getCustomerName()).append(o.getCustomerAddress());
        return output;
    }
    public  StringBuilder printLineItems(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription()).append('\t');
        output.append(lineItem.getPrice()).append('\t');
        output.append(lineItem.getQuantity()).append('\t');
        output.append(lineItem.totalAmount()).append('\n');
        return output;
    }

    public double calculateSalesTax(LineItem lineItem){
        double salesTax = lineItem.totalAmount() * TAXRATE;
        return salesTax;
    }

    public  StringBuilder printTheStateTax(StringBuilder output, double totSalesTx) {
        output.append("Sales Tax").append('\t').append(totSalesTx);
        return output;
    }
    public  StringBuilder printTotalAmount(StringBuilder output, double totalAmount) {
        output.append("Total Amount").append('\t').append(totalAmount);
        return output;
    }

}