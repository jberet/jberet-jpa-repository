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

import com.google.common.base.Throwables;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import org.jberet.util.BatchUtil;

/**
 *
 * @author a.moscatelli
 */
public final class TableColumnsJpa {

    public static final String VERSION = "VERSION";
    public static final String JOB_INSTANCE = "JOB_INSTANCE";
    public static final String JOBINSTANCEID = "JOBINSTANCEID";
    public static final String JOBNAME = "JOBNAME";
    public static final String APPLICATIONNAME = "APPLICATIONNAME";
    public static final String JOB_EXECUTION = "JOB_EXECUTION";
    public static final String JOBEXECUTIONID = "JOBEXECUTIONID";
    public static final String CREATETIME = "CREATETIME";
    public static final String STARTTIME = "STARTTIME";
    public static final String ENDTIME = "ENDTIME";
    public static final String LASTUPDATEDTIME = "LASTUPDATEDTIME";
    public static final String BATCHSTATUS = "BATCHSTATUS";
    public static final String EXITSTATUS = "EXITSTATUS";
    public static final String JOBPARAMETERS = "JOBPARAMETERS";
    public static final String RESTARTPOSITION = "RESTARTPOSITION";
    public static final String STEPEXECUTIONID = "STEPEXECUTIONID";
    public static final String STEPNAME = "STEPNAME";
    public static final String EXECUTIONEXCEPTION = "EXECUTIONEXCEPTION";
    public static final String PERSISTENTUSERDATA = "PERSISTENTUSERDATA";
    public static final String READCOUNT = "READCOUNT";
    public static final String WRITECOUNT = "WRITECOUNT";
    public static final String COMMITCOUNT = "COMMITCOUNT";
    public static final String ROLLBACKCOUNT = "ROLLBACKCOUNT";
    public static final String READSKIPCOUNT = "READSKIPCOUNT";
    public static final String PROCESSSKIPCOUNT = "PROCESSSKIPCOUNT";
    public static final String FILTERCOUNT = "FILTERCOUNT";
    public static final String WRITESKIPCOUNT = "WRITESKIPCOUNT";
    public static final String READERCHECKPOINTINFO = "READERCHECKPOINTINFO";
    public static final String WRITERCHECKPOINTINFO = "WRITERCHECKPOINTINFO";
    public static final String PARTITION_EXECUTION = "PARTITION_EXECUTION";
    
    static final int EXECUTION_EXCEPTION_LENGTH_LIMIT = 2048;

    static String formatException(final Exception exception) {
        if (exception == null) {
            return null;
        }

        String asString = Throwables.getStackTraceAsString(exception);
        final Charset charset = Charset.defaultCharset();
        byte[] asBytes = asString.getBytes(charset);
        if (asBytes.length <= EXECUTION_EXCEPTION_LENGTH_LIMIT) {
            return asString;
        }

        asString = exception + BatchUtil.NL + Throwables.getRootCause(exception);
        asBytes = asString.getBytes(charset);
        if (asBytes.length <= EXECUTION_EXCEPTION_LENGTH_LIMIT) {
            return asString;
        }

        final ByteBuffer bb = ByteBuffer.wrap(asBytes, 0, EXECUTION_EXCEPTION_LENGTH_LIMIT);
        final CharBuffer cb = CharBuffer.allocate(EXECUTION_EXCEPTION_LENGTH_LIMIT);
        final CharsetDecoder decoder = charset.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.IGNORE);
        decoder.decode(bb, cb, true);
        decoder.flush(cb);
        return new String(cb.array(), 0, cb.position());
    }

}
