package util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonUtil {
    public static String mapToJson(Map<String, Object> map) {
        String res = "{";

        Set<String> set = map.keySet();
        int len = set.size();
        int p = 0;
        for (String str : set) {
            res += "\"" + str + "\": ";
            res += valueToJson(map.get(str));
            p++;
            if (p != len) {
                res += ", ";
            }
        }

        res += "}";
        return res;
    }

    private static String listToJson(List<Object> list) {
        String res = "[";
        int len = list.size();
        int p = 0;
        for (Object value : list) {
            res += valueToJson(value);
            p++;
            if (p != len) {
                res += ", ";
            }
        }
        res += "]";
        return res;
    }

    private static String valueToJson(Object value) {
        String res = "";
        if (value == null) {
            res += "null";
        } else {
            String type = value.getClass().getSimpleName();
            if (type.endsWith("Map")) {
                res += mapToJson((Map<String, Object>) value);
            } else if (type.endsWith("List")) {
                res += listToJson((List<Object>) value);
            } else if (type.equals("String")) {
                res += "\"" + ((String) value).replaceAll("\n", "\\\\n") + "\"";
            } else {
                res += value;
            }
        }
        return res;
    }
}
