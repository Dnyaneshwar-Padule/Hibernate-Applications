package com.tca.util;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIDGenerator implements IdentifierGenerator {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
//        return "STU-" + UUID.randomUUID().toString().substring(0, 8);
        return "STU-" + LocalDate.now().format( DateTimeFormatter.ofPattern("yyyyMMdd") ) + UUID.randomUUID().toString().substring(0, 6);
    }
}
