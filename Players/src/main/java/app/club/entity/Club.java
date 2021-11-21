package app.club.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

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
}
