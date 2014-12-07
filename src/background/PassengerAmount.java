public class PassengerAmount{
    private int full;
    private int half;
    private int free;

    public double payingTotal(){
        return full + (0.5 * half);
    }
}
