package com.sda.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> {
    List<T> reviews;
    boolean hasMore;
    long total;
}
