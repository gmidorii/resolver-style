import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class SampleResolverTest {

    @RunWith(Theories.class)
    public static class ResolverLocal {

        private static final String FORMAT = "yyyy-MM-dd HH:mm";

        private static final DateTimeFormatter actualFormatter = DateTimeFormatter.ofPattern(FORMAT);

        @DataPoints
        public static Fixture[] fixtures = {
                // 一般
                new Fixture("2018-01-01 10:00", "2018-01-01 10:00"),
                // 月が13
                new Fixture("2018-13-01 00:00",  "2019-01-01 00:00"),
                // 日が32
                new Fixture("2018-01-32 00:00",  "2018-02-01 00:00"),
                // うるう年
                new Fixture("2020-02-29 00:00",  "2020-02-29 00:00"),
                // うるう年 + 1日
                new Fixture("2020-02-30 00:00",  "2020-03-01 00:00"),
                // 時刻が24
                new Fixture("2018-01-01 24:00",  "2018-01-02 00:00"),
                // 時刻が48
                new Fixture("2018-01-01 48:00",  "2018-01-03 00:00"),
                // 分が70
                new Fixture("2018-01-01 00:70",  "2018-01-01 01:10"),
        };

        @Theory
        public void formatLocal_LENIENT_正常系(Fixture f) throws Exception {
            SampleResolver r = new SampleResolver(FORMAT, ResolverStyle.LENIENT);

            LocalDateTime actual = r.formatLocal(f.input);
            assertThat(f.toString(), actual.format(actualFormatter), is(f.expected));
        }

    }

    static class Fixture {
        String input;
        String expected;

        public Fixture(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }

        public String getInput() {
            return input;
        }

        public String getExpected() {
            return expected;
        }

        @Override
        public String toString() {
            return "Fixture{" +
                    "input='" + input + '\'' +
                    ", expected='" + expected + '\'' +
                    '}';
        }
    }
}