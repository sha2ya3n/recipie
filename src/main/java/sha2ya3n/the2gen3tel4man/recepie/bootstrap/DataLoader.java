package sha2ya3n.the2gen3tel4man.recepie.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.model.*;
import sha2ya3n.the2gen3tel4man.recepie.repository.CategoryRepository;
import sha2ya3n.the2gen3tel4man.recepie.repository.RecipieRepository;
import sha2ya3n.the2gen3tel4man.recepie.repository.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Profile("default")
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipieRepository recipieRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipieRepository recipieRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipieRepository = recipieRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipieRepository.saveAll(getRecipie());
        log.info("Bootstrap or data loader from h2 database is now loaded");
    }

    private List<Recipie> getRecipie() {

        List<Recipie> recipieList = new ArrayList<>();

        Optional<UnitOfMeasure> eachUnitOfMeasure = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUnitOfMeasure.isPresent()) {
            throw new RuntimeException("UOM Can not be found in UnitOfMeasure Repository");
        }

        Optional<UnitOfMeasure> tablespoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tablespoonUnitOfMeasure.isPresent()) {
            throw new RuntimeException("UOM Can not be found in UnitOfMeasure Repository");
        }

        Optional<UnitOfMeasure> cupUnitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUnitOfMeasure.isPresent()) {
            throw new RuntimeException("UOM Can not be found in UnitOfMeasure Repository");
        }

        Optional<UnitOfMeasure> pinchUnitOfMeasure = unitOfMeasureRepository.findByDescription("Pinch");
        if (!pinchUnitOfMeasure.isPresent()) {
            throw new RuntimeException("UOM Can not be found in UnitOfMeasure Repository");
        }

        Optional<UnitOfMeasure> dashUnitOfMeasure = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUnitOfMeasure.isPresent()) {
            throw new RuntimeException("UOM Can not be found in UnitOfMeasure Repository");
        }

        Optional<UnitOfMeasure> teaspoonUunitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaspoonUunitOfMeasure.isPresent()) {
            throw new RuntimeException("UOM Can not be found in UnitOfMeasure Repository");
        }

        Optional<UnitOfMeasure> newUnitOfMeasure = unitOfMeasureRepository.findByDescription("New");
        if(!newUnitOfMeasure.isPresent()){
            throw new RuntimeException("UOM can not be found in UnitOfMeasure Repository");
        }

        // get optional from DB
        UnitOfMeasure eachUOM = eachUnitOfMeasure.get();
        UnitOfMeasure tablespoonUOM = tablespoonUnitOfMeasure.get();
        UnitOfMeasure cupUOM = cupUnitOfMeasure.get();
        UnitOfMeasure pinchUOM = pinchUnitOfMeasure.get();
        UnitOfMeasure dashUOM = dashUnitOfMeasure.get();
        UnitOfMeasure teaspoonUOM = teaspoonUunitOfMeasure.get();

        // this is another way od implementation of fetch data from DB by .get() method.
