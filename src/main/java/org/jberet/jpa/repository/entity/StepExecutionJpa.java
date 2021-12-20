package org.jberet.jpa.repository.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.batch.runtime.BatchStatus;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static org.jberet.jpa.repository.TableColumnsJpa.BATCHSTATUS;
import static org.jberet.jpa.repository.TableColumnsJpa.COMMITCOUNT;
import static org.jberet.jpa.repository.TableColumnsJpa.ENDTIME;
import static org.jberet.jpa.repository.TableColumnsJpa.EXECUTIONEXCEPTION;
import static org.jberet.jpa.repository.TableColumnsJpa.EXITSTATUS;
import static org.jberet.jpa.repository.TableColumnsJpa.FILTERCOUNT;
import static org.jberet.jpa.repository.TableColumnsJpa.JOBEXECUTIONID;
import static org.jberet.jpa.repository.TableColumnsJpa.PERSISTENTUSERDATA;
import static org.jberet.jpa.repository.TableColumnsJpa.PROCESSSKIPCOUNT;
import static org.jberet.jpa.repository.TableColumnsJpa.READCOUNT;
import static org.jberet.jpa.repository.TableColumnsJpa.READERCHECKPOINTINFO;
import static org.jberet.jpa.repository.TableColumnsJpa.READSKIPCOUNT;
import static org.jberet.jpa.repository.TableColumnsJpa.ROLLBACKCOUNT;
import static org.jberet.jpa.repository.TableColumnsJpa.STARTTIME;
import static org.jberet.jpa.repository.TableColumnsJpa.STEPEXECUTIONID;
import static org.jberet.jpa.repository.TableColumnsJpa.STEPNAME;
import static org.jberet.jpa.repository.TableColumnsJpa.VERSION;
import static org.jberet.jpa.repository.TableColumnsJpa.WRITECOUNT;
import static org.jberet.jpa.repository.TableColumnsJpa.WRITERCHECKPOINTINFO;
import static org.jberet.jpa.repository.TableColumnsJpa.WRITESKIPCOUNT;
import static org.jberet.jpa.repository.entity.PartitionExecutionJpa_.STEP_EXECUTION;

/**
 *
 * @author a.moscatelli
 */
@Entity
@Table(name = STEP_EXECUTION)
public class StepExecutionJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = STEPEXECUTIONID)
    private Long id;

    @Column(name = VERSION)
    private Long version;
    
    @ManyToOne()
    @JoinColumn(name = JOBEXECUTIONID, nullable = false)
    private JobExecutionJpa jobExecution;
    
    @Column(name = STEPNAME)
    private String stepName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = STARTTIME)
    private Date startTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = ENDTIME)
    private Date endTime;
    
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

    @Column(name = READCOUNT)
    private Long readCount;

    @Column(name = WRITECOUNT)
    private Long writeCount;

    @Column(name = COMMITCOUNT)
    private Long commitCount;

    @Column(name = ROLLBACKCOUNT)
    private Long rollbackCount;

    @Column(name = READSKIPCOUNT)
    private Long readSkipCount;

    @Column(name = PROCESSSKIPCOUNT)
    private Long processSkipCount;

    @Column(name = FILTERCOUNT)
    private Long filterCount;

    @Column(name = WRITESKIPCOUNT)
    private Long writeSkipCount;
    
    @Lob
    @Column(name = READERCHECKPOINTINFO)
    private byte[] readerCheckPointInfo;
    
    @Lob
    @Column(name = WRITERCHECKPOINTINFO)
    private byte[] writerCheckPointInfo;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = PartitionExecutionJpa_.STEP_EXECUTION, cascade = CascadeType.REMOVE , orphanRemoval = true)
    private Collection<PartitionExecutionJpa> partitionExecutions;
    
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

    public JobExecutionJpa getJobExecution() {
        return jobExecution;
    }

    public void setJobExecution(JobExecutionJpa jobExecution) {
        this.jobExecution = jobExecution;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(Long writeCount) {
        this.writeCount = writeCount;
    }

    public Long getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(Long commitCount) {
        this.commitCount = commitCount;
    }

    public Long getRollbackCount() {
        return rollbackCount;
    }

    public void setRollbackCount(Long rollbackCount) {
        this.rollbackCount = rollbackCount;
    }

    public Long getReadSkipCount() {
        return readSkipCount;
    }

    public void setReadSkipCount(Long readSkipCount) {
        this.readSkipCount = readSkipCount;
    }

    public Long getProcessSkipCount() {
        return processSkipCount;
    }

    public void setProcessSkipCount(Long processSkipCount) {
        this.processSkipCount = processSkipCount;
    }

    public Long getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(Long filterCount) {
        this.filterCount = filterCount;
    }

    public Long getWriteSkipCount() {
        return writeSkipCount;
    }

    public void setWriteSkipCount(Long writeSkipCount) {
        this.writeSkipCount = writeSkipCount;
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

    public Collection<PartitionExecutionJpa> getPartitionExecutions() {
        return partitionExecutions;
    }

    public void setPartitionExecutions(Collection<PartitionExecutionJpa> partitionExecutions) {
        this.partitionExecutions = partitionExecutions;
    }
    
}
