package ladder.domain.line;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lines {

    public static final int MIN_SIZE = 1;
    private final List<Line> lines;

    private Lines(List<Line> lines) {
        this.lines = lines;
    }

    public static Lines of(List<Line> lines) {
        return new Lines(lines);
    }

    public static Lines of(int height, int playersCount) {
        validateHeight(height);
        List<Line> lines = IntStream.range(0, height)
                .mapToObj(i -> Line.of(playersCount))
                .collect(Collectors.toList());
        return new Lines(lines);
    }

    private static void validateHeight(int height) {
        if (height < MIN_SIZE) {
            throw new LinesSizeException();
        }
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public int indexOfResult(int indexOfStart) {
        int index = indexOfStart;
        for (Line line : lines) {
            index = line.indexOfResult(index);
        }
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lines lines1 = (Lines) o;
        return Objects.equals(lines, lines1.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }

}
