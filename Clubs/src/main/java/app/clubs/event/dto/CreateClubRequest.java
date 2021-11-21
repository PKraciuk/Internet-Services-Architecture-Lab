package app.clubs.event.dto;

import lombok.*;
import app.clubs.entity.Club;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateClubRequest {

    /**
     * User's login.
     */
    private String name;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<Club, CreateClubRequest> entityToDtoMapper() {
        return entity -> CreateClubRequest.builder()
                .name(entity.getName())
                .build();
    }

}
