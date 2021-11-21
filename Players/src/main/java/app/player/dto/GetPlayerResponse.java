package app.player.dto;

import lombok.*;
import app.player.entity.Player;
import app.club.entity.Club;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPlayerResponse {


    private Long id;

    private String name;

    private String position;

    private int age;

    private int height;

    private int value;

    private String club;

    public static Function<Player, GetPlayerResponse> entityToDtoMapper() {
        return player -> app.player.dto.GetPlayerResponse.builder()
                .id(player.getId())
                .name(player.getName())
                .position(player.getPosition())
                .age(player.getAge())
                .height(player.getHeight())
                .value(player.getValue())
                .club(player.getClub().getName())
                .build();
    }

}