//        if(!newUnitOfMeasure.isPresent()){
//            UnitOfMeasure newUOM = unitOfMeasureRepository.findByDescription("New").get();
//        }else{
//            throw new RuntimeException("UOM can not be found on repository");
//        }

        Optional<Category> americanCategoty = categoryRepository.findByDescription("American");
        if (!americanCategoty.isPresent()) {
            throw new RuntimeException("Category Is not in category repository");
        }

        Optional<Category> mexicanCategory = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategory.isPresent()) {
            throw new RuntimeException("Category Is not in category repository");
        }

        // get category from DB
        Category americanCateg = americanCategoty.get();
        Category mexicanCateg = mexicanCategory.get();
        americanCateg.setDescription("This is american style of food");
        mexicanCateg.setDescription("This is mexican style of our food");

        // set all the information of recipe
        Recipie guacamole = new Recipie();
        guacamole.setDescription("Guacamole Deviled Eggs Recipe");
        guacamole.setPrepTime(25);
        guacamole.setCookTime(30);
        guacamole.getCategory().add(americanCateg);
        guacamole.getCategory().add(mexicanCateg);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setRating("12");
        guacamole.setSource("simplyrecipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/guacamole_deviled_eggs/");
        guacamole.setDirection("1 Hard boil the eggs: The easiest way to make hard boiled eggs that are easy to peel for deviled eggs is to steam them. Fill a saucepan with 1 inch of water and insert a steamer basket. (If you don't have a steamer basket, that's ok.)\n" +
                "\n" +
                "Bring the water to a boil, gently place 6 eggs in the steamer basket or directly in the saucepan. Cover the pot. Set your timer for 15 minutes. Remove eggs and set in icy cold water to cool. For more advice, see our How to Steam Hard Boiled Eggs. (You can also pressure cook them.)\n" +
                "\n" +
                "2 Prep the eggs: Once they've cooled, carefully peel the hard boiled eggs and cut them in half lengthwise. Place them on a serving platter. Scoop out the cooked yolks and set aside.\n" +
                "\n" +
                "3 Make the filling: Cut the avocados in half. Remove the pit. Scoop out the avocado flesh and place in a bowl. (See How to Cut and Peel an Avocado.)\n" +
                "\n" +
                "Roughly mash with a fork. Use your fingers to break up one or two of the cooked egg yolks (2 to 4 halves) over the mashed avocado. (Reserve the remaining egg yolks for another use.) Sprinkle with lime juice and salt, and stir in the sour cream. Stir in the chopped cilantro, serrano or jalapeño chile pepper, and chives.\n" +
                "\n" +
                "4 Fill the egg halves: Scoop a generous spoonful of the avocado mixture into each well of the hard boiled egg whites. Top with a small sprig of fresh cilantro or some chopped chives.\n");

        // set All ingredient of guacamole
        guacamole.getIngredients().add(new Ingredient("Large eggs", new BigDecimal(6), eachUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Ripe of Avokado", new BigDecimal(2), eachUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Lemon juice", new BigDecimal(1), tablespoonUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Sour Cream", new BigDecimal(1.2), teaspoonUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(1.2), eachUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Green onion", new BigDecimal(1), tablespoonUOM, guacamole));

        // set note of Recipe guacamole
        Note guacamoleNote = new Note();
        guacamoleNote.setRecipie(guacamole);
        guacamoleNote.setRecipeNotes("While you can easily hard cook the eggs several days ahead, the make-ahead issue is with the guacamole. Avocados quickly brown when exposed to air.\n" +
                "\n" +
                "If you need to make ahead, make the filling (you’ll need to hard cook and cut the eggs to get the egg yolks for the filling), and store the filling in the refrigerator in a freezer bag with all air squeezed out. The filling will last a day or two without browning, stored this way.\n" +
                "\n" +
                "When getting ready to serve, cut out a corner of the freezer bag and pipe the filling into the egg white halves.\n" +
                "\n" +
                "If you’ve already made your guacamole deviled eggs and need to store them for more than an hour, cover with plastic wrap and make sure that all surfaces of the filling are touching the plastic wrap and not exposed to air.");
        guacamole.setNote(guacamoleNote);

        recipieList.add(guacamole);



        // get Category from DB
        Optional<Category> italian = categoryRepository.findByDescription("Italian");
        if(!italian.isPresent()){
            throw new RuntimeException("Expected Category is not found");
        }

        Optional<Category> fastFood = categoryRepository.findByDescription("Fast Food");
        if(!fastFood.isPresent()){
            throw new RuntimeException("Expected Category is not fount");
        }

        Category italianCategory = italian.get();
        Category fastFoodCategory = fastFood.get();
        italianCategory.setDescription("This is Italian style of the food");
        fastFoodCategory.setDescription("This is fast food style of the food");

        // set Recipie of Spicy Grilled Chicken Tacos
        Recipie chicken = new Recipie();
        chicken.setDescription("Spicy Grilled Chicken Tacos");
        chicken.setCookTime(15);
        chicken.setPrepTime(20);
        chicken.setDifficulty(Difficulty.MODERATE);
        chicken.setSource("simplyrecipes");
        chicken.setRating("1");
        chicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chicken.getCategory().add(italianCategory);
        chicken.getCategory().add(fastFoodCategory);
        chicken.setDirection("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        // set Note of chicken food:
        Note chickenNote = new Note();
        chickenNote.setRecipie(chicken);
        chickenNote.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");
        chicken.setNote(chickenNote);

        // set Ingredient of Spicy chicken
        chicken.getIngredients().add(new Ingredient("Ancho Chili Power", new BigDecimal(2), tablespoonUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), tablespoonUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Dried sumin", new BigDecimal(1),teaspoonUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1.2), teaspoonUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Salt", new BigDecimal(1.2), teaspoonUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Garlik", new BigDecimal(1), eachUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Orange", new BigDecimal(1), tablespoonUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Squeezed Orange Juice", new BigDecimal(3), tablespoonUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Chicken thighs", new BigDecimal(6), eachUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Tortillas", new BigDecimal(8), eachUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Baby Arugula", new BigDecimal(3), cupUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Avokado", new BigDecimal(2), eachUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Radishes", new BigDecimal(4), eachUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Cherry Tomato", new BigDecimal(1.2), eachUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Red Onion", new BigDecimal(1.4), eachUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Sour Cream", new BigDecimal(1.2), cupUOM, chicken));
        chicken.getIngredients().add(new Ingredient("Lime", new BigDecimal(1), eachUOM, chicken));

        recipieList.add(chicken);
        return recipieList;











    }
}
