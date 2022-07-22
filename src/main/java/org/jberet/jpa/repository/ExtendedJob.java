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

import org.jberet.job.model.Job;
import org.jberet.repository.ApplicationAndJobName;

public class ExtendedJob {

    private ApplicationAndJobName applicationAndJobName;
    private Job job;

    public ApplicationAndJobName getApplicationAndJobName() {
        return applicationAndJobName;
    }

    public void setApplicationAndJobName(ApplicationAndJobName applicationAndJobName) {
        this.applicationAndJobName = applicationAndJobName;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    
}
