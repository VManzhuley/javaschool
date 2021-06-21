package unit;

import com.tsystems.javaschool.dto.ColourDTO;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Photo;
import com.tsystems.javaschool.service.impl.ColourServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ColourServiceTest {
    private final Colour colourRed = new Colour();
    private final Colour colourBlue = new Colour();
    private ColourDTO colourDTO = new ColourDTO();

    @InjectMocks
    private ColourServiceImpl colourService;

    @Before
    public void setUp() {
        colourRed.setId(12);
        colourRed.setName("Red");
        colourBlue.setId(4);
        colourBlue.setName("Blue");
    }

    @Test
    public void mapPhotoWithTwoColour() {
        Photo photo = new Photo(1, null, colourRed, colourBlue, "");
        colourDTO = colourService.mapToColourDTO(photo);

        assertEquals("Red/Blue", colourDTO.getName());
        assertEquals("1204", colourDTO.getArticle());
    }

    @Test
    public void mapPhotoWithOneColour(){
        Photo photo = new Photo(2, null, colourRed, null, "");
        colourDTO = colourService.mapToColourDTO(photo);

        assertEquals("Red", colourDTO.getName());
        assertEquals("0012", colourDTO.getArticle());
    }
}
