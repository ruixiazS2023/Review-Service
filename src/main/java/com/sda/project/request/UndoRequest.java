package com.sda.project.request;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class UndoRequest {
    private Timestamp updateTime;
}
