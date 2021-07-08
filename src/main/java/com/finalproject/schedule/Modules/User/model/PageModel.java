package com.finalproject.schedule.Modules.User.model;

import lombok.Data;

import java.util.List;

@Data
public class PageModel {

    private List list;
    private int pageSize;
    private int pageNumber;
    private int totalPages;
}
