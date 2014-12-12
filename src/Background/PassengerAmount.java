package Background;

public class PassengerAmount{
    private int full;
    private int half;
    private int free;

    public PassengerAmount(){
        this.full = 0;
        this.half = 0;
        this.free = 0;
    }

    public PassengerAmount(int full, int half, int free){
        this.full = full;
        this.half = half;
        this.free = free;
    }

    public PassengerAmount(int full){
        this.full = full;
        this.half = 0;
        this.free = 0;
    }

    public double payingTotal(){
        return full + (0.5 * half);
    }

    public int total(){
        return this.full + this.half + this.free;
    }

    public int sum(PassengerAmount passengerAmount){
        return (this.total() + passengerAmount.total());
    }

    public void increment(PassengerAmount passengerAmount){
       this.full += passengerAmount.full;
       this.half += passengerAmount.half;
       this.free += passengerAmount.free;
    }
}
