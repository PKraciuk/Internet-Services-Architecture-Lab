package app.clubs.dto;

import lombok.*;
import app.clubs.entity.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateClubRequest {

    private String name;

    private int CreationYear;

    private String MainColor;

    private Double Budget;




    public static Function<CreateClubRequest, Club> dtoToEntityMapper(
            ) {
        return request -> Club.builder()
                .name(request.getName())
                .CreationYear(request.getCreationYear())
                .MainColor(request.getMainColor())
                .Budget(request.getBudget())
                .build();
    }


}
