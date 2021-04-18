/**
 * @author Raquel Ferreira
 * @author Sophie Pousinho
 **/

public interface Aviao {
    boolean checkReservation(String[] reservations);

    int getAvailableTuristicSeats();
    int getAvailableExecutiveSeats();
    int getTuristicSeats();
    int getTuristicRows();

}
