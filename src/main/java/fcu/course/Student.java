package fcu.course;

import lombok.*;

import java.time.LocalDate;

//@Getter // 使用 Lombok 的 @Getter 生成 getter 方法
//@Setter // 使用 Lombok 的 @Setter 生成 setter 方法
@AllArgsConstructor // 使用 Lombok 的 @AllArgsConstructor 生成有參數的建構子
@NoArgsConstructor  // 使用 Lombok 的 @NoArgsConstructor 生成無參數的建構子
//@ToString  // 使用 Lombok 的 @ToString 生成 toString 方法
@Data // 使用 Lombok 的 @Data 生成所有必要的方法，包括 getter、setter、toString、equals 和 hashCode
public class Student {
    private  int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
}
