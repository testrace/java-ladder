package ladder.ui;

import ladder.domain.ladder.Awards;
import ladder.domain.ladder.Ladder;
import ladder.domain.ladder.LadderResult;
import ladder.domain.line.Line;
import ladder.utils.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static final String LADDER_GRAPH = "\n사다리 결과\n";
    private static final String VERTICAL_DELIMITER = "|";
    private static final String VERTICAL_DELIMITER_PREFIX = "  |";
    private static final String LINE_DASH = "-----";
    private static final String LINE_EMPTY = "     ";
    private static final String LADDER_RESULT = "\n실행결과";

    public static void printLadder(Ladder ladder) {
        printPlayers(ladder);

        ladder.getLines().stream()
                .map(ResultView::lineToDash)
                .forEach(System.out::println);
    }

    private static String lineToDash(Line line) {
        return line.getPoints().stream()
                .map(point -> point ? LINE_DASH : LINE_EMPTY)
                .collect(Collectors.joining(VERTICAL_DELIMITER, VERTICAL_DELIMITER_PREFIX, VERTICAL_DELIMITER));
    }

    private static void printPlayers(Ladder ladder) {
        System.out.println(LADDER_GRAPH);

        System.out.println(names(ladder.getPlayers()));
    }

    public static void printAwards(Awards awards) {
        System.out.println(names(awards.getAwards()));
    }

    private static String names(List<?> list) {
        return list.stream()
                .map(Object::toString)
                .map(name -> StringUtil.rightPad(name, 6))
                .collect(Collectors.joining());
    }

    public static void printWantedPlayer(LadderResult ladderResult, String wantedPlayer) {
        System.out.println(LADDER_RESULT);
        System.out.println(ladderResult.resultOfPlayer(wantedPlayer));
    }
}
