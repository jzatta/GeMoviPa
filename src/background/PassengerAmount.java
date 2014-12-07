public class PassengerAmount{
    private int full;
    private int half;
    private int free;

    public PassengerAmount(int full){
        this.full = full;
    }
    public double payingTotal(){
        return full + (0.5 * half);
    }
}
