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
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import jakarta.batch.runtime.BatchStatus;
import jakarta.batch.runtime.JobExecution;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import org.jberet.jpa.repository.PropertiesConverter;
import static org.jberet.jpa.repository.TableColumnsJpa.BATCHSTATUS;
import static org.jberet.jpa.repository.TableColumnsJpa.CREATETIME;
import static org.jberet.jpa.repository.TableColumnsJpa.ENDTIME;
import static org.jberet.jpa.repository.TableColumnsJpa.EXITSTATUS;
import static org.jberet.jpa.repository.TableColumnsJpa.JOBEXECUTIONID;
import static org.jberet.jpa.repository.TableColumnsJpa.JOBINSTANCEID;
import static org.jberet.jpa.repository.TableColumnsJpa.JOBPARAMETERS;
import static org.jberet.jpa.repository.TableColumnsJpa.LASTUPDATEDTIME;
import static org.jberet.jpa.repository.TableColumnsJpa.RESTARTPOSITION;
import static org.jberet.jpa.repository.TableColumnsJpa.STARTTIME;
import static org.jberet.jpa.repository.TableColumnsJpa.VERSION;
import static org.jberet.jpa.repository.entity.StepExecutionJpa_.JOB_EXECUTION;

/**
 *
 * @author a.moscatelli
 */
@Entity
@Table(name = JOB_EXECUTION)
public class JobExecutionJpa implements Serializable, JobExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = JOBEXECUTIONID)
    private Long id;

    @Column(name = VERSION)
    private Long version;

    @ManyToOne()
    @JoinColumn(name = JOBINSTANCEID, nullable = false)
    private JobInstanceJpa jobInstance;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = CREATETIME)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = STARTTIME)
    private Date startTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = ENDTIME)
    private Date endTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = LASTUPDATEDTIME)
    private Date lastUpdatedTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = BATCHSTATUS)
    private BatchStatus batchStatus;
    
    @Column(name = EXITSTATUS)
    private String exitStatus;
    
    @Convert(converter = PropertiesConverter.class)
    @Column(name = JOBPARAMETERS)
    private Properties jobParameters;
    
    @Column(name = RESTARTPOSITION)
    private String restartPosition;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = StepExecutionJpa_.JOB_EXECUTION, cascade = CascadeType.REMOVE , orphanRemoval = true)
    private Collection<StepExecutionJpa> stepExecutions = new ArrayList<>();
    
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

    public JobInstanceJpa getJobInstance() {
        return jobInstance;
    }

    public void setJobInstance(JobInstanceJpa jobInstance) {
        this.jobInstance = jobInstance;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public BatchStatus getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(BatchStatus batchStatus) {
        this.batchStatus = batchStatus;
    }

    @Override
    public String getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(String exitStatus) {
        this.exitStatus = exitStatus;
    }

    @Override
    public Properties getJobParameters() {
        return jobParameters;
    }

    public void setJobParameters(Properties jobParameters) {
        this.jobParameters = jobParameters;
    }

    public String getRestartPosition() {
        return restartPosition;
    }

    public void setRestartPosition(String restartPosition) {
        this.restartPosition = restartPosition;
    }

    @Override
    public long getExecutionId() {
        return this.getId();
    }

    @Override
    public String getJobName() {
        return this.jobInstance.getJobName();
    }

    public Collection<StepExecutionJpa> getStepExecutions() {
        return stepExecutions;
    }

    public void setStepExecutions(Collection<StepExecutionJpa> stepExecutions) {
        this.stepExecutions = stepExecutions;
    }

}
