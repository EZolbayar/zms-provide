package com.example.terguun.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TBLOANINSTALLMENTS-ийн нийлмэл түлхүүр. INSTALLMENTID нь данс тус бүрд 1, 2, 3 (эхний төлөлт,
 * хоёр дахь төлөлт г.м) гэж давтагддаг тул дангаараа мөрийг заахгүй. ACCOUNTID-тай хослуулаагүй бол
 * олон дансны хуваарийг нэг дор татахад ижил дугаартай мөрүүд хоорондоо давхцаж алдагдана.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanInstallmentId implements Serializable {

    private String accountId;
    private Long installmentId;
}
