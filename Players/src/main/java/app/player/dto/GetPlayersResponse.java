package app.player.dto;

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
public class GetPlayersResponse {


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
    public static class Player {

        /**
         * Unique id identifying character.
         */
        private Long id;

        /**
         * Name of the character.
         */
        private String name;

    }

    /**
     * Name of the selected characters.
     */
    @Singular
    private List<Player> players;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<app.player.entity.Player>, GetPlayersResponse> entityToDtoMapper() {
        return players -> {
            GetPlayersResponseBuilder response = app.player.dto.GetPlayersResponse.builder();
            players.stream()
                    .map(player -> Player.builder()
                            .id(player.getId())
                            .name(player.getName())
                            .build())
                    .forEach(response::player);
            return response.build();
        };
    }
}



