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
public class CreatePlayerRequest {

    private String name;

    private String position;

    private int age;

    private int height;

    private int value;

    private String club;

    public static Function<CreatePlayerRequest, Player> dtoToEntityMapper(
            Function<String, Club> clubFunction) {
        return request -> Player.builder()
                .name(request.getName())
                .position(request.getPosition())
                .age(request.getAge())
                .height(request.getHeight())
                .value(request.getValue())
                .club(clubFunction.apply(request.getClub()))
                .build();

    }


}
