package checkout;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckoutInformation {
    private String firstName;
    private String lastName;
    private String zipPostalCode;
}
