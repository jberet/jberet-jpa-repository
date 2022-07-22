/*
 * Copyright (c) 2022 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.jberet.jpa.repository.entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.batch.runtime.BatchStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import static org.jberet.jpa.repository.TableColumnsJpa.BATCHSTATUS;
import static org.jberet.jpa.repository.TableColumnsJpa.EXECUTIONEXCEPTION;
import static org.jberet.jpa.repository.TableColumnsJpa.EXITSTATUS;
import static org.jberet.jpa.repository.TableColumnsJpa.PARTITION_EXECUTION;
import static org.jberet.jpa.repository.TableColumnsJpa.PERSISTENTUSERDATA;
import static org.jberet.jpa.repository.TableColumnsJpa.READERCHECKPOINTINFO;
import static org.jberet.jpa.repository.TableColumnsJpa.STEPEXECUTIONID;
import static org.jberet.jpa.repository.TableColumnsJpa.VERSION;
import static org.jberet.jpa.repository.TableColumnsJpa.WRITERCHECKPOINTINFO;

/**
 *
 * @author a.moscatelli
 */
@Entity
@Table(name = PARTITION_EXECUTION)
public class PartitionExecutionJpa implements Serializable {

    @Id
    private Long id;

    @Column(name = VERSION)
    private Long version;

    @Id
    @ManyToOne()
    @JoinColumn(name = STEPEXECUTIONID)
    private StepExecutionJpa stepExecution;

    @Enumerated(EnumType.STRING)
    @Column(name = BATCHSTATUS)
    private BatchStatus batchStatus;

    @Column(name = EXITSTATUS)
    private String exitStatus;

    @Column(name = EXECUTIONEXCEPTION)
    private String executionException;

    @Lob
    @Column(name = PERSISTENTUSERDATA)
    private byte[] persistenUserData;

    @Lob
    @Column(name = READERCHECKPOINTINFO)
    private byte[] readerCheckPointInfo;

    @Lob
    @Column(name = WRITERCHECKPOINTINFO)
    private byte[] writerCheckPointInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public StepExecutionJpa getStepExecution() {
        return stepExecution;
    }

    public void setStepExecution(StepExecutionJpa stepExecution) {
        this.stepExecution = stepExecution;
    }

    public BatchStatus getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(BatchStatus batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(String exitStatus) {
        this.exitStatus = exitStatus;
    }

    public String getExecutionException() {
        return executionException;
    }

    public void setExecutionException(String executionException) {
        this.executionException = executionException;
    }

    public byte[] getPersistenUserData() {
        return persistenUserData;
    }

    public void setPersistenUserData(byte[] persistenUserData) {
        this.persistenUserData = persistenUserData;
    }

    public byte[] getReaderCheckPointInfo() {
        return readerCheckPointInfo;
    }

    public void setReaderCheckPointInfo(byte[] readerCheckPointInfo) {
        this.readerCheckPointInfo = readerCheckPointInfo;
    }

    public byte[] getWriterCheckPointInfo() {
        return writerCheckPointInfo;
    }

    public void setWriterCheckPointInfo(byte[] writerCheckPointInfo) {
        this.writerCheckPointInfo = writerCheckPointInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartitionExecutionJpa)) {
            return false;
        }
        PartitionExecutionJpa cast = PartitionExecutionJpa.class.cast(o);
        return Objects.equals(getId(), cast.getId()) && Objects.equals(getStepExecution(), cast.getStepExecution());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stepExecution);
    }

}
