//package sha2ya3n.the2gen3tel4man.recepie.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;
//import sha2ya3n.the2gen3tel4man.recepie.services.RecipieService;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class RecipieControllerTest {
//
//    RecipieController controller;
//
//    @Mock
//    RecipieService recipieService;
//
//
//    @Before
//    public void setUp() throws Exception{
//        controller = new RecipieController(recipieService);
//        MockitoAnnotations.initMocks(this);
//
//    }
//
//    @Test
//    public void showMockMvcByIdTest() throws Exception {
//        Recipie recipie = new Recipie();
//        recipie.setId(1L);
//
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//
//        when(recipieService.findById(anyLong())).thenReturn(recipie);
//
//        mockMvc.perform(get("/recipie/show/1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("recipie/show"))
//                .andExpect(model().attributeExists("recipe"));
//
//    }
//
//}