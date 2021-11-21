package app.clubs.dto;


import lombok.*;
import app.clubs.entity.Club;

import java.util.function.BiFunction;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateClubRequest {


    private String mainColor;

    /**
     * Character's name.
     */

    /**
     * @return updater for convenient updating entity object using dto object
     */
    public static BiFunction<Club, UpdateClubRequest, Club> dtoToEntityUpdater() {
        return (club, request) -> {
            club.setMainColor(request.getMainColor());
            return club;
        };
    }
}
