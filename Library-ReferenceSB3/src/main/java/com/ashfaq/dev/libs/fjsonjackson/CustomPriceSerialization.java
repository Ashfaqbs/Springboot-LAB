package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

class CustomPriceSerializer extends StdSerializer<Double> {
    public CustomPriceSerializer() {
        this(null);
    }

    public CustomPriceSerializer(Class<Double> t) {
        super(t);
    }

    @Override
    public void serialize(Double value, com.fasterxml.jackson.core.JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString("$" + value); // Prefix value with "$"
    }
}

class ExampleProduct {
    public String name;

    @JsonSerialize(using = CustomPriceSerializer.class)
    public Double price;

    public ExampleProduct(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public ExampleProduct() {}
}

public class CustomPriceSerialization {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ExampleProduct product = new ExampleProduct("Phone", 699.99);

        String json = mapper.writeValueAsString(product);
        System.out.println("Serialized JSON: " + json);
        /*
        OP
        Serialized JSON: {"name":"Phone","price":"$699.99"}
         */
    }
}
