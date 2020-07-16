package com.hannamsm.shop.domain.account.vo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class AccountSerializer extends JsonSerializer<Account>{

	@Override
	public void serialize(Account account, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("no", account.getAccountNo());
		gen.writeEndObject();
	}

}
