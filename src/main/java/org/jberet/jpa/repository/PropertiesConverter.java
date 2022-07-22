/*
 * Copyright (c) 2022 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.jberet.jpa.repository;

import java.util.Properties;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.jberet.util.BatchUtil;

/**
 *
 * @author a.moscatelli
 */
@Converter
public class PropertiesConverter implements AttributeConverter<Properties, String> {

    @Override
    public String convertToDatabaseColumn(Properties x) {
        return BatchUtil.propertiesToString(x);
    }

    @Override
    public Properties convertToEntityAttribute(String y) {
        return BatchUtil.stringToProperties(y);
    }
    
}
