package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

   /* @OneToOne
    private Ingredient ingredient;*/      // To chyba nie potrzebne

    /* public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    } */
}
