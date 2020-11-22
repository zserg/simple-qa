package ru.zserg.simpleqa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zserg.simpleqa.model.Card;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class CardService {
    private static final String filesDir = "content";
    private List<Card> cards = new ArrayList<>();
    private ObjectMapper om = new ObjectMapper();

    private final String Q_PATTERN = "^#Q$";
    private final String A_PATTERN = "^#A$";

    @PostConstruct
    private void init() {

        try (Stream<Path> walk = Files.walk(Paths.get(filesDir))) {

            walk.peek(f -> log.info("file: {}", f))
                    .map(Path::toString)
                    .filter(f -> f.endsWith(".txt"))
                    .forEach(this::processFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("cards: {}", cards);
    }

    private void processFile(String filePath) {
        try {
            Stream<String> str = Files.lines(Paths.get(filePath));
            List<String> stringList = str.collect(Collectors.toList());
            List<Card> cardList = parseData(stringList);
            cards.addAll(cardList);
        } catch (IOException e) {
            log.error("error processing file: {}", filePath, e);
        }
    }

    public Card getRandomCard() {
        Random random = new Random();
        int i = random.nextInt(cards.size());
        Card card = cards.get(i);
        card.setId(i);
        return card;
    }

    public Card getCard(int id) {
        Card card = cards.get(id);
        card.setId(id);
        return card;
    }

    public List<Card> parseData(List<String> lines) {
        List<Card> cardList = new ArrayList<>();
        Card card = new Card();
        List<String> front = new ArrayList<>();
        List<String> back = new ArrayList<>();
        State state = State.IDLE;

        for (String line : lines) {
            if (line.matches(Q_PATTERN)) {
                if(state.equals(State.BACK)){
                    card.setBack(String.join("", back));
                    cardList.add(card);
                    card = new Card();
                }

                state = State.FRONT;
                front = new ArrayList<>();
                continue;
            } else if (line.matches(A_PATTERN)) {
                card.setFront(String.join("", front));
                state = State.BACK;
                back = new ArrayList<>();
                continue;
            }

            if(state.equals(State.FRONT)){
                front.add(line);
            }
            if(state.equals(State.BACK)){
                back.add(line);
            }
        }

        card.setBack(String.join("<p>", back));
        cardList.add(card);

        return cardList;

    }

    enum State {
        IDLE,
        FRONT,
        BACK
    }

}
