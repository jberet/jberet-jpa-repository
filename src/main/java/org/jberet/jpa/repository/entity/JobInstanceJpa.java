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
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import static org.jberet.jpa.repository.TableColumnsJpa.APPLICATIONNAME;
import static org.jberet.jpa.repository.TableColumnsJpa.JOBINSTANCEID;
import static org.jberet.jpa.repository.TableColumnsJpa.JOBNAME;
import static org.jberet.jpa.repository.TableColumnsJpa.JOB_INSTANCE;
import static org.jberet.jpa.repository.TableColumnsJpa.VERSION;

@Entity
@Table(name = JOB_INSTANCE)
public class JobInstanceJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = JOBINSTANCEID)
    private Long id;

    @Column(name = VERSION)
    private Long version;

    @Column(name = JOBNAME)
    private String jobName;

    @Column(name = APPLICATIONNAME)
    private String applicationName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = JobExecutionJpa_.JOB_INSTANCE, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<JobExecutionJpa> jobExecutions = new ArrayList<>();

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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Collection<JobExecutionJpa> getJobExecutions() {
        return jobExecutions;
    }

    public void setJobExecutions(Collection<JobExecutionJpa> jobExecutions) {
        this.jobExecutions = jobExecutions;
    }

}
