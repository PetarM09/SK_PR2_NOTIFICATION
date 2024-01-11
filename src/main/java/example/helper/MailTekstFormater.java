package example.helper;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MailTekstFormater {

    private List<String> dohvatiPostavljanjaRedosled(String message) {
        return IntStream.iterate(1, n -> StringUtils.ordinalIndexOf(message, "%", n) + 1)
                .limit(StringUtils.countMatches(message, "%") / 2)
                .mapToObj(n -> message.substring(n, StringUtils.ordinalIndexOf(message, "%", n + 1)))
                .collect(Collectors.toList());
    }

    private List<String> dohvatiParametreRedosled(String message, Object data) throws IllegalAccessException {
        List<String> postavljanjaList = dohvatiPostavljanjaRedosled(message);
        return postavljanjaList.stream()
                .map(postavljanje -> {
                    try {
                        return data.getClass().getDeclaredField(postavljanje).get(data).toString();
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        return "-1";
                    }
                })
                .collect(Collectors.toList());
    }

    private String zameniPostavljanjeParametrom(String message, String postavljanje, String parametar) {
        int pocetakIdx = StringUtils.ordinalIndexOf(message, "%", 1);
        int krajIdx = 1 + StringUtils.ordinalIndexOf(message, "%", 2);
        return message.substring(0, pocetakIdx) + parametar + message.substring(krajIdx);
    }

    public String formatirajTekst(String message, Object data) throws IllegalAccessException {
        List<String> parametriList = dohvatiParametreRedosled(message, data);
        return IntStream.range(0, parametriList.size())
                .boxed()
                .reduce(message, (msg, i) -> zameniPostavljanjeParametrom(msg, dohvatiPostavljanjaRedosled(message).get(i), parametriList.get(i)), (a, b) -> a);
    }
}
