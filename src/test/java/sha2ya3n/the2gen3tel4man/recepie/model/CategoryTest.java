package sha2ya3n.the2gen3tel4man.recepie.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long value = 4L;
        category.setId(value);
        assertEquals(value, category.getId());

    }

    @Test
    public void getDescription() {
        String description = "This is for test purpose";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

}