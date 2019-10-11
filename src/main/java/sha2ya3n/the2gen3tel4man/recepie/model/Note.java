package sha2ya3n.the2gen3tel4man.recepie.model;

import javax.persistence.*;

@Entity
public class Note {

    @OneToOne
    private Recipie recipie;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String recipeNotes;

    public Recipie getRecipie() {
        return recipie;
    }

    public void setRecipie(Recipie recipie) {
        this.recipie = recipie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
