package app.club.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetClubsResponse {


    /**
     * Represents single character in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Club {

        private String name;

    }

    /**
     * Name of the selected characters.
     */
    @Singular
    private List<Club> clubs;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<app.club.entity.Club>, GetClubsResponse> entityToDtoMapper() {
        return clubs -> {
            GetClubsResponseBuilder response = app.club.dto.GetClubsResponse.builder();
            clubs.stream()
                    .map(club -> Club.builder()
                            .name(club.getName())
                            .build())
                    .forEach(response::club);
            return response.build();
        };
    }
}



