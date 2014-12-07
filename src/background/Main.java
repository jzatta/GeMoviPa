public class Main{
    public static void main(String[] args){
        Enterprise enterprise = new Enterprise("Barba Negra",0);
        Boat boat = new Boat("Barba Negra I",0,140);
        enterprise.addBoat(boat);
    }
}
