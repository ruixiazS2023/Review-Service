package com.sda.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


/*
    * This class is used to store the result of a page of reviews.
 */
@Data
@AllArgsConstructor
public class PageResult<T> {
    List<T> reviews;
    boolean hasMore;
    long total;
}
