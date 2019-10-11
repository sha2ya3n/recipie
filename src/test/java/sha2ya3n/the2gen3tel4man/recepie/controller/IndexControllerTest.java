//package sha2ya3n.the2gen3tel4man.recepie.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.ui.Model;
//import sha2ya3n.the2gen3tel4man.recepie.controller.IndexController;
//import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;
//import sha2ya3n.the2gen3tel4man.recepie.services.RecipieService;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//public class IndexControllerTest {
//
//    IndexController controller;
//
//    @Mock
//    Model model;
//
//    @Mock
//    RecipieService recipieService;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        controller = new IndexController(recipieService);
//    }
//
//    // this is second way of test for controllers instead of testing like showRecipies().
//    @Test
//    public void testMockMvc() throws Exception{
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//        mockMvc.perform(get("/")).andExpect(status().isOk())
//                .andExpect(view().name("index"));
//    }
//
//    @Test
//    public void showRecipies() throws Exception{
//        // given
//        Recipie recipie1 = new Recipie();
//        Recipie recipie2 = new Recipie();
//        Set<Recipie> recipieSet = new HashSet<>();
//        recipieSet.add(recipie1);
//        recipieSet.add(recipie2);
//
//        when(recipieService.getRecipies()).thenReturn(recipieSet);
//
//        // when
//        String sample = controller.showRecipies(model);
//        assertEquals("index", sample);
//
//        ArgumentCaptor<Set<Recipie>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
//
//        // then
//        verify(recipieService, times(1)).getRecipies();
//        verify(model, times(1)).addAttribute(eq("recipies"), argumentCaptor.capture());
//        Set<Recipie> recipies = argumentCaptor.getValue();
//        assertEquals(2, recipies.size());
//
//    }
//}