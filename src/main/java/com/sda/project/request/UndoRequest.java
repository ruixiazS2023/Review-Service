package com.sda.project.request;

import lombok.Data;

import java.sql.Timestamp;

/**
 *
 * This class is used to store the request of undo.
 *
 */
@Data
public class UndoRequest {
    private String rid;
    private String historyId;
    private Timestamp updateTime;
}
