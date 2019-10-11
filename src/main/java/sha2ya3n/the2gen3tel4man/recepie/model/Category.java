package sha2ya3n.the2gen3tel4man.recepie.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "category")
    private Set<Recipie> recipies = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Recipie> getRecipies() {
        return recipies;
    }

    public void setRecipies(Set<Recipie> recipies) {
        this.recipies = recipies;
    }

}
