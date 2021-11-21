package app.clubs.event.repository;

import app.clubs.entity.Club;
import app.clubs.event.dto.CreateClubRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ClubEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public ClubEventRepository(@Value("${lab.players.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(String name) {
        restTemplate.delete("/clubs/{name}", name);
    }

    public void create(Club club) {
        restTemplate.postForLocation("/clubs", CreateClubRequest.entityToDtoMapper().apply(club));
    }
}
