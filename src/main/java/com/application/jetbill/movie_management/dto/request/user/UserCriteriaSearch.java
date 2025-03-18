package com.application.jetbill.movie_management.dto.request.user;

import jakarta.validation.constraints.Size;

public record UserCriteriaSearch(@Size(min = 3, max = 50 , message = "{generic.size}")String name,
                                 String username) {
}
