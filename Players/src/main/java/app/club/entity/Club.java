package app.club.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import app.player.entity.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "clubs")

public class Club {

    @Id
    private String name;

    private int CreationYear;

    private String MainColor;

    private Double Budget;

    public Club RetClub(){
        return this;

    }

    public Double getBudget() {
        return Budget;
    }

    @OneToMany(mappedBy = "club", fetch =  FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Player> players;

}
