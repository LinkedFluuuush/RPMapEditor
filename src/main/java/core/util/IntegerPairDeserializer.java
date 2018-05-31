package core.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class IntegerPairDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String s, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String[] values = s.split("-");

        return new Pair<>(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

}
