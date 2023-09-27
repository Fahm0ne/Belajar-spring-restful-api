package com.mff.restfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaggingResponse {

    private Integer currentPage;

    private Integer totalPage;

    private Integer size;

}
