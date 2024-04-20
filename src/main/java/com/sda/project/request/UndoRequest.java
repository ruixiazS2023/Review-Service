package com.sda.project.request;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class UndoRequest {
    private String rid;
    private String historyId;
    private Timestamp updateTime;
}
