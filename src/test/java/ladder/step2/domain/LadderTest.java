package ladder.step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LadderTest {

  @DisplayName("사다리의 높이에 대한 테스트")
  @ParameterizedTest
  @MethodSource("provideLadderAndHeight")
  void 사다리_높이_테스트 (Ladder ladder, long expected) {
    assertEquals(expected, ladder.stream().count());
  }

  private static Stream<Arguments> provideLadderAndHeight () {
    return Stream.of(
      Arguments.of(Ladder.of(5, 5, prev -> true), 5),
      Arguments.of(Ladder.of(5, 4, prev -> true), 4),
      Arguments.of(Ladder.of(5, 3, prev -> true), 3),
      Arguments.of(Ladder.of(5, 2, prev -> true), 2),
      Arguments.of(Ladder.of(5, 1, prev -> true), 1)
    );
  }

  @DisplayName("사다리가 규칙에 맞게 잘 정상적으로 잘 생성 되는지 테스트")
  @ParameterizedTest
  @MethodSource("provideLadder")
  void 사다리_생성_테스트 (Ladder ladder, List<List<Boolean>> shape) {
    assertThat(
      ladder.stream()
        .map(ladderLine -> ladderLine.stream()
          .collect(toList()))
        .collect(toList())).isEqualTo(shape);
  }

  private static Stream<Arguments> provideLadder () {
    return Stream.of(
      Arguments.of(
        Ladder.of(4, 3, prev -> !prev),
        Arrays.asList(
          Arrays.asList(true, false, true),
          Arrays.asList(true, false, true),
          Arrays.asList(true, false, true)
        )
      ),
      Arguments.of(
        Ladder.of(4, 3, prev -> true),
        Arrays.asList(
          Arrays.asList(true, true, true),
          Arrays.asList(true, true, true),
          Arrays.asList(true, true, true)
        )
      ),
      Arguments.of(
        Ladder.of(4, 3, prev -> false),
        Arrays.asList(
          Arrays.asList(false, false, false),
          Arrays.asList(false, false, false),
          Arrays.asList(false, false, false)
        )
      )
    );
  }
}