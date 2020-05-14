import java.util.Enumeration;
import java.util.Vector;

class Customer {
    private final String name;
    private final Vector rentals = new Vector();

    public Customer(String newname) {
        name = newname;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalCharge = 0;
        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

        while (enum_rentals.hasMoreElements()) {
            Rental each = (Rental) enum_rentals.nextElement();
            // add frequent renter points
            frequentRenterPoints += each.getFrequentRenterPoints();
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + "\t" + each.getDaysRented() + "\t" + each.getCharge() + "\n";
            totalCharge += each.getCharge();
        }
        //add footer lines
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        Enumeration enum_rentals = rentals.elements();
        String result = "<H1>Rentals for <EM>" + getName() + "</EM></ H1><P>\n";
        while (enum_rentals.hasMoreElements()) {
            Rental each = (Rental) enum_rentals.nextElement();
            //show figures for each rental
            result += each.getMovie().getTitle()+ ": " + each.getCharge() + "<BR>\n";
        }
        //add footer lines
        result += "<P>You owe <EM>" + getTotalCharge() + "</EM><P>\n";
        result += "On this rental you earned <EM>" + getTotalFrequentRenterPoints() + "</EM> frequent renter points<P>";
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int totalFrequentRenterPoints = 0;
        for (Object o : rentals) {
            Rental rental = (Rental) o;
            totalFrequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return totalFrequentRenterPoints;
    }

    private double getTotalCharge() {
        double totalCharge = 0;
        for (Object o : rentals) {
            Rental rental = (Rental) o;
            totalCharge += rental.getCharge();
        }
        return totalCharge;
    }

}
    