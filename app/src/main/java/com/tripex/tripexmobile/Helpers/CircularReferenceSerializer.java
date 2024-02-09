package com.tripex.tripexmobile.Helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CircularReferenceSerializer {

    public static JSONObject serialize(JsonObject args) {
        JSONObject volleyJsonBody = new JSONObject();
        if (args != null) {
            JsonObject jsonObject = args.getAsJsonObject();

            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                JsonElement value = entry.getValue();
                try {
                    if (value.isJsonNull()) {
                        volleyJsonBody.put(entry.getKey(), JSONObject.NULL);
                    } else {
                        if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isBoolean()) {
                            volleyJsonBody.put(entry.getKey(), value.getAsBoolean());
                        } else if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isString()) {
                            volleyJsonBody.put(entry.getKey(), value.getAsString());
                        }
                        if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isNumber()) {
                            JsonPrimitive jsonPrimitive = value.getAsJsonPrimitive();
                            if (!jsonPrimitive.getAsString().contains(".")) {
                                volleyJsonBody.put(entry.getKey(), value.getAsInt());
                            } else {
                                volleyJsonBody.put(entry.getKey(), value.getAsDouble());
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return volleyJsonBody;
    }
}
