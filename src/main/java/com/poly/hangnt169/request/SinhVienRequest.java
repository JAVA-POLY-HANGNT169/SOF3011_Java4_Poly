package com.poly.hangnt169.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hangnt169
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SinhVienRequest {
    private Long id;
    private String ma;
    private String ten;
    private boolean gioiTinh;
    private String email;
    private Long chuyenNganhID;
    private Long lopID;

}
