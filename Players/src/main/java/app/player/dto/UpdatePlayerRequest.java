package app.player.dto;


import lombok.*;
import app.player.entity.Player;

import java.util.function.BiFunction;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdatePlayerRequest {

    /**
     * Character's name.
     */
    private String name;

    /**
     * Character's background story.
     */
    private int value;

    /**
     * Character's name.
     */
    private int age;

    /**
     * @return updater for convenient updating entity object using dto object
     */
    public static BiFunction<Player, UpdatePlayerRequest, Player> dtoToEntityUpdater() {
        return (player, request) -> {
            player.setName(request.getName());
            player.setAge(request.getAge());
            player.setValue(request.getValue());
            return player;
        };
    }
}
