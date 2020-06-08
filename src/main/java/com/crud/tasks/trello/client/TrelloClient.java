package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = getTrelloBoardsUri();

        Optional<TrelloBoardDto[]> boardsResponse =
                Optional.ofNullable(restTemplate.getForObject(url, TrelloBoardDto[].class));

        return boardsResponse.map(Arrays::asList).orElseGet(ArrayList::new);

        /*
        if (boardsResponse.isPresent()) {
            return Arrays.asList(boardsResponse.get());
        } else {
            return new ArrayList<>();
        }
         */
    }

    private URI getTrelloBoardsUri() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint
                + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .build().encode().toUri();
    }

}
