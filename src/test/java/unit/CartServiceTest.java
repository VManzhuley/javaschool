package unit;

import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.CartItemDTO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.service.impl.CartServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CartServiceTest {
    private final ProductDTO productDTO1 = new ProductDTO(1, "KIT CAMPO", "KIT CAMPO, Sports kit, ROYAL/BLUE, M", "KITC53 0204 M", null, null, 1840, 10, false);
    private final ProductDTO productDTO2 = new ProductDTO(2, "KIT CAMPO", "KIT CAMPO, Sports kit, ROYAL/BLUE, L", "KITC53 0204 L", null, null, 1840, 10, false);
    private final CartItemDTO cartItemDTO = new CartItemDTO(productDTO1, 1, 0);
    private final CartDTO cartDTO = new CartDTO(null, new ArrayList(Arrays.asList(cartItemDTO)), false);
    private final Product product = new Product();
    @Mock
    private ProductDAO productDAO;
    @InjectMocks
    private CartServiceImpl cartService;

    @Before
    public void setUp() {
        when(productDAO.getById(1)).thenReturn(product);
    }

    @Test
    public void checkAvailability() {
        cartService.checkAvailability(cartDTO);

        assertEquals(1, cartItemDTO.getMissQuantity());
        assertTrue(cartDTO.getIsMissQuantity());
    }

    @Test
    public void removeProductFromCart() {
        cartService.removeCartItem(cartDTO, 1);

        assertTrue(cartDTO.getCartItems().isEmpty());
    }

    @Test
    public void removeNotExistingProductFromCart() {
        cartService.removeCartItem(cartDTO, 2);

        assertEquals(1, cartDTO.getCartItems().size());
    }

    @Test
    public void addNewProductToCart() {
        cartService.addCartItem(cartDTO, productDTO2, 10);

        assertEquals(2, cartDTO.getCartItems().size());
    }

    @Test
    public void addExistingProductToCart() {
        cartService.addCartItem(cartDTO, productDTO1, 10);

        assertEquals(1, cartDTO.getCartItems().size());
        assertEquals(11, cartDTO.getCartItems().get(0).getQuantity());
    }

}
