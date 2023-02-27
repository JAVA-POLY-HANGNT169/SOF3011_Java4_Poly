package com.poly.hangnt169.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hangnt169
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LopResponse {

    private Long id;
    private String tenLop;
    private String maLop;
    private Integer soLuongSV;

}
