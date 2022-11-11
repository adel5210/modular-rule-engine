import com.adel.modularruleengine.model.Customer;
import com.adel.modularruleengine.model.Invoice;
import org.evrete.dsl.annotation.Rule;
import org.evrete.dsl.annotation.Where;

import java.math.BigDecimal;

public class rule1 {

//    @Rule
//    public void rule1a(Customer $c){
//        $c.setWalletAmount(BigDecimal.ZERO);
//    }

    @Rule
    @Where("$i.customer.name == $c.name")
    public void rule1b(Customer $c, Invoice $i){
        $c.addAmount($i.getAmount());
        $i.setProcessed(true);
    }

}