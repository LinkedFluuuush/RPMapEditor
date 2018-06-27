package core.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerPairDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String s, DeserializationContext deserializationContext) {
        Pattern pattern = Pattern.compile("(?<posI>-?[0-9]+)-(?<posJ>-?[0-9]+)");
        Matcher matcher = pattern.matcher(s);

        matcher.find();
        int i = Integer.parseInt(matcher.group("posI"));
        int j = Integer.parseInt(matcher.group("posJ"));

        return new Pair<>(i, j);
    }

}
