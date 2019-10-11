package sha2ya3n.the2gen3tel4man.recepie.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Recipie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;
    private String source;
    private String rating;

    @Lob
    private String direction;
    private String url;
    private Integer cookTime;
    private Integer prepTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipie")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name = "recipie_category",
            joinColumns = @JoinColumn(name = "recepie_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> category =  new HashSet<>();

    public void setNote(Note note) {
        this.note = note;
//        note.setRecipie(this);
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getRating() {
//        return rating;
//    }
//
//    public void setRating(String rating) {
//        this.rating = rating;
//    }
//
//    public String getDirection() {
//        return direction;
//    }
//
//    public void setDirection(String direction) {
//        this.direction = direction;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public Byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(Byte[] image) {
//        this.image = image;
//    }
//
//    public Integer getCookTime() {
//        return cookTime;
//    }
//
//    public void setCookTime(Integer cookTime) {
//        this.cookTime = cookTime;
//    }
//
//    public Integer getPrepTime() {
//        return prepTime;
//    }
//
//    public void setPrepTime(Integer prepTime) {
//        this.prepTime = prepTime;
//    }
//
//    public Note getNote() {
//        return note;
//    }

//    public Set<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(Set<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public Difficulty getDifficulty() {
//        return difficulty;
//    }
//
//    public void setDifficulty(Difficulty difficulty) {
//        this.difficulty = difficulty;
//    }
//
//    public Set<Category> getCategory() {
//        return category;
//    }
//
//    public void setCategory(Set<Category> category) {
//        this.category = category;
//    }

    public Recipie addIngredient(Ingredient ingredient){
        ingredient.setRecipie(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
