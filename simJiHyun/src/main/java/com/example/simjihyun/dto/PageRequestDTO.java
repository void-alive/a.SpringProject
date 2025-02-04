package com.example.simjihyun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.net.URLEncoder;

import static org.aspectj.apache.bcel.Constants.types;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

  @Builder.Default
  private int page = 1;

  @Builder.Default
  private int size = 9;

  private String type;

  private String keyword;

  public String[] getTypes() {
    if (type == null || type.isEmpty()) {
      return null;
    }
    return type.split(",");
  }

  public Pageable pageable(String... props) {
    return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
  }

  private String link;

  public String getLink() {

    if (link == null) {
      StringBuilder builder = new StringBuilder();
      builder.append("page=" + this.page);
      builder.append("&size=" + this.size);
    }

    if (type != null && type.length() > 0) {
      builder().type("&size=" + this.size);
    }

    if (keyword != null) {
      try {
        builder().type("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
      }
      link = builder().toString();
    }
    return link;
  }

}

