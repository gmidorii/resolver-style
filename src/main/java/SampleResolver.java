import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class SampleResolver {
    private DateTimeFormatter formatter;

    public SampleResolver(String format, ResolverStyle style) {
        this.formatter = DateTimeFormatter
                .ofPattern(format)
                .withResolverStyle(style);
    }

    public LocalDateTime formatLocal(String s) {
        return LocalDateTime.parse(s, formatter);
    }

    public ZonedDateTime formatZoned(String s) {
        return ZonedDateTime.parse(s, formatter);
    }
}
