package core.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

public class IntegerPairDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String s, DeserializationContext deserializationContext) {
        String[] values = s.split("-");

        return new Pair<>(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

}
