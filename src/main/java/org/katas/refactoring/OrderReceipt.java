package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        output.append("======Printing Orders======\n");

        output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());

        // prints lineItems
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : o.getLineItems()) {
            printLineItems(output,lineItem);

            // calculate sales tax @ rate of 10%
            totSalesTx += calculateSalesTax(lineItem);

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + calculateSalesTax(lineItem);
        }

        // prints the state tax
        printTheStateTax(output,totSalesTx);

        // print total amount
        printTotalAmount(output,tot);
        return output.toString();
    }

    public  StringBuilder printLineItems(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription()).append('\t').append(lineItem.getPrice()).append('\t').append(lineItem.getQuantity()).append('\t').append(lineItem.totalAmount()).append('\n');
        return output;
    }

    public double calculateSalesTax(LineItem lineItem){
        double salesTax = lineItem.totalAmount() * .10;
        return salesTax;
    }
    public  StringBuilder printTheStateTax(StringBuilder output, double totSalesTx) {
        output.append("Sales Tax").append('\t').append(totSalesTx);
        return output;
    }
    public  StringBuilder printTotalAmount(StringBuilder output, double tot) {
        output.append("Total Amount").append('\t').append(tot);
        return output;
    }

}