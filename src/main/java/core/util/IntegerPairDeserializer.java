/*
   Copyright 2018 Jean-Baptiste Louvet

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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
