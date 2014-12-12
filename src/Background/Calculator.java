package Background;

import java.util.List;
import java.util.ArrayList;

public interface Calculator{
    public void calculateApportionment(List<Enterprise> enterprises);
    public void calculateCommission(List<Seller> sellers);
}
