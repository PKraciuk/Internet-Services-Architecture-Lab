package app.club.dto;

import lombok.*;
import app.club.entity.Club;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetClubResponse {


    private String name;

    private int CreationYear;

    private String MainColor;

    private Double Budget;

    public static Function<Club, GetClubResponse> entityToDtoMapper() {
        return club -> app.club.dto.GetClubResponse.builder()
                .name(club.getName())
                .CreationYear(club.getCreationYear())
                .MainColor(club.getMainColor())
                .Budget(club.getBudget())
                .build();
    }

}
