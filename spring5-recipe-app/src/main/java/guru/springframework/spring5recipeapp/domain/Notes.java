package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    private Recipe recipe;

    @Lob  // To zwiększa nam zasięg Stringa, bo Spring nie osiąga takiej długości Stringa co String javy
    private String recipeNotes;

    public Notes() {
    }




}
