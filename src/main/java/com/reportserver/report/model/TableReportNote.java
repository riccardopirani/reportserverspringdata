package com.reportserver.report.model;

import org.springframework.context.annotation.Description;

import java.io.Serializable;

@Description(value = "Table Note Report Model")
public class TableReportNote implements Serializable {
    public String note;

    public TableReportNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
